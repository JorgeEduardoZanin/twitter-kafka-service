package project.spring.utils;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import project.spring.entities.NotificacaoPagamento;
import project.spring.enums.StatusPagamento;
import project.spring.repository.NotificacaoPagamentoRepository;

@Component
public class ProcessamentoRespostaNotificacao {

	@Autowired
	private NotificacaoPagamentoRepository notificacaoPagamentoRepository;
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@Transactional
	public Optional<NotificacaoPagamento> toNotificacaoPagamento(Long id) throws InterruptedException{
		var notificacaoPagamento = notificacaoPagamentoRepository.findById(id);
		for(int i =1; i <=5 ; i++) {
			
			notificacaoPagamento = notificacaoPagamentoRepository.findById(id);
			if(notificacaoPagamento.isPresent() && notificacaoPagamento.get().getStatus() != StatusPagamento.PENDING) {
				return notificacaoPagamento;
			}
			entityManager.refresh(notificacaoPagamento.get());
			Thread.sleep(2500);
		}
		return notificacaoPagamento;
	
	}
	
}
