package project.spring.services;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import project.spring.dto.request.PagamentoPixRequest;
import project.spring.dto.response.PagamentoPixResponse;
import project.spring.repository.PagamentoRepository;
import project.spring.services.pagamento.PagamentoPixApi;

@Service
public class PagamentoService {

	@Autowired
	private PagamentoPixApi pagamentoPix;
	
	@Autowired
	private PagamentoRepository pagamentoRepository;
	
	
	public PagamentoPixResponse createPagamentoPix(PagamentoPixRequest request) throws IOException {
		var pagamento = pagamentoPix.createPayment(request);
		System.out.println(pagamento);
		pagamentoRepository.save(pagamento.toEntity());
		return pagamento;
		
	}
	
}
