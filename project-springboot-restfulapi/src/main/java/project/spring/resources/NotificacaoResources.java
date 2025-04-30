package project.spring.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import project.spring.entities.Notificacao;
import project.spring.services.NotificacaoServices;

@RestController
@RequestMapping("/notificacao")
public class NotificacaoResources {
   
	@Autowired
	private NotificacaoServices service;

	@GetMapping
	@PreAuthorize("hasAuthority('SCOPE_BASIC')")
	public ResponseEntity<List<Notificacao>> listAllNotificacaoByUsuarioId(JwtAuthenticationToken token){
		List<Notificacao> listAll = service.findAllNotificacaoByUsuarioId(token);
		return ResponseEntity.status(HttpStatus.OK).body(listAll);
	}
}
