/*
package project.spring.services.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import project.spring.avro.Boleto;


@Service
public class BoletoProducer {
	
	@Value("${spring.kafka.topico-notificacao}")
	private String topico;
	
	@Autowired
	private KafkaTemplate<String, Boleto> kafkaTemplate;
	
	public void enviarMensagem(Boleto boleto) {
		kafkaTemplate.send(topico, boleto);
	}
	
}
*/