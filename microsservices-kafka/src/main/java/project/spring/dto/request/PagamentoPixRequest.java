package project.spring.dto.request;

import java.math.BigDecimal;

import project.spring.util.DecimalUtils;

public record PagamentoPixRequest(String usuarioId, String nome, String cpf_cnpj, BigDecimal valor, Long identificadorApi) {

	public static PagamentoPixRequest toRequest(project.spring.avro.PagamentoPixRequest pagamentoPix) {
		return new PagamentoPixRequest(pagamentoPix.getUsuario().getId().toString(),
				pagamentoPix.getUsuario().getNome() != null ? pagamentoPix.getUsuario().getNome().toString() : null,
				pagamentoPix.getUsuario().getCpfCnpj() != null ? pagamentoPix.getUsuario().getCpfCnpj().toString() : null,
				DecimalUtils.toBigDecimal(pagamentoPix.getValue(), 2), 
				pagamentoPix.getIdentificadorApiPrincipal().getIdentificadorApiPrincipal());
	}
	
}
