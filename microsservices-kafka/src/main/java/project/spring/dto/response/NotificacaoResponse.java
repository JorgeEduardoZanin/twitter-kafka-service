package project.spring.dto.response;

import java.time.LocalDate;

import project.spring.avro.NotificacaoPagamento;
import project.spring.dto.request.PagamentoPixRequest;
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
	
	public static NotificacaoResponse pixToResponse(PagamentoPixRequest usuario, PagamentoPixResponse pagamentoPix) {
		return new NotificacaoResponse(usuario.identificadorApi() ,usuario.usuarioId() , StatusPagamento.valueOf(pagamentoPix.status()),
				pagamentoPix.billingType(), pagamentoPix.dueDate(), pagamentoPix.chavePix(),
				pagamentoPix.id() , null);
	}
	
	public static NotificacaoPagamento creditoToAvro(Pagamento pagamento, UsuarioPagamento usuarioPagamento) {
	
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
	
	public static NotificacaoPagamento pixToAvro(NotificacaoResponse response) {
		return NotificacaoPagamento.newBuilder()
				.setBillingType(response.billingType)
				.setChavePix(response.chavePix)
				.setDataExpiracaoAssinatura(null)
				.setDataExpiracaoPagamento(response.dataExpiracaoPagamento.toString())
				.setId(response.id)
				.setPaymentId(response.pagamentoId)
				.setStatus(response.status.toString())
				.setUsuarioId(response.usuarioId)
				.build();
	}
	
}
