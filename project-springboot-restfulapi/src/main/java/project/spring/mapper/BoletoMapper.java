package project.spring.mapper;

import project.spring.dto.response.BoletoResponse;
import project.spring.entities.Boleto;

public class BoletoMapper {

	public static BoletoResponse boletoDTO(Boleto boleto) {
		return new BoletoResponse(boleto.getCodigoBarras(), boleto.getStatusBoleto(), boleto.getDataCriacao(), boleto.getDataAtualizacao());
	}
	
	public static project.spring.avro.Boleto boletoAvro (Boleto boleto){
		return project.spring.avro.Boleto.newBuilder()
				.setCodigoBarras(boleto.getCodigoBarras())
				.setStatus(boleto.getStatusBoleto().toString()).build();
		
	}
	
}
