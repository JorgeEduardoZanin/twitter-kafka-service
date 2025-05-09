package project.spring.services;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;
import project.spring.entities.Pagamento;
import project.spring.enums.StatusPagamento;
import project.spring.repository.PagamentoRepository;


@Service
public class NotificacaoWebhookPagamentoService {

	@Autowired
	private PagamentoRepository repository;	
	
	
	public void  notificacaoPagamento(Map<String, Object> body) {
		
		@SuppressWarnings("unchecked")
		Map<String, Object> payment = (Map<String, Object>) body.get("payment");
		
		StatusPagamento statusPagamento = StatusPagamento.valueOf(payment.get("status").toString());
		
		var pag = repository.findById(payment.get("id").toString())
				.orElseThrow(() -> new EntityNotFoundException("Pagamento não encontrado para ID " + payment.get("id").toString()));
		
		
		Pagamento pagamento = new Pagamento();
		pagamento.setId(pag.getId());
		pagamento.setStatus(statusPagamento.name());
		
		
		
	}
	
	
}
