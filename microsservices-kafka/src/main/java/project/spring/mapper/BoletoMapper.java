package project.spring.mapper;

import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

import project.spring.entities.Boleto;
import project.spring.enums.StatusBoleto;

@Component
public class BoletoMapper {

	public Boleto toBoletoEntity (project.spring.avro.Boleto boleto) {
	    StatusBoleto statusEnum = StatusBoleto.valueOf(boleto.getStatus().toString());
		return new Boleto(boleto.getCodigoBarras().toString(), statusEnum , LocalDateTime.now());
	}
	
	public project.spring.avro.Boleto toBoletoAvro(Boleto boleto){
		return project.spring.avro.Boleto.newBuilder()
				.setCodigoBarras(boleto.getCodigoBarras())
				.setStatus(boleto.getStatusBoleto().toString()).build();
	}
	
}
