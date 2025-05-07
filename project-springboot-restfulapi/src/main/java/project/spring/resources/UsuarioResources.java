package project.spring.resources;

import java.util.List;
import java.util.UUID;

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

import project.spring.dto.request.UsuarioRequest;
import project.spring.entities.Usuario;
import project.spring.services.UsuarioServices;

@RestController
@RequestMapping(value = "/usuario")
public class UsuarioResources {

	@Autowired
	private UsuarioServices services;
	/*
	 *
	 * 
	 CONTROLADORES DO TIPO GET 
	 *
	 *
	 */
	@GetMapping
	@PreAuthorize("hasAuthority('SCOPE_BASIC')")
	public ResponseEntity<List<Usuario>> findAll(){
		List<Usuario> listaUsuarios = services.findAll();
		return ResponseEntity.status(HttpStatus.OK).body(listaUsuarios);
	}
	
	@GetMapping(value = "/{id}")
	@PreAuthorize("hasAuthority('SCOPE_BASIC')")
	public ResponseEntity<Usuario> findById(@PathVariable UUID id){
		Usuario usuario = services.findById(id);
		return ResponseEntity.status(HttpStatus.OK).body(usuario);
	}
	
	@GetMapping(value = "/authUser")
	@PreAuthorize("hasAuthority('SCOPE_BASIC')")
	public ResponseEntity<Usuario> findByIdAuthentication(JwtAuthenticationToken token){
		Usuario usuario = services.findByIdAuthentication(token);
		return ResponseEntity.status(HttpStatus.OK).body(usuario);
	}
	
	@GetMapping(value = "/nome/{nome}")
	@PreAuthorize("hasAuthority('SCOPE_BASIC')")
	public ResponseEntity<List<Usuario>> findAllLikeName(@PathVariable String nome){
		List<Usuario> userList = services.findAllByLikeName(nome);
		return ResponseEntity.status(HttpStatus.OK).body(userList);
	}
	/*
	 *
	 * 
	 CONTROLADORES DO TIPO POST
	 *
	 *
	 */
	@PostMapping
	public ResponseEntity<Usuario> saveUser(@RequestBody UsuarioRequest request){
		Usuario usuario = services.saveUser(request);
		return ResponseEntity.status(HttpStatus.CREATED).body(usuario);
	}
	/*
	 *
	 * 
	 CONTROLADORES DO TIPO PUT
	 *
	 *
	 */
	@PutMapping(value = "/{id}")
	@PreAuthorize("hasAuthority('SCOPE_BASIC')")
	public ResponseEntity<Usuario> updateUser(@RequestBody Usuario usuario, JwtAuthenticationToken token){
		usuario = services.updateUser(usuario, token);
		return ResponseEntity.status(HttpStatus.OK).body(usuario);
	}
	/*
	 *
	 * 
	 CONTROLADORES DO TIPO DELETE
	 *
	 *
	 */
	@DeleteMapping(value = "/{id}")
	@PreAuthorize("hasAuthority('SCOPE_BASIC')")
	public ResponseEntity<String> deleteUser(@PathVariable UUID id){
		return ResponseEntity.status(HttpStatus.OK).body(services.deleteUserById(id));
	}
}
