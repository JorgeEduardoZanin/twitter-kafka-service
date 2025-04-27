package project.spring.services.kafka.producer;

import java.io.File;
import java.io.IOException;
import java.time.ZoneOffset;

import org.apache.avro.Schema;                  
import org.apache.avro.generic.GenericRecord;                 
import org.apache.avro.generic.GenericRecordBuilder;         
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import project.spring.avro.Boleto;
import project.spring.dto.response.BoletoResponse;

@Component
public class BoletoProducer {

    @Value("${spring.kafka.topico-boleto}")
    private String topico;

    // Use GenericRecord, não GenericRecordBuilder
    @Autowired
    private KafkaTemplate<String, Boleto> kafkaTemplate;

    public void enviarMensagem(Boleto boleto) {
        kafkaTemplate.send(topico, boleto);
    }
}
