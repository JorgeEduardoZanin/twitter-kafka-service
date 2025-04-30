package project.spring.services.kafka.producer;
         
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import project.spring.avro.Boleto;

@Service
public class BoletoProducer {

    @Value("${spring.kafka.topico-assinatura}")
    private String topico;

    @Autowired
    private KafkaTemplate<String, Boleto> kafkaTemplate;

    public void enviarMensagem(Boleto boleto) {
        kafkaTemplate.send(topico, getKey(boleto), boleto);
    }
    
    private String getKey(Boleto boleto) {
    	if(boleto.getCodigoBarras().toString().substring(0,1).equals("2")){
    		return "chave1";
    	}
    	
    	return "chave2";
    }
}
