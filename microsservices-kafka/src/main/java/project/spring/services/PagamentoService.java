package project.spring.services;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import project.spring.dto.request.PagamentoPixRequest;
import project.spring.dto.request.UsuarioPagamentoRequest;
import project.spring.dto.response.NotificacaoResponse;
import project.spring.dto.response.PagamentoPixResponse;
import project.spring.dto.wrapper.PagamentoCreditoWrapperRequest;
import project.spring.repository.PagamentoRepository;
import project.spring.repository.UsuarioPagamentoRepository;
import project.spring.services.pagamento.PagamentoCreditoApi;
import project.spring.services.pagamento.PagamentoPixApi;


@Service
public class PagamentoService {

	@Autowired
	private PagamentoPixApi pagamentoPix;
	
	@Autowired
	private PagamentoCreditoApi pagamentoCredito;
	
	@Autowired
	private UsuarioPagamentoService usuarioPagamentoService;
	
	@Autowired
	private PagamentoRepository pagamentoRepository;
	
	@Autowired
	private UsuarioPagamentoRepository usuarioPagamentoRepository;
	
	public PagamentoPixResponse createPagamentoPix(PagamentoPixRequest request) throws IOException {
		var customer = usuarioPagamentoRepository.findByUsuarioId("cus_000006682352");
		
		if(customer.isEmpty()) {
			UsuarioPagamentoRequest usuarioPagamentoRequest = new UsuarioPagamentoRequest(null,"23418115403", "Juninho Tornado");
			var usuarioPagamento = usuarioPagamentoService.UsuarioPagamento(usuarioPagamentoRequest);
			var pagamento = pagamentoPix.createPayment(request, usuarioPagamento.customer());
			var keyPix = pagamentoPix.getPixKey(pagamento.id());
			
			PagamentoPixResponse response = new PagamentoPixResponse(pagamento.id(), pagamento.customer(),
					pagamento.status(), pagamento.value(), pagamento.dueDate(), 
					pagamento.billingType(), keyPix.chavePix());
			
			
			pagamentoRepository.save(response.toEntity(null));
			return response;
		}
		
		var pagamento = pagamentoPix.createPayment(request, customer.get().getCustomer());
		var keyPix = pagamentoPix.getPixKey(pagamento.id());
		
		PagamentoPixResponse response = new PagamentoPixResponse(pagamento.id(), pagamento.customer(),
				pagamento.status(), pagamento.value(), pagamento.dueDate(), 
				pagamento.billingType(), keyPix.chavePix());
	
		System.out.println(pagamento);
		pagamentoRepository.save(response.toEntity(null));
		return response;
		
	}
	
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
	
	public NotificacaoResponse getPagamentoCredito(Long identificadorApiPrincipal) {
		var pagamento = pagamentoRepository.findByIdApiPrincipal(identificadorApiPrincipal);
		var usuarioPagamento = usuarioPagamentoRepository.findByCustomer(pagamento.get().getCustomer());
		return NotificacaoResponse.toResponse(pagamento.get(), usuarioPagamento.get());
	}
			
}
