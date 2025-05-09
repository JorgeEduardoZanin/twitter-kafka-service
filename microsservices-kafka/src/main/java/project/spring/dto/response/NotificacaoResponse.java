package project.spring.dto.response;

import java.util.Map;

import project.spring.avro.NotificacaoPagamento;

public record NotificacaoResponse(String usuarioId, Map<String, Object>  payment) {

	public NotificacaoPagamento toAvro() {
		return NotificacaoPagamento.newBuilder()
				.setUsuarioId(usuarioId)
				.setBillingType(payment.get("billingType").toString())
				.setDueDate(payment.get("dueDate").toString())
				.setId(payment.get("id").toString())
				.setStatus(payment.get("status").toString())
				.build();			
	}
	
}
