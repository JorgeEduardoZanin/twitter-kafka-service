package project.spring.services;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import project.spring.dto.request.PagamentoPixRequest;
import project.spring.dto.request.UsuarioPagamentoRequest;
import project.spring.dto.response.NotificacaoResponse;
import project.spring.dto.response.PagamentoPixResponse;
import project.spring.repository.PagamentoRepository;
import project.spring.repository.UsuarioPagamentoRepository;
import project.spring.services.kafka.producer.NotificacaoPagamentoProducer;
import project.spring.services.pagamento.PagamentoPixApi;

@Service
public class PagamentoPixService {
	
	@Autowired
	private PagamentoPixApi pagamentoPix;
	
	@Autowired
	private UsuarioPagamentoService usuarioPagamentoService;
	
	@Autowired
	private PagamentoRepository pagamentoRepository;
	
	@Autowired
	private UsuarioPagamentoRepository usuarioPagamentoRepository;
	

	
	public void createPagamentoPix(PagamentoPixRequest request) throws IOException {
		var customer = usuarioPagamentoRepository.findByUsuarioId(request.usuarioId());
		
		if(customer.isEmpty() && request.cpf_cnpj() != null) {
			UsuarioPagamentoRequest usuarioPagamentoRequest = new UsuarioPagamentoRequest(request.usuarioId(), request.cpf_cnpj(), request.cpf_cnpj());
			var usuarioPagamento = usuarioPagamentoService.UsuarioPagamento(usuarioPagamentoRequest);
			
			var pagamento = pagamentoPix.createPayment(request, usuarioPagamento.customer());
			var keyPix = pagamentoPix.getPixKey(pagamento.id());
			
			PagamentoPixResponse response = PagamentoPixResponse.toPagamentoPixResponse(pagamento, keyPix);

			pagamentoRepository.save(response.toEntity(request.usuarioId(), request.identificadorApi()));
			
		
		}
		
		var pagamento = pagamentoPix.createPayment(request, customer.get().getCustomer());
		var keyPix = pagamentoPix.getPixKey(pagamento.id());
		
		PagamentoPixResponse response = PagamentoPixResponse.toPagamentoPixResponse(pagamento, keyPix);
	
		pagamentoRepository.save(response.toEntity(request.usuarioId(),request.identificadorApi()));

	}
	
}
