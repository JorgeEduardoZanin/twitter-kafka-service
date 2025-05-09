package project.spring.entities;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_usuario_pagamento")
public class UsuarioPagamento {

	@Id
	private String usuarioId;
	
	@Column(unique = true)
	private String customer;

	private String nome;
	
	private String cpf_cnpj;
	
	@OneToMany(mappedBy = "usuario" ,fetch = FetchType.LAZY)
	private List<Pagamento> pagamento;
	
	public UsuarioPagamento() {
	}

	public UsuarioPagamento(String customer, String nome, String cpf_cnpj) {
		this.customer = customer;
		this.nome = nome;
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

	public String getCustomer() {
		return customer;
	}

	public void setCustomer(String customer) {
		this.customer = customer;
	}

	public List<Pagamento> getPagamento() {
		return pagamento;
	}

	public void setPagamento(List<Pagamento> pagamento) {
		this.pagamento = pagamento;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
}
