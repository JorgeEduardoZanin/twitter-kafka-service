package project.spring.services;


import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Service;

import project.spring.dto.request.UsuarioPagamentoRequest;
import project.spring.dto.request.UsuarioRequest;
import project.spring.entities.Role;
import project.spring.entities.Usuario;
import project.spring.repository.RoleRepository;
import project.spring.repository.UsuarioRepository;

@Service
public class UsuarioServices {
	
	@Autowired
	private UsuarioRepository repository;
	@Autowired
	private RoleRepository roleRepository;
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	/*
	 *
	 * 
	 SERVICES TIPO GET
	 *
	 *
	 */
	public List<Usuario> findAll(){
		return repository.findAllByOrderByNomeAsc();
	}
	
	public Usuario findById(UUID id) {
		Optional<Usuario> usuario = repository.findById(id);
		return usuario.get();
	}
	
	public Usuario findByIdAuthentication(JwtAuthenticationToken token) {
		Optional<Usuario> usuario = repository.findById(UUID.fromString(token.getName()));
		return usuario.get();
	}
	
	public List<Usuario> findAllByLikeName(String name){
		return repository.findAllWhereNomeOrderByNome(name);
	}
	/*
	 *
	 * 
	 SERVICES DO TIPO POST
	 *
	 *
	 */
	public Usuario saveUser(UsuarioRequest request) {	
		var usuario = request.toEntity();
		usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
		Role role = roleRepository.findFirstByName(Role.Values.BASIC.name());
		usuario.setRoles(Set.of(role));
		usuario = repository.save(usuario);
		
		return usuario;
	}
	/*
	 *
	 * 
	 SERVICES DO TIPO PUT
	 *
	 *
	 */
	public Usuario updateUser(Usuario usuario, JwtAuthenticationToken token) {
		usuario.setId(UUID.fromString(token.getName()));
		usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
		return usuario = repository.saveAndFlush(usuario);
	}
	/*
	 *
	 * 
	 SERVICES DO TIPO DELETE
	 *
	 *
	 */
	public String deleteUserById(UUID id) {
		repository.deleteById(id);
		return "Usuario deletado com sucesso!";
	}
	/*
	 *
	 * 
	 SERVICES DE LOGICAS
	 *
	 *
	 */
	public UsuarioPagamentoRequest verificaPrimeiraCobranca(Usuario usuario) {
		UsuarioPagamentoRequest usuarioRequest = new UsuarioPagamentoRequest(usuario.getId().toString(), null, null);
		
		if(usuario.isPrimeiraCobranca()) {
			 usuarioRequest = new UsuarioPagamentoRequest(usuario.getId().toString(), usuario.getCpf_cnpj(), usuario.getNome());
			 usuario.setPrimeiraCobranca(false);
			 repository.saveAndFlush(usuario);
		}
		return usuarioRequest;
		}

}
