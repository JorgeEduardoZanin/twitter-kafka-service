package project.spring.entities;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_usuario_pagamento")
public class UsuarioPagamento {

	@Id
	public String usuarioId;
	
	private String cpf_cnpj;
	
	@OneToMany(mappedBy = "usuario" ,fetch = FetchType.LAZY)
	private List<Pagamento> pagamento;
	
	public UsuarioPagamento() {
	}

	public UsuarioPagamento( String cpf_cnpj) {
		this.cpf_cnpj = cpf_cnpj;
	}
	
	public String getUsuarioId() {
		return usuarioId;
	}



	public void setUsuarioId(String usuarioId) {
		this.usuarioId = usuarioId;
	}



	public String getCpf_cnpj() {
		return cpf_cnpj;
	}



	public void setCpf_cnpj(String cpf_cnpj) {
		this.cpf_cnpj = cpf_cnpj;
	}



	

	
	
	
	
}
