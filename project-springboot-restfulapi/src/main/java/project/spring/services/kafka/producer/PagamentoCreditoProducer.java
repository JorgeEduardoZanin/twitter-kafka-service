package project.spring.services.kafka.producer;
         
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;


import project.spring.avro.PagamentoRequest;

@Service
public class PagamentoCreditoProducer {

    @Value("${spring.kafka.topico-assinatura-credito}")
    private String topico;

    @Autowired
    private KafkaTemplate<String, PagamentoRequest> kafkaTemplate;

    public void enviarMensagem(PagamentoRequest pagamentoRequest) {
    	System.out.println(pagamentoRequest);
        kafkaTemplate.send(topico, pagamentoRequest);
    }
 
}
