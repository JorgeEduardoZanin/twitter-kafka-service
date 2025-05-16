package project.spring.services;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.spring.dto.request.UsuarioPagamentoRequest;
import project.spring.dto.wrapper.PagamentoCreditoWrapperRequest;
import project.spring.repository.PagamentoRepository;
import project.spring.repository.UsuarioPagamentoRepository;
import project.spring.services.pagamento.PagamentoCreditoApi;



@Service
public class PagamentoCreditoService {

	@Autowired
	private PagamentoCreditoApi pagamentoCredito;
	
	@Autowired
	private UsuarioPagamentoService usuarioPagamentoService;
	
	@Autowired
	private PagamentoRepository pagamentoRepository;
	
	@Autowired
	private UsuarioPagamentoRepository usuarioPagamentoRepository;
	
	public void createPagamentoCredito(PagamentoCreditoWrapperRequest wrapper) throws IOException {
		var customer = usuarioPagamentoRepository.findByUsuarioId(wrapper.usuario().usuarioId());
	
		if(customer.isEmpty() || wrapper.usuario().cpf_cnpj() != null) {
			UsuarioPagamentoRequest usuarioPagamentoRequest = new UsuarioPagamentoRequest(wrapper.usuario().usuarioId(), wrapper.usuario().cpf_cnpj(), wrapper.usuario().nome());
			var usuarioPagamento = usuarioPagamentoService.UsuarioPagamento(usuarioPagamentoRequest);
			var responseCredito = pagamentoCredito.createPagamento(wrapper.pagamentoCredito(), wrapper.titularCartao(), usuarioPagamento.customer(), wrapper.value());
			
			pagamentoRepository.save(responseCredito.toEntity(wrapper.usuario().usuarioId(), wrapper.identificadorApiPrincipal()));
			return;
		}
		
		var responseCredito = pagamentoCredito.createPagamento(wrapper.pagamentoCredito(), wrapper.titularCartao(), customer.get().getCustomer(), wrapper.value());
		pagamentoRepository.save(responseCredito.toEntity(wrapper.usuario().usuarioId(), wrapper.identificadorApiPrincipal()));
		return;
	}
	
			
}
