package project.spring.services;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Service;

import project.spring.entities.Boleto;
import project.spring.entities.Notificacao;
import project.spring.repository.BoletoRepository;
import project.spring.repository.NotificacaoRepository;

@Service
public class NotificacaoServices {
	
	@Autowired
	public NotificacaoRepository repository;
	
	@Autowired
	private BoletoRepository boletoRepository;
	
	public void gerenciadorNotificacoes (Boleto boleto) {
		
		var findBoleto = boletoRepository.findByCodigoBarras(boleto.getCodigoBarras());
		var n = repository.findByBoletoId(findBoleto.get().getId());
		if(n.isPresent()) {
			n.get().setStatus(boleto.getStatusBoleto().toString());
			repository.save(n.get());
			return;
		}
		
		
		
		Notificacao notificacao = new Notificacao(boleto.getStatusBoleto().toString() , findBoleto.get().getUsuario() , findBoleto.get());
		
		repository.save(notificacao);
	}
	
	public List<Notificacao>findAllNotificacaoByUsuarioId(JwtAuthenticationToken token) {
		return repository.findAllByUsuarioId(UUID.fromString(token.getName()));
	}
	
	
}
