package project.spring.dto.response;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import project.spring.entities.Pagamento;

@JsonIgnoreProperties(ignoreUnknown = true)
public record PagamentoPixResponse(String id, String customer, String status, Long value, String pixQrCode, String qrCode, LocalDate dueDate, String billingType) {
	
	public Pagamento toEntity() {
		
		Pagamento pagamento = new Pagamento(this.customer, this.dueDate, this.value, this.billingType, this.status, this.qrCode);
		pagamento.setId(id);
		return pagamento;
	}
	
}
