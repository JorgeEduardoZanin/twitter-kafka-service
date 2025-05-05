package project.spring.services;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import project.spring.dto.request.PagamentoPixRequest;
import project.spring.dto.request.UsuarioPagamentoRequest;
import project.spring.dto.response.PagamentoPixResponse;
import project.spring.repository.PagamentoRepository;
import project.spring.repository.UsuarioPagamentoRepository;
import project.spring.services.pagamento.PagamentoPixApi;


@Service
public class PagamentoService {

	@Autowired
	private PagamentoPixApi pagamentoPix;
	
	@Autowired
	private UsuarioPagamentoService usuarioPagamentoService;
	
	@Autowired
	private PagamentoRepository pagamentoRepository;
	
	@Autowired
	private UsuarioPagamentoRepository usuarioPagamentoRepository;
	

	public PagamentoPixResponse createPagamentoPix(PagamentoPixRequest request) throws IOException {
		var customer = usuarioPagamentoRepository.findByCustomer("cus_000006682352");
		
		if(customer.isEmpty()) {
			UsuarioPagamentoRequest usuarioPagamentoRequest = new UsuarioPagamentoRequest("23418115403", "Juninho Tornado");
			var usuarioPagamento = usuarioPagamentoService.UsuarioPagamento(usuarioPagamentoRequest);
			var pagamento = pagamentoPix.createPayment(request, usuarioPagamento.customer());
			pagamentoRepository.save(pagamento.toEntity());
			return pagamento;
		}
		
		var pagamento = pagamentoPix.createPayment(request, customer.get().getCustomer());
		System.out.println(pagamento);
		pagamentoRepository.save(pagamento.toEntity());
		return pagamento;
		
	}
			
}
