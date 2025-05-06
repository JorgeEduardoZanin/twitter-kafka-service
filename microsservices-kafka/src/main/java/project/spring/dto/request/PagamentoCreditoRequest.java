package project.spring.dto.request;


public record PagamentoCreditoRequest(String value, String nomeCartao,  String numeroCartao, String mesExpiracao, String anoExpiracao, String ccv) {

}
