package project.spring.services.kafka.consumer;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Service;

import project.spring.avro.PagamentoPixRequest;
import project.spring.services.PagamentoPixService;

@Service
public class PagamentoPixConsumer {

	private static final Logger LOGGER = LoggerFactory.getLogger(PagamentoCreditoConsumer.class);
	
	@Autowired
	private PagamentoPixService service;
	
	@KafkaListener(topics = "${spring.kafka.topico-assinatura-pix}", groupId = "${spring.kafka.consumer.pix.group-id}")
	public void pagamentoCreditoConsumer(PagamentoPixRequest pagamento, Acknowledgment ack) throws IOException {
		LOGGER.info("Consumindo mensagem {}",  pagamento);
		
		service.createPagamentoPix(project.spring.dto.request.PagamentoPixRequest.toRequest(pagamento));
			
		ack.acknowledge();
	}
	
}
