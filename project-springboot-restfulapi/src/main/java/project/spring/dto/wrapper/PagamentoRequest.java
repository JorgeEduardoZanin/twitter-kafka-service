package project.spring.dto.wrapper;


import java.math.BigDecimal;
import java.nio.ByteBuffer;

import org.apache.avro.Conversions.DecimalConversion;
import org.apache.avro.LogicalTypes;
import org.apache.avro.Schema;

import project.spring.dto.request.CartaoCreditoRequest;
import project.spring.dto.request.TitularCartaoCreditoRequest;
import project.spring.dto.request.UsuarioPagamentoRequest;


public record PagamentoRequest(CartaoCreditoRequest cartao, TitularCartaoCreditoRequest titular, BigDecimal value,
		UsuarioPagamentoRequest usuario, Long identificadorApiPrincipal){
	
	public project.spring.avro.PagamentoRequest toAvro(){
		
	    project.spring.avro.TitularCartaoCreditoRequest titularAvro =
	        project.spring.avro.TitularCartaoCreditoRequest.newBuilder()
	            .setNomeTitularCartao(this.titular.nomeTitularCartao())
	            .setEmail(this.titular.email())
	            .setCpfCnpjTitular(this.titular.cpf_cnpj_titular())
	            .setCodigoPostal(this.titular.codigoPostal())
	            .setNumeroEndereco(this.titular.numeroEndereco())
	            .setTelefone(this.titular.telefone())
	            .build();
	    
	    project.spring.avro.IdentificadorApiPrincipalRequest identificadorApi = 
	    		project.spring.avro.IdentificadorApiPrincipalRequest.newBuilder()
	    		.setIdentificadorApiPrincipal(this.identificadorApiPrincipal())
	    		.build();

	   
	    Schema schema = project.spring.avro.PagamentoRequest.getClassSchema();
        Schema valueSchema = schema.getField("value").schema();
        LogicalTypes.Decimal decimalType = LogicalTypes.decimal(10, 2);
        ByteBuffer valueAvro = new DecimalConversion().toBytes(value, valueSchema, decimalType);
        	 
	    project.spring.avro.UsuarioPagamentoRequest usuarioAvro =
	        project.spring.avro.UsuarioPagamentoRequest.newBuilder()
	            .setId(this.usuario.id())
	            .setCpfCnpj(this.usuario.cpf_cnpj())
	            .setNome(this.usuario.nome())
	            .build();
	    
	    
	    project.spring.avro.CartaoCreditoRequest cartaoAvro =
		        project.spring.avro.CartaoCreditoRequest.newBuilder()
		            .setNomeCartao(this.cartao.nomeCartao())
		            .setNumeroCartao(this.cartao.numeroCartao())
		            .setMesExpiracao(this.cartao.mesExpiracao())
		            .setAnoExpiracao(this.cartao.anoExpiracao())
		            .setCcv(this.cartao.ccv())
		            .build();

	
	    return project.spring.avro.PagamentoRequest.newBuilder()
	        .setCartao(cartaoAvro)
	        .setTitular(titularAvro)
	        .setValue(valueAvro)
	        .setUsuario(usuarioAvro)
	        .setIdentificadorApiPrincipal(identificadorApi)
	        .build();
				
				
				
	}
	
}
