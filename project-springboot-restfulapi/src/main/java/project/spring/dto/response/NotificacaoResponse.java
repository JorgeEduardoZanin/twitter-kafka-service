package project.spring.dto.response;

import java.time.LocalDate;
import java.util.UUID;

import project.spring.entities.NotificacaoPagamento;
import project.spring.entities.Usuario;
import project.spring.enums.StatusPagamento;


public record NotificacaoResponse(Long id, UUID usuarioId, StatusPagamento status, String billingType,
		LocalDate dataExpiracaoPagamento, String chavePix, String pagamentoId, LocalDate dataExpiracaoAssinatura) {

	
	public static NotificacaoResponse toResponse(NotificacaoPagamento notificacao, Usuario usuario) {
		return new NotificacaoResponse(notificacao.getId(), usuario.getId(), notificacao.getStatus(), notificacao.getTipoPagamento(),
				notificacao.getDataExpiracaoPagamento(), notificacao.getChavePix(), notificacao.getIdPagamento(),
				notificacao.getDataExpiracaoAssinatura());
	}
	
	public static NotificacaoPagamento toEntity(project.spring.avro.NotificacaoPagamento notificacao, Usuario usuario) {
		
		
		
		return new NotificacaoPagamento(notificacao.getId(),
				notificacao.getPaymentId().toString(),
				StatusPagamento.valueOf(notificacao.getStatus().toString()),
				LocalDate.parse(notificacao.getDataExpiracaoAssinatura()),
				notificacao.getChavePix() != null ? notificacao.getChavePix().toString() : "",
				notificacao.getBillingType().toString(),
				LocalDate.parse(notificacao.getDataExpiracaoPagamento()), 
				usuario);
	}
	
}	