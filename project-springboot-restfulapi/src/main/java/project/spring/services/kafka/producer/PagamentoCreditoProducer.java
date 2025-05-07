package project.spring.services.kafka.producer;
         
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import project.spring.avro.Boleto;
import project.spring.avro.PagamentoRequest;

@Service
public class PagamentoCreditoProducer {

    @Value("${spring.kafka.topico-assinatura-credito}")
    private String topico;

    @Autowired
    private KafkaTemplate<String, PagamentoRequest> kafkaTemplate;

    public void enviarMensagem(PagamentoRequest pagamentoRequest) {
        kafkaTemplate.send(topico, pagamentoRequest);
    }
    
    private String getKey(Boleto boleto) {
    	if(boleto.getCodigoBarras().toString().substring(0,1).equals("2")){
    		return "chave1";
    	}
    	
    	return "chave2";
    }
}
