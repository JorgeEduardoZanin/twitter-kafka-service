package project.spring.dto.request;


public record PagamentoCreditoRequest(String nomeCartao,  String numeroCartao, String mesExpiracao, String anoExpiracao, String ccv) {

}
