package project.spring.services;

import java.time.LocalDate;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import project.spring.avro.NotificacaoPagamento;
import project.spring.dto.response.NotificacaoResponse;
import project.spring.entities.Usuario;
import project.spring.enums.StatusPagamento;
import project.spring.repository.NotificacaoPagamentoRepository;
import project.spring.repository.UsuarioRepository;
import project.spring.utils.ProcessamentoRespostaNotificacao;

@Service
public class NotificacaoService {
	
	@Autowired
	private NotificacaoPagamentoRepository repository;
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private ProcessamentoRespostaNotificacao processaNotificacao;
	
	public Long createNotificacao() {
		project.spring.entities.NotificacaoPagamento notificacao = project.spring.entities.NotificacaoPagamento.newNotificacao();
		repository.saveAndFlush(notificacao);
		return notificacao.getId();
	}
	
	public void updateNotificacao(NotificacaoPagamento notificacao) {
		
		var usuario = usuarioRepository.findById(UUID.fromString(notificacao.getUsuarioId().toString()));
	
		usuario.get().usuarioAssinatura(StatusPagamento.valueOf(notificacao.getStatus().toString()),
				notificacao.getDataExpiracaoAssinatura()!=null ? LocalDate.parse(notificacao.getDataExpiracaoAssinatura()):null);
		
		var notificacaoEntity = NotificacaoResponse.toEntity(notificacao, usuario.get());
		
		usuarioRepository.saveAndFlush(usuario.get());
		repository.saveAndFlush(notificacaoEntity);
	}
	

	public NotificacaoResponse getNotificacao(Long id, Usuario usuario) throws InterruptedException {
		
		var notificacaoPagamento = processaNotificacao.toNotificacaoPagamento(id);
		
		return NotificacaoResponse.toResponse(notificacaoPagamento.get(), usuario);
	}
	
	
}
