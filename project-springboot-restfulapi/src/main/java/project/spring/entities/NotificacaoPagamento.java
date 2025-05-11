package project.spring.entities;


import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import project.spring.enums.StatusPagamento;

@Entity
@Table(name = "tb_notificacao_pagamento")
public class NotificacaoPagamento {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(unique = true)
	private String idPagamento;
	
	private StatusPagamento status;
	private LocalDate  dataExpiracaoAssinatura;
	private String chavePixString;
	private String tipoPagamento;
	private LocalDate dataExpiracaoPagamento;
	
	public NotificacaoPagamento() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getIdPagamento() {
		return idPagamento;
	}

	public void setIdPagamento(String idPagamento) {
		this.idPagamento = idPagamento;
	}

	public StatusPagamento getStatus() {
		return status;
	}

	public void setStatus(StatusPagamento status) {
		this.status = status;
	}

	public LocalDate getDataExpiracaoAssinatura() {
		return dataExpiracaoAssinatura;
	}

	public void setDataExpiracaoAssinatura(LocalDate dataExpiracaoAssinatura) {
		this.dataExpiracaoAssinatura = dataExpiracaoAssinatura;
	}

	public String getChavePixString() {
		return chavePixString;
	}

	public void setChavePixString(String chavePixString) {
		this.chavePixString = chavePixString;
	}

	public String getTipoPagamento() {
		return tipoPagamento;
	}

	public void setTipoPagamento(String tipoPagamento) {
		this.tipoPagamento = tipoPagamento;
	}

	public LocalDate getDataExpiracaoPagamento() {
		return dataExpiracaoPagamento;
	}

	public void setDataExpiracaoPagamento(LocalDate dataExpiracaoPagamento) {
		this.dataExpiracaoPagamento = dataExpiracaoPagamento;
	}
	
	
	
}
