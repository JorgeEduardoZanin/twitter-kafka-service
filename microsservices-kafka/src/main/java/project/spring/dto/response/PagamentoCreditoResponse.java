package project.spring.dto.response;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import project.spring.entities.Pagamento;

@JsonIgnoreProperties(ignoreUnknown = true)
public record PagamentoCreditoResponse(@JsonProperty("id") String id, @JsonProperty("customer") String customer,
		@JsonProperty("status") String status,  @JsonProperty("value") Long value,
		@JsonProperty("dueDate") @JsonFormat(pattern = "yyyy-MM-dd") LocalDate dueDate,
		@JsonProperty("billingType") String billingType) {

	
	public Pagamento toEntity() {
		Pagamento pagamento = new Pagamento(this.customer, this.dueDate, this.value, this.billingType, this.status);
		pagamento.setId(this.id);
		return pagamento;
	
	}
	
}
