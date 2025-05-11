package project.spring.util;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jakarta.persistence.EntityNotFoundException;
import project.spring.repository.UsuarioPagamentoRepository;

@Component
public class DataExpiracaoLogical {

	@Autowired
	private UsuarioPagamentoRepository repository;
	
	public void dataExpiracao(String usuarioId) {
		 var usuario = repository.findPagamentoNaoExpirado(usuarioId)
		            .map(u -> {
		                u.setExpiracaoAssinatura(
		                    u.getExpiracaoAssinatura().plusMonths(1)
		                );
		                return u;
		            })
		            .or(() -> repository.findById(usuarioId)
		                .map(u -> {
		                    u.setExpiracaoAssinatura(
		                        LocalDate.now().plusMonths(1)
		                    ); 
		                    return u;
		                }))
		            .orElseThrow(() ->
		                new EntityNotFoundException("Usuário não encontrado")
		            );
		
		repository.saveAndFlush(usuario);
	}
	
	public LocalDate dataExpiracaoNula() {
		return LocalDate.now().plusMonths(1);
	}
	
	public LocalDate adicionarExpiracaoMes(LocalDate date) {
		return date.plusMonths(1);
	}
	
	
	
}
