package project.spring.dto.request;

import project.spring.avro.PagamentoRequest;

public record UsuarioPagamentoRequest(String usuarioId, String cpf_cnpj, String nome) {
	
	public static UsuarioPagamentoRequest toUsuarioRequest(PagamentoRequest pagamento) {
		return new UsuarioPagamentoRequest(pagamento.getUsuario().getId().toString(),
				pagamento.getUsuario().getCpfCnpj().toString(), 
				pagamento.getUsuario().getNome().toString());
	}
	
}
