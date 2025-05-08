package project.spring.dto.request;

import project.spring.avro.PagamentoRequest;

public record TitularCartaoCreditoRequest(String nomeTitularCartao, String email, String cpf_cnpj, String codigoPostal, String numeroEndereco, String telefone) {

	public static TitularCartaoCreditoRequest toTitularCartaoRequest(PagamentoRequest pagamento) {
		return new TitularCartaoCreditoRequest(pagamento.getTitular().getNomeTitularCartao().toString(), pagamento.getTitular().getEmail().toString(),
				pagamento.getTitular().getCpfCnpjTitular().toString(), pagamento.getTitular().getCodigoPostal().toString(),
				pagamento.getTitular().getNumeroEndereco().toString(), pagamento.getTitular().getTelefone().toString());
	}
	
}
