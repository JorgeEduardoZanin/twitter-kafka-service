package project.spring.services;

import java.math.BigDecimal;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Service;


import project.spring.dto.request.CartaoCreditoRequest;
import project.spring.dto.request.IdentificadorApiPrincipalRequest;
import project.spring.dto.request.TitularCartaoCreditoRequest;
import project.spring.dto.request.UsuarioPagamentoRequest;
import project.spring.dto.response.NotificacaoResponse;
import project.spring.dto.wrapper.PagamentoRequest;
import project.spring.entities.NotificacaoPagamento;
import project.spring.enums.StatusPagamento;
import project.spring.repository.NotificacaoPagamentoRepository;
import project.spring.repository.UsuarioRepository;
import project.spring.services.kafka.producer.PagamentoCreditoProducer;

@Service
public class PagamentoCreditoService {

	@Value("${produto.assinatura.valor}")
    private BigDecimal assinaturaValor;
	
	@Autowired
	private PagamentoCreditoProducer producer;
	
	@Autowired
	NotificacaoService notificacaoService;
	
	@Autowired
	private UsuarioRepository repository;
	
	@Autowired
	private NotificacaoPagamentoRepository notificacaoPagamentoRepository;
	
	
	public NotificacaoResponse signature(CartaoCreditoRequest cartaoRequest, TitularCartaoCreditoRequest titularCartaoRequest, JwtAuthenticationToken token) throws InterruptedException {
		var usuario = repository.findById(UUID.fromString(token.getName())).get();
	
		UsuarioPagamentoRequest usuarioRequest = new UsuarioPagamentoRequest(usuario.getId().toString(), usuario.getCpf_cnpj(), usuario.getNome());
		
		NotificacaoPagamento notPagamento = new NotificacaoPagamento();
		notPagamento.setStatus(StatusPagamento.PENDING);
		notificacaoPagamentoRepository.save(notPagamento);

		
		PagamentoRequest request = new PagamentoRequest(cartaoRequest, titularCartaoRequest, assinaturaValor, usuarioRequest, new IdentificadorApiPrincipalRequest(notPagamento.getId()) );
	
		producer.enviarMensagem(request.toAvro());
		
		Thread.sleep(5000);
	
	
		return notificacaoService.getNotificacao(notPagamento.getId(), usuario);
		
	}
	
}
