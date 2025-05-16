package project.spring.dto.wrapper;

import java.math.BigDecimal;
import java.nio.ByteBuffer;

import org.apache.avro.LogicalTypes;
import org.apache.avro.Schema;
import org.apache.avro.Conversions.DecimalConversion;

import project.spring.avro.PagamentoPixRequest;

import project.spring.dto.request.UsuarioPagamentoRequest;


public record PagamentoPixWrapper(Long identificadorApiPrincipal, BigDecimal valor, UsuarioPagamentoRequest usuario) {

	public PagamentoPixRequest toAvro() {
		
		   project.spring.avro.IdentificadorApiPrincipalRequest identificadorApi = 
		    		project.spring.avro.IdentificadorApiPrincipalRequest.newBuilder()
		    		.setIdentificadorApiPrincipal(this.identificadorApiPrincipal())
		    		.build();

		   
		    Schema schema = project.spring.avro.PagamentoRequest.getClassSchema();
	        Schema valueSchema = schema.getField("value").schema();
	        LogicalTypes.Decimal decimalType = LogicalTypes.decimal(10, 2);
	        ByteBuffer valueAvro = new DecimalConversion().toBytes(valor, valueSchema, decimalType);
	        	 
		    project.spring.avro.UsuarioPagamentoRequest usuarioAvro =
		        project.spring.avro.UsuarioPagamentoRequest.newBuilder()
		            .setId(this.usuario.id())
		            .setCpfCnpj(this.usuario.cpf_cnpj())
		            .setNome(this.usuario.nome())
		            .build();
		    
		    return PagamentoPixRequest.newBuilder()
		    		.setIdentificadorApiPrincipal(identificadorApi)
		    		.setUsuario(usuarioAvro)
		    		.setValue(valueAvro)
		    		.build();
		    		
	}
	
}
