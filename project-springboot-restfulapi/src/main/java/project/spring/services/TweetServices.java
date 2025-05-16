package project.spring.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import project.spring.entities.Role;
import project.spring.entities.Tweet;
import project.spring.entities.Usuario;
import project.spring.exceptions.TweetAccessDeniedException;
import project.spring.repository.TweetRepository;
import project.spring.repository.UsuarioRepository;

@Service
public class TweetServices {

	@Autowired
	private TweetRepository repository;
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	/*
	 *
	 * 
	 SERVICES DO TIPO GET
	 *
	 *
	 */
	public List<Tweet> listAll(int page, int pageSize){
		
		return repository
	            .findAll(PageRequest.of(page, pageSize, Sort.Direction.DESC, "createTimestamp"))
	            .getContent();
	}
	/*
	 *
	 * 
	 SERVICES DO TIPO POST
	 *
	 *
	 */
	public Tweet createTweet(Tweet tweet, JwtAuthenticationToken token) {
	
		var usuario = usuarioRepository.findById(UUID.fromString(token.getName()));
		if (!usuario.get().isAssinante() && tweet.getContent().length() > 100) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Usuário não é assinante premium.");
		}
		tweet.setUsuario(usuario.get());
		return repository.save(tweet);
		
		
	}
	/*
	 *
	 * 
	 SERVICES DO TIPO DELETE
	 *
	 *
	 */
	public String deleteTweet(Long id, JwtAuthenticationToken token) {
		
		Tweet tweet = repository.findById(id).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Tweet não encontrado!"));
		
		Optional<Usuario> admin = usuarioRepository.findById(UUID.fromString(token.getName()));
		
		var isAdm = admin.get().getRoles().stream().anyMatch(role->role.getName().equalsIgnoreCase(Role.Values.ADMIN.name()));
		
		if(!isAdm && !UUID.fromString(token.getName()).equals(tweet.getUsuario().getId())) {
			throw new TweetAccessDeniedException();
		}
			
		repository.deleteById(id);
		return "Tweet deletado com sucesso!";
	}
	
	
	
	
	
	
	
	
}
