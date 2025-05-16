package project.spring.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import project.spring.dto.response.NotificacaoResponse;
import project.spring.services.PagamentoPixService;

@RestController
@RequestMapping("/assinaturaPix")
public class PagamentoPixResource {

	@Autowired
	private PagamentoPixService service;
	
	@PostMapping
	public ResponseEntity<NotificacaoResponse> createPix(JwtAuthenticationToken token) throws InterruptedException{
		var notificacao = service.createPix(token);
		return ResponseEntity.status(HttpStatus.CREATED).body(notificacao);
	}
	
}
