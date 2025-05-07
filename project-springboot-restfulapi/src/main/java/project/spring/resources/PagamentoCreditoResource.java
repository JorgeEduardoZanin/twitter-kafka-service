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

import project.spring.dto.wrapper.PagamentoRequestControllerWrapper;
import project.spring.services.PagamentoCreditoService;

@RestController
@RequestMapping("/assinaturaCredito")
public class PagamentoCreditoResource {

	@Autowired
	private PagamentoCreditoService service;
	
	@PostMapping
	@PreAuthorize("hasAuthority('SCOPE_BASIC')")
	public ResponseEntity<String> pagamentoCredito(@RequestBody @Valid PagamentoRequestControllerWrapper request, JwtAuthenticationToken token){
			
		System.out.println("tenta executar" +request);
		
		
		
		
		String pagamento  = service.signature(request.cartaoRequest(), request.titularRequest(), token);
		
		return ResponseEntity.ok(pagamento); 
		
	}
	
}
