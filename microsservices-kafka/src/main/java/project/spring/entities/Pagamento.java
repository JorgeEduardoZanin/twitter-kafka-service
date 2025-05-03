package project.spring.entities;

import java.time.LocalDate;
import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_pagamento")
public class Pagamento {

	@Id
	private String id;
	
	private UUID customer;
	
	private LocalDate dueDate;
	private Long value;
	private String billingType;
	private String status;
	private String pixQrCode;
	
	public Pagamento() {

	}

	

	public Pagamento(UUID customer, LocalDate dueDate, Long value, String billingType, String status, String pixQrCode) {
		this.customer = customer;
		this.dueDate = dueDate;
		this.value = value;
		this.billingType = billingType;
		this.status = status;
		this.pixQrCode = pixQrCode;
	}



	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public UUID getCustomer() {
		return customer;
	}

	public void setCustomer(UUID customer) {
		this.customer = customer;
	}

	public LocalDate getDueDate() {
		return dueDate;
	}

	public void setDueDate(LocalDate dueDate) {
		this.dueDate = dueDate;
	}

	public Long getValue() {
		return value;
	}

	public void setValue(Long value) {
		this.value = value;
	}

	public String getBillingType() {
		return billingType;
	}

	public void setBillingType(String billingType) {
		this.billingType = billingType;
	}



	public String getStatus() {
		return status;
	}



	public void setStatus(String status) {
		this.status = status;
	}



	public String getPixQrCode() {
		return pixQrCode;
	}



	public void setPixQrCode(String pixQrCode) {
		this.pixQrCode = pixQrCode;
	}
	
	
	
	
}
