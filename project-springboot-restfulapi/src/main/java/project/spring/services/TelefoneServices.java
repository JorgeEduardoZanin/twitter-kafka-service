package project.spring.services;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Service;

import project.spring.entities.Telefone;
import project.spring.entities.Usuario;
import project.spring.repository.TelefoneRepository;

@Service
public class TelefoneServices {

	@Autowired
	private TelefoneRepository repository;
	/*
	 *
	 * 
	 SERVICES DO TIPO GET
	 *
	 *
	 */
	public List<Telefone> findAll(JwtAuthenticationToken token){
		return repository.findByUsuarioIdOrderByNumeroAsc(UUID.fromString(token.getName()));
	}
	
	public Telefone findById(Long id, JwtAuthenticationToken token) {
		return repository.findByIdAndUsuarioId(id, UUID.fromString(token.getName()));
	}
	
	public List<Telefone> findByNumero(String numero, JwtAuthenticationToken token) {
		return repository.findAllByNumero(numero, UUID.fromString(token.getName()));
	}
	/*
	 *
	 * 
	 SERVICES DO TIPO POST
	 *
	 *
	 */
	public Telefone saveTelefone(Telefone telefone, JwtAuthenticationToken token) {
		
		Usuario usuario = new Usuario();
		usuario.setId(UUID.fromString(token.getName()));
		telefone.setUsuario(usuario);
		return repository.save(telefone);
	}
	/*
	 *
	 * 
	 SERVICES DO TIPO PUT
	 *
	 *
	 */
	public Telefone updateTelefone(Telefone telefone, Long id, JwtAuthenticationToken token) {
		Usuario usuario = new Usuario();
		usuario.setId(UUID.fromString(token.getName()));
		telefone.setId(id);
		telefone.setUsuario(usuario);
		return repository.saveAndFlush(telefone);
	}
	/*
	 *
	 * 
	 SERVICES DO TIPO DELETE
	 *
	 *
	 */
	public String deleteTelefoneById(Long id) {
		repository.deleteById(id);
		return "Telefone deletado com sucesso!";
	}
	


	
}
