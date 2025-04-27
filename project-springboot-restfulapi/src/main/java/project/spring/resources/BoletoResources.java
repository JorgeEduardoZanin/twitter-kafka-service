package project.spring.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import project.spring.dto.request.BoletoRequest;
import project.spring.dto.response.BoletoResponse;
import project.spring.services.BoletoServices;

@RestController
@RequestMapping(value = "/assinatura")
public class BoletoResources {

	@Autowired
	private BoletoServices services;
	
	@PostMapping
	@PreAuthorize("hasAuthority('SCOPE_BASIC')")
	public ResponseEntity<BoletoResponse> salvar(@Valid @RequestBody BoletoRequest boletoRequest, JwtAuthenticationToken token){
		var boleto = services.saveBoleto(boletoRequest, token);
		return ResponseEntity.status(201).body(boleto);
	}
	
}
