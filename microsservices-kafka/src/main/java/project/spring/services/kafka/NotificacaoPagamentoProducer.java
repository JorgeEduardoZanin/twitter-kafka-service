
package project.spring.services.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import project.spring.avro.NotificacaoPagamento;


@Service
public class NotificacaoPagamentoProducer {
	
	@Value("${spring.kafka.topico-notificacao}")
	private String topico;
	
	@Autowired
	private KafkaTemplate<String, NotificacaoPagamento> kafkaTemplate;
	
	public void enviarMensagem(NotificacaoPagamento notificacao) {
		kafkaTemplate.send(topico, notificacao);
	}
	
}

