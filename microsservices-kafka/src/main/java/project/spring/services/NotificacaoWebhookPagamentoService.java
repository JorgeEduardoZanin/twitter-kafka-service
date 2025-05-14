package project.spring.services;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;
import project.spring.dto.response.NotificacaoResponse;
import project.spring.entities.Pagamento;
import project.spring.entities.UsuarioPagamento;
import project.spring.enums.StatusEventoPagamento;
import project.spring.enums.StatusPagamento;
import project.spring.repository.PagamentoRepository;
import project.spring.repository.UsuarioPagamentoRepository;
import project.spring.services.kafka.NotificacaoPagamentoProducer;
import project.spring.util.DataExpiracaoLogical;


@Service
public class NotificacaoWebhookPagamentoService {

	@Autowired
	private PagamentoRepository repository;	
	
	@Autowired
	private UsuarioPagamentoRepository usuarioPagamentoRepository;	
	
	@Autowired
	private DataExpiracaoLogical logicalExpiracao;
	
	@Autowired
	private NotificacaoPagamentoProducer producer;
	
	
	public void  notificacaoPagamento(Map<String, Object> body) {
		
		String asaasEvent = (String) body.get("event");
		@SuppressWarnings("unchecked")
		Map<String, Object> payment = (Map<String, Object>) body.get("payment");
		
		StatusPagamento statusPagamento = StatusPagamento.valueOf(payment.get("status").toString());
		StatusEventoPagamento statusEvento = StatusEventoPagamento.valueOf(asaasEvent);
		
		System.out.println(payment.get("status").toString());
		var pag = repository.findById(payment.get("id").toString())
				.orElseThrow(() -> new EntityNotFoundException("Pagamento não encontrado para ID " + payment.get("id").toString()));
		
		
		
		pag.setId(pag.getId());
		pag.setStatus(statusPagamento.name());
		
		repository.saveAndFlush(pag);
		
		if(statusPagamento == StatusPagamento.CONFIRMED && statusEvento == StatusEventoPagamento.PAYMENT_CONFIRMED) {
			logicalExpiracao.dataExpiracao(pag.getUsuario().getUsuarioId());
			}
		var usuario = usuarioPagamentoRepository.findByCustomer(pag.getCustomer());
		producer.enviarMensagem(NotificacaoResponse.toAvro(pag, usuario.get()));
	}

	
}
