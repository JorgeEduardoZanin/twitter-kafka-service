# Twitter-kafka-service


Este repositório contém um serviço Java Spring Boot desenvolvido para demonstrar habilidades em autenticação/autorização (OAuth2 com roles) e mensageria assíncrona (Apache Kafka). Embora o nome faça referência a “Twitter”, o foco principal é a orquestração de login, controle de permissões e fluxo de processamento de pedidos de assinatura — não a integração direta com a API do Twitter.

---

## 📖 Descrição

O objetivo deste projeto é apresentar um cenário real de microserviços em que:

1. **Autenticação e Autorização** são gerenciadas via OAuth2 com encriptação assimétrica, utilizando chaves RSA.  
2. **Usuários** pré-cadastrados, com roles (por exemplo, `ROLE_USER` ou `ROLE_ADMIN`), podem solicitar assinaturas recorrentes em dois modelos de pagamento: Pix ou Crédito. A cada pedido, o período da assinatura do usuário é incrementado em 1 mês.  
3. **Mensageria Assíncrona** é realizada através do Apache Kafka, com mensagens serializadas em Avro. Isso permite desacoplar a lógica de criação & validação de notificações de pagamento (serviço principal) da lógica de execução dos pagamentos (microserviço consumidor).  
4. **Integração com API de Pagamento Asaas (Sandbox)**: após validar e persistir uma notificação, o microserviço consumidor utiliza os dados recebidos para criar cobranças no Asaas em modo sandbox. A resposta final de pagamento é recebida via webhook do Asaas e retornada ao serviço principal por outra mensagem Kafka, concluindo o fluxo.  
5. **Persistência de Dados** no PostgreSQL, de forma que cada microserviço (API principal e consumidor) mantenha seu próprio banco para entidades específicas (notificações e registros de pagamento, respectivamente).

Este cenário ressalta a capacidade de projetar e implementar um fluxo de autenticação/autorizações robusto, integrar serviços externos (Asaas), orquestrar processos assíncronos com Kafka e manter consistência de dados em bancos distintos.

---

## ⚙️ Funcionalidades Principais

- **Login com OAuth2 e Roles**  
  - JWTs assinados com chave privada (RSA) e validados pelos Resource Servers com chave pública.  
  - Controle de acesso baseado em roles (por exemplo, somente usuários com `ROLE_USER` podem solicitar assinaturas).  

- **Solicitação de Assinatura (Pix ou Crédito)**  
  - Endpoints REST protegidos por OAuth2.  
  - Criação de notificação de pagamento no banco do serviço principal, sem armazenar dados de pagamento puro.  
  - Emissão de evento Kafka (Avro) com `notificationId` e detalhes do pedido.

- **Microserviço Kafka Consumer (Execução de Pagamento)**  
  - Desserialização de evento Avro e chamada à API Asaas (Sandbox) para criar cobrança.  
  - Persistência da entidade de pagamento em banco (status “PENDENTE”).  
  - Exposição de endpoint para receber webhook Asaas com confirmação do pagamento.  
  - Após validação do webhook, persistência do status definitivo (por exemplo, “PAGO” ou “FALHA”) e publicação de evento Kafka de retorno.

- **Notificação de Resultado à API Principal**  
  - O próprio serviço principal escuta o tópico de resposta, atualiza a notificação original e, em caso de pagamento aprovado, incrementa o prazo de assinatura do usuário em 1 mês.

---

## 🛠️ Tecnologias Utilizadas

- **Linguagem e Framework**  
  - Java 21  
  - Spring Boot (Spring Security, Spring OAuth2, Spring Kafka, Spring Data JPA)  

- **Banco de Dados**  
  - PostgreSQL  

- **Mensageria**  
  - Apache Kafka (Docker Compose fornecendo ZooKeeper, Kafka Broker, Schema Registry e Control Center)  
  - Avro para serialização/​desserialização de mensagens  

- **Autenticação & Autorização**  
  - OAuth2 com JWT (criptografia assimétrica RSA)  
  - Controle de acesso baseado em roles (perfis de usuário)  

- **Integração de Pagamento**  
  - API Asaas (modo Sandbox) para criação de cobranças (Pix ou Cartão de Crédito)  
  - Webhook Asaas para confirmação de pagamento  

- **Orquestração de Contêineres**  
  - Docker & Docker Compose (para rodar ZooKeeper, Kafka, Schema Registry e Control Center)  

- **Gerenciamento de Dependências**  
  - Maven  

---

## 🔄 Fluxo de Processamento

1. **Login / Obtenção de Token**  
   - Usuário cadastrado (role definida) faz `POST /oauth/token` com credenciais.  
   - Serviço retorna JWT assinado pela chave privada RSA.

2. **Envio de Pedido de Assinatura**  
   - Cliente realiza requisição protegida (`POST /assinatura/pix` ou `/assinatura/credito`) com Bearer Token.  
   - API Principal:
     - Valida token e verifica role (`ROLE_USER`).  
     - Cria e persiste uma **Notificação** no banco (status: `PENDENTE`).  
     - Publica evento Kafka no tópico `request-assinatura` (mensagem Avro contendo `notificationId`, `userId`, `tipoPagamento`, `valor`, etc.).

3. **Microserviço Consumer Kafka / Execução Asaas**  
   - Lê evento do tópico `request-assinatura`, faz desserialização Avro.  
   - Chama a API Asaas (Sandbox) para criar cobrança (Pix ou Crédito).  
   - Persiste entidade **Pagamento** no seu próprio banco (status inicial: `AGUARDANDO_WEBHOOK`).  
   - Aguarda callback (webhook) do Asaas que informará o resultado final do pagamento.  

4. **Recebimento do Webhook Asaas**  
   - Asaas envia JSON de callback ao endpoint `/webhook/asaas` do Consumer.  
   - Consumer valida assinatura do webhook (se configurado), atualiza status do **Pagamento** (por exemplo, `PAGO` ou `CANCELADO`).  
   - Publica evento Kafka no tópico `response-assinatura` com `notificationId` e `statusPagamento`.

5. **Atualização da Notificação na API Principal**  
   - Consumidor (ou a própria API Principal, se configurar outro listener) lê o tópico `response-assinatura`.  
   - Atualiza a **Notificação** original para `PAGO` (ou outro status).  
   - Se pagamento aprovado, incrementa 1 mês na data de expiração do usuário (persistido na tabela de usuários).  
   - Caso deseje, notifica usuário final via e-mail/WebSocket ou outro canal.

---

## 🚀 Como Executar (Alta Visão)

1. **Pré-requisitos**  
   - Java 21 instalado  
   - Maven 3.8+  
   - Docker e Docker Compose  
   - PostgreSQL acessível (com usuário, senha e banco criados)  
   - Chaves RSA (par pública/privada) para OAuth2  
   - API Key do Asaas (Sandbox)  

2. **Subir Infraestrutura Kafka via Docker Compose**  
   - No diretório raiz do projeto, execute:
     ```bash
     docker-compose up -d
     ```
   - Isso levantará ZooKeeper, Kafka Broker, Schema Registry e Control Center.

3. **Configurar `application.yml` (ou variáveis de ambiente)**  
   - Ajuste dados de conexão ao PostgreSQL (URL, usuário e senha).  
   - Informe chaves RSA (pública e privada) em `classpath:keys/`.  
   - Defina endpoints Kafka (bootstrap servers e schema registry).  
   - Configure API Key e URL base do Asaas (Sandbox).  

4. **Build e Início do Serviço**  
   ```bash
   mvn clean install
   mvn spring-boot:run
