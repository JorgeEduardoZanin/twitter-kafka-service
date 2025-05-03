package project.spring.dto.response;

import java.time.LocalDate;
import java.util.UUID;

import project.spring.entities.Pagamento;

public record PagamentoPixResponse(String id, UUID customer, String status, Long value, String pixQrCode, String qrCode, LocalDate dueDate, String billingType) {
	
	public Pagamento toEntity(PagamentoPixResponse response) {
		
		Pagamento pagamento = new Pagamento(response.customer, response.dueDate, response.value, response.billingType, response.status, response.qrCode);
		pagamento.setId(id);
		return pagamento;
	}
	
}
