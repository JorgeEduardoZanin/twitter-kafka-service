package projeto.spring.services.kafka;

import java.util.Map;

import org.apache.kafka.common.TopicPartition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.listener.ConsumerSeekAware;
import org.springframework.stereotype.Service;


@Service
public class BoletoConsumer implements ConsumerSeekAware{

	private static final Logger LOGGER = LoggerFactory.getLogger(BoletoConsumer.class);
	
	
	@KafkaListener(topics = "${spring.kafka.topico-boleto}",
			    groupId = "validador-boleto"
			  )
	public void consomeBoleto(String msg) {

		try {
		LOGGER.info("Consumindo mensagem -%s"+  msg);
	    System.out.println("string recebida: " + msg);
		}catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
	
	}


	@Override
	public void onPartitionsAssigned(Map<TopicPartition, Long> assignments, ConsumerSeekCallback callback) {
		assignments.forEach((t, o)-> callback.seekToEnd(t.topic(), t.partition()));
	}
	
	
}
