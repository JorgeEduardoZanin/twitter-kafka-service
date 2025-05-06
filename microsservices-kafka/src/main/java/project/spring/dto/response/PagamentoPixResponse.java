package project.spring.dto.response;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import project.spring.entities.Pagamento;


public record PagamentoPixResponse(String id, String customer,
			String status, Long value, @JsonFormat(pattern = "yyyy-MM-dd") LocalDate dueDate, String billingType, String chavePix) {

	public PagamentoPixResponse toPagamentoPixResponse(PixResponse response, KeyPixResponse key) {
		return new PagamentoPixResponse(response.id(), response.customer(), response.status(), response.value(), response.dueDate(),
				   response.billingType(), key.chavePix());
	}
	
	public Pagamento toEntity() {
		
		Pagamento pagamento = new Pagamento(this.customer, this.dueDate, this.value, this.billingType, this.status);
		pagamento.setChavePix(this.chavePix);
		pagamento.setId(this.id);
		return pagamento;
	}
	
}
