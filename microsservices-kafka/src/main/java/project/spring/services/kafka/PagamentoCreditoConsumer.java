package project.spring.services.kafka;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Service;

import project.spring.avro.PagamentoRequest;



@Service
public class PagamentoCreditoConsumer{

	private static final Logger LOGGER = LoggerFactory.getLogger(PagamentoCreditoConsumer.class);


	
	@KafkaListener(topics = "${spring.kafka.topico-assinatura-credito}", groupId = "${spring.kafka.consumer.credito.group-id}")
	public void consomeBoleto(PagamentoRequest pagamento, Acknowledgment ack) throws InterruptedException {
		LOGGER.info("Consumindo mensagem {}",  pagamento);
	
		ack.acknowledge();
	}
	
	
	}
	

