package project.spring.services;

import java.math.BigDecimal;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Service;

import project.spring.dto.response.NotificacaoResponse;
import project.spring.dto.wrapper.PagamentoPixWrapper;
import project.spring.repository.UsuarioRepository;
import project.spring.services.kafka.producer.PagamentoPixProducer;

@Service
public class PagamentoPixService {

	@Value("${produto.assinatura.valor}")
    private BigDecimal assinaturaValor;
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private PagamentoPixProducer pixProducer;
	
	@Autowired
	private NotificacaoService notificacaoService;
	
	@Autowired
	private UsuarioServices usuarioServices;
	
	public NotificacaoResponse createPix(JwtAuthenticationToken token) throws InterruptedException {
		var usuario = usuarioRepository.findById(UUID.fromString(token.getName()));
		
		var usuarioRequest = usuarioServices.verificaPrimeiraCobranca(usuario.get());
		
		Long indetificadorApi = notificacaoService.createNotificacao();
			
		pixProducer.enviarMensagem(new PagamentoPixWrapper(indetificadorApi, assinaturaValor, usuarioRequest).toAvro());
		
		return notificacaoService.getNotificacao(indetificadorApi, usuario.get());
	}
	
}
