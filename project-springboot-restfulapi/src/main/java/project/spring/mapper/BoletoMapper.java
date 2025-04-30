package project.spring.mapper;

import org.springframework.stereotype.Component;

import project.spring.dto.response.BoletoResponse;
import project.spring.entities.Boleto;
import project.spring.enums.StatusBoleto;

@Component
public class BoletoMapper {

	public static BoletoResponse boletoDTO(Boleto boleto) {
		return new BoletoResponse(boleto.getCodigoBarras(), boleto.getStatusBoleto(), boleto.getDataCriacao(), boleto.getDataAtualizacao());
	}
	
	public static project.spring.avro.Boleto boletoAvro (Boleto boleto){
		return project.spring.avro.Boleto.newBuilder()
				.setCodigoBarras(boleto.getCodigoBarras())
				.setStatus(boleto.getStatusBoleto().toString()).build();
		
	}
	
	public Boleto toBoletoEntity(project.spring.avro.Boleto boleto) {
		Boleto boletoEntity = new Boleto();
		boletoEntity.setCodigoBarras(boleto.getCodigoBarras().toString());
		boletoEntity.setStatusBoleto(StatusBoleto.valueOf(boleto.getStatus().toString()));
		
		return boletoEntity;
	}
	
}
