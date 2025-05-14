package project.spring.services;

import java.time.LocalDate;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import project.spring.avro.NotificacaoPagamento;
import project.spring.dto.response.NotificacaoResponse;
import project.spring.entities.Usuario;
import project.spring.enums.StatusPagamento;
import project.spring.repository.NotificacaoPagamentoRepository;
import project.spring.repository.UsuarioRepository;

@Service
public class NotificacaoService {
	
	@Autowired
	private NotificacaoPagamentoRepository repository;
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private NotificacaoPagamentoRepository notificacaoPagamentoRepository;
	
	@PersistenceContext
	private EntityManager manager;
	
	
	public void createNotificacao(NotificacaoPagamento notificacao) {
		
		var usuario = usuarioRepository.findById(UUID.fromString(notificacao.getUsuarioId().toString()));
	
		if(StatusPagamento.valueOf(notificacao.getStatus().toString()) == StatusPagamento.CONFIRMED) {
			usuario.get().setDataExpiracaoAssinatura(LocalDate.parse(notificacao.getDataExpiracaoAssinatura()));
			usuario.get().setAssinante(true);
		}
		var notificacaoEntity = NotificacaoResponse.toEntity(notificacao, usuario.get());
		
		usuarioRepository.saveAndFlush(usuario.get());
		repository.saveAndFlush(notificacaoEntity);
	}
	
	@Transactional
	public NotificacaoResponse getNotificacao(Long id, Usuario usuario) {
		
		var notificacaoPagamento = notificacaoPagamentoRepository.findById(id).get();
		manager.refresh(notificacaoPagamento);
		return NotificacaoResponse.toResponse(notificacaoPagamento, usuario);
	}
	
	
}
