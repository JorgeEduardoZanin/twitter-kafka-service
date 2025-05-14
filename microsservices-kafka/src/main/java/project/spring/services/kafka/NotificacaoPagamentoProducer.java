
package project.spring.services.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import project.spring.avro.NotificacaoPagamento;


@Service
public class NotificacaoPagamentoProducer {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(NotificacaoPagamentoProducer.class);
	
	@Value("${spring.kafka.topico-notificacao}")
	private String topico;
	
	@Autowired
	private KafkaTemplate<String, NotificacaoPagamento> kafkaTemplate;
	
	public void enviarMensagem(NotificacaoPagamento notificacao) {
		LOGGER.info("Consumindo mensagem {}",  notificacao);
		kafkaTemplate.send(topico, notificacao);
	}
	
}

