package project.spring.dto.request;

import project.spring.avro.PagamentoRequest;

public record PagamentoCreditoRequest(String nomeCartao,  String numeroCartao, String mesExpiracao, String anoExpiracao, String ccv) {


	public static PagamentoCreditoRequest toCreditoRequest(PagamentoRequest pagamento) {
		return new PagamentoCreditoRequest(pagamento.getCartao().getNomeCartao().toString(), pagamento.getCartao().getNumeroCartao().toString(),
		pagamento.getCartao().getMesExpiracao().toString(), pagamento.getCartao().getAnoExpiracao().toString(), pagamento.getCartao().getCcv().toString());
	}
}
