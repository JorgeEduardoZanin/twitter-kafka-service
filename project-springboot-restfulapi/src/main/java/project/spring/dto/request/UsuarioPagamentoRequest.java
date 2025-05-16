package project.spring.dto.request;

import project.spring.entities.Usuario;

public record UsuarioPagamentoRequest(String id, String cpf_cnpj, String nome) {

	public static UsuarioPagamentoRequest toRequest(Usuario usuario) {
		return new UsuarioPagamentoRequest(usuario.getId().toString(), 
				usuario.getCpf_cnpj() != null ? usuario.getCpf_cnpj():null, 
				usuario.getNome() != null ? usuario.getNome():null) ;
	}
	
}
