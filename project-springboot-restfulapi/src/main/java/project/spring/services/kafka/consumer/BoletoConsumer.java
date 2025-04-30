package project.spring.services.kafka.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Service;

import project.spring.avro.Boleto;
import project.spring.mapper.BoletoMapper;
import project.spring.services.NotificacaoServices;




@Service
public class BoletoConsumer{

	private static final Logger LOGGER = LoggerFactory.getLogger(BoletoConsumer.class);
	
	@Autowired
	private NotificacaoServices notificationService;
	
	@Autowired
	private BoletoMapper mapper;
	
	@KafkaListener(topics = "${spring.kafka.topico-notificacao}", groupId = "${spring.kafka.consumer.group-id}")
	public void consomeBoleto(Boleto boleto, Acknowledgment ack) throws InterruptedException {
		LOGGER.info("Consumindo mensagem {}",  boleto);
		notificationService.gerenciadorNotificacoes(mapper.toBoletoEntity(boleto));
		ack.acknowledge();
	}
	
	
	}
	

