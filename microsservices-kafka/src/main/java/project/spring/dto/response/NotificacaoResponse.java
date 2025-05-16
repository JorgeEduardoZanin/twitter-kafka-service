package project.spring.dto.response;

import java.time.LocalDate;

import project.spring.avro.NotificacaoPagamento;
import project.spring.entities.Pagamento;
import project.spring.entities.UsuarioPagamento;
import project.spring.enums.StatusPagamento;

public record NotificacaoResponse(Long id, String usuarioId, StatusPagamento status, String billingType,
		LocalDate dataExpiracaoPagamento, String chavePix, String pagamentoId, LocalDate dataExpiracaoAssinatura) {

	public static NotificacaoResponse toResponse(Pagamento pagamento, UsuarioPagamento usuarioPagamento) {
		return new NotificacaoResponse(pagamento.getIdApiPrincipal() ,pagamento.getUsuario().getUsuarioId(), StatusPagamento.valueOf(pagamento.getStatus().toString()),
				pagamento.getBillingType(), pagamento.getDueDate(), pagamento.getChavePix(),
				pagamento.getId(), usuarioPagamento.getExpiracaoAssinatura());
	}
	
	public static NotificacaoPagamento toAvro(Pagamento pagamento, UsuarioPagamento usuarioPagamento) {
		if(usuarioPagamento.getExpiracaoAssinatura()==null) {
			return NotificacaoPagamento.newBuilder()
					.setBillingType(pagamento.getBillingType())
					.setId(pagamento.getIdApiPrincipal())
					.setChavePix(pagamento.getChavePix())
					.setDataExpiracaoAssinatura(usuarioPagamento.getExpiracaoAssinatura() != null ? usuarioPagamento.getExpiracaoAssinatura().toString() : null)
					.setDataExpiracaoPagamento(pagamento.getDueDate().toString())
					.setStatus(pagamento.getStatus().toString())
					.setPaymentId(pagamento.getId())
					.setUsuarioId(usuarioPagamento.getUsuarioId())
					.build();
		}
		
		return NotificacaoPagamento.newBuilder()
				.setBillingType(pagamento.getBillingType())
				.setId(pagamento.getIdApiPrincipal())
				.setChavePix(pagamento.getChavePix())
				.setDataExpiracaoAssinatura(usuarioPagamento.getExpiracaoAssinatura().toString())
				.setDataExpiracaoPagamento(pagamento.getDueDate().toString())
				.setStatus(pagamento.getStatus().toString())
				.setPaymentId(pagamento.getId())
				.setUsuarioId(usuarioPagamento.getUsuarioId())
				.build();
	}
	
	
}
