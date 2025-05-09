package project.spring.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import project.spring.entities.UsuarioPagamento;

@JsonIgnoreProperties(ignoreUnknown = true)
public record UsuarioPagamentoResponse(String usuarioId, @JsonProperty("id") String customer, @JsonProperty("name") String nome, @JsonProperty("cpfCnpj") String cpf_cnpj){
	
	public UsuarioPagamento toEntity(String usuarioId) {
		UsuarioPagamento usuario = new UsuarioPagamento(this.customer, this.nome, this.cpf_cnpj);
		usuario.setUsuarioId(usuarioId);
		return usuario;
	}

}
