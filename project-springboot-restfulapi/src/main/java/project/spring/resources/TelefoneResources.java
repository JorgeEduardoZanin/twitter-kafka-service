package project.spring.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import project.spring.entities.Telefone;
import project.spring.services.TelefoneServices;

@RestController
@RequestMapping(value = "/usuario/telefone")
public class TelefoneResources {

	@Autowired
	private TelefoneServices services;
	/*
	 *
	 * 
	 CONTROLADORES DO TIPO GET 
	 *
	 *
	 */
	@GetMapping
	@PreAuthorize("hasAuthority('SCOPE_BASIC')")
	public ResponseEntity<List<Telefone>> findAll(JwtAuthenticationToken token){
		return ResponseEntity.status(HttpStatus.OK).body(services.findAll(token));
	}
	
	@GetMapping(value = "/{idTel}")
	@PreAuthorize("hasAuthority('SCOPE_BASIC')")
	public ResponseEntity<Telefone> findById(@PathVariable Long idTel,  JwtAuthenticationToken token){
		return ResponseEntity.status(HttpStatus.OK).body(services.findById(idTel, token));
		
	}
	
	@GetMapping(value = "/numero/{numero}")
	@PreAuthorize("hasAuthority('SCOPE_BASIC')")
	public ResponseEntity<List<Telefone>> findAllByNumero(@PathVariable String numero, JwtAuthenticationToken token){
		return ResponseEntity.status(HttpStatus.OK).body(services.findByNumero(numero, token));
	}
	/*
	 *
	 * 
	 CONTROLADORES DO TIPO POST
	 *
	 *
	 */
	@PostMapping
	@PreAuthorize("hasAuthority('SCOPE_BASIC')")
	public ResponseEntity<Telefone> save(JwtAuthenticationToken token, @RequestBody Telefone telefone){
		return ResponseEntity.status(HttpStatus.CREATED).body(services.saveTelefone(telefone, token));
	}
	/*
	 *
	 * 
	 CONTROLADORES DO TIPO PUT
	 *
	 *
	 */
	@PutMapping(value = "/{idTel}")
	@PreAuthorize("hasAuthority('SCOPE_BASIC')")
	public ResponseEntity<Telefone> updateTelefone(JwtAuthenticationToken token, @PathVariable Long idTel, @RequestBody Telefone telefone) {
		return ResponseEntity.status(HttpStatus.OK).body(services.updateTelefone(telefone, idTel, token));
	}
	/*
	 *
	 * 
	 CONTROLADORES DO TIPO DELETE
	 *
	 *
	 */
	@DeleteMapping(value = "/{idTel}")
	@PreAuthorize("hasAuthority('SCOPE_BASIC')")
	public ResponseEntity<String> deletaTelefone(@PathVariable Long idTel){
		return ResponseEntity.status(HttpStatus.OK).body(services.deleteTelefoneById(idTel));
	}
}

