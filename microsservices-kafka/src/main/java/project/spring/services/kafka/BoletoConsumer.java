package project.spring.services.kafka;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Service;

import project.spring.avro.Boleto;
import project.spring.mapper.BoletoMapper;
import project.spring.services.ValidarBoletoService;



@Service
public class BoletoConsumer{

	private static final Logger LOGGER = LoggerFactory.getLogger(BoletoConsumer.class);
	@Autowired
	private ValidarBoletoService validarBoletoService;
	@Autowired
	private BoletoMapper mapper;
	
	@KafkaListener(topics = "${spring.kafka.topico-assinatura}", groupId = "${spring.kafka.consumer.group-id}")
	public void consomeBoleto(Boleto boleto, Acknowledgment ack) throws InterruptedException {
		LOGGER.info("Consumindo mensagem {}",  boleto);
		validarBoletoService.validarBoleto(mapper.toBoletoEntity(boleto));
		ack.acknowledge();
	}
	
	
	}
	

