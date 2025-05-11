package project.spring.dto.response;

import java.time.LocalDate;

import project.spring.entities.Pagamento;
import project.spring.enums.StatusPagamento;

public record NotificacaoResponse(String usuarioId, StatusPagamento status, String billingType, LocalDate dataExpiracaoPagamento, String chavePix, String pagamentoId) {

	public static NotificacaoResponse toResponse(Pagamento pagamento) {
		return new NotificacaoResponse(pagamento.getUsuario().getUsuarioId(), StatusPagamento.valueOf(pagamento.getStatus().toString()),
				pagamento.getBillingType(), pagamento.getDueDate(), pagamento.getChavePix(),
				pagamento.getId());
	}
	
	
}
