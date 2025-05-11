package project.spring.resources;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import project.spring.dto.response.NotificacaoResponse;
import project.spring.services.PagamentoService;

@RestController
@RequestMapping("/pagamento")
public class PagamentoResource {

	@Autowired
	private PagamentoService service;
	
	@GetMapping
	public ResponseEntity<NotificacaoResponse> getPagamentoId(JwtAuthenticationToken token){
		var notificacao = service.getPagamentoCredito(token);
		return ResponseEntity.ok(notificacao);
	}
	
}
