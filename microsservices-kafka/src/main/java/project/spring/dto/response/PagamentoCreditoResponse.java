package project.spring.dto.response;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import project.spring.entities.Pagamento;
import project.spring.entities.UsuarioPagamento;

@JsonIgnoreProperties(ignoreUnknown = true)
public record PagamentoCreditoResponse(@JsonProperty("id") String id, @JsonProperty("customer") String customer,
		@JsonProperty("status") String status,  @JsonProperty("value") BigDecimal value,
		@JsonProperty("dueDate") @JsonFormat(pattern = "yyyy-MM-dd") LocalDate dueDate,
		@JsonProperty("billingType") String billingType) {

	
	public Pagamento toEntity(String usuarioId, Long idApiPrincipal) {
		Pagamento pagamento = new Pagamento(this.customer, this.dueDate, this.value, this.billingType, this.status);
		UsuarioPagamento usuario = new UsuarioPagamento();
		usuario.setUsuarioId(usuarioId);
		pagamento.setUsuario(usuario);
		pagamento.setId(this.id);
		pagamento.setIdApiPrincipal(idApiPrincipal);
		return pagamento;
	
	}
	
}
