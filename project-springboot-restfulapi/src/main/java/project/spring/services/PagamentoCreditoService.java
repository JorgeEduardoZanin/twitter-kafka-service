package project.spring.services;

import java.math.BigDecimal;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Service;


import project.spring.dto.request.CartaoCreditoRequest;
import project.spring.dto.request.TitularCartaoCreditoRequest;
import project.spring.dto.response.NotificacaoResponse;
import project.spring.dto.wrapper.PagamentoRequest;


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
	private UsuarioServices usuarioService;
	
	
	public NotificacaoResponse signature(CartaoCreditoRequest cartaoRequest, TitularCartaoCreditoRequest titularCartaoRequest, JwtAuthenticationToken token) throws InterruptedException {
		var usuario = repository.findById(UUID.fromString(token.getName())).get();
		
		var usuarioRequest = usuarioService.verificaPrimeiraCobranca(usuario);

		Long identificadorApi = notificacaoService.createNotificacao();

		PagamentoRequest request = new PagamentoRequest(cartaoRequest, titularCartaoRequest, assinaturaValor, usuarioRequest, identificadorApi);
	
		producer.enviarMensagem(request.toAvro());

		return notificacaoService.getNotificacao(identificadorApi, usuario);
		
	}
	
}
