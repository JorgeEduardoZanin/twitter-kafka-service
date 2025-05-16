package project.spring.services.kafka.consumer;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Service;
import project.spring.avro.NotificacaoPagamento;
import project.spring.services.NotificacaoService;




@Service
public class NotificacaoPagamentoConsumer{

	private static final Logger LOGGER = LoggerFactory.getLogger(NotificacaoPagamentoConsumer.class);
	
	@Autowired
	private NotificacaoService service;

    
	
	@KafkaListener(topics = "${spring.kafka.topico-notificacao}", groupId = "${spring.kafka.consumer.notificacao.group-id}")
	public void pagamentoCreditoConsumer(NotificacaoPagamento notificacao, Acknowledgment ack) throws IOException {
		LOGGER.info("Consumindo mensagem {}",  notificacao);
		
		service.updateNotificacao(notificacao);
			
		ack.acknowledge();
	}
	
	
	}
	

