package project.spring.services.kafka.producer;
         
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import project.spring.avro.PagamentoPixRequest;

@Service
public class PagamentoPixProducer {

	 @Value("${spring.kafka.topico-assinatura-pix}")
	 private String topico;

	 @Autowired
	 private KafkaTemplate<String, PagamentoPixRequest> kafkaTemplate;

	 public void enviarMensagem(PagamentoPixRequest pagamentoPixRequest) {
		 	System.out.println(pagamentoPixRequest);
		 	kafkaTemplate.send(topico, pagamentoPixRequest);
	 }
 
}
