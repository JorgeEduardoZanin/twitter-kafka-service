package project.spring.dto.request;

import project.spring.entities.Usuario;

public record UsuarioRequest(String nome, String cpf_cnpj, String email, Integer idade, String password) {

	public Usuario toEntity() {
		return new Usuario(this.nome, this.cpf_cnpj, this.email, this.idade, this.password);
	}
	
}
