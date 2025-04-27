package project.spring.services;

import java.time.Instant;
import java.util.stream.Collectors;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;


import project.spring.dto.request.LoginRequest;
import project.spring.dto.response.LoginResponse;
import project.spring.entities.Role;
import project.spring.repository.UsuarioRepository;

@Service
public class TokenService {

	private final JwtEncoder jwtEncoder;
	private final UsuarioRepository repository;
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	public TokenService(JwtEncoder jwtEncoder, UsuarioRepository repository, BCryptPasswordEncoder bCryptPasswordEncoder) {
		this.jwtEncoder = jwtEncoder;
		this.repository = repository;
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
	}
	
	public LoginResponse login (LoginRequest loginRequest) {
		var usuario = repository.findByEmail(loginRequest.email());
		
		if(usuario.isEmpty() || !usuario.get().isLoginCorrect(loginRequest, bCryptPasswordEncoder)) {
			throw new BadCredentialsException("Usuário ou senha inválidos!");
		}
		
		var now = Instant.now();
		long expiresIn = 300L;
		
		var scopes = usuario.get().getRoles()
				.stream()
				.map(Role::getName)
				.collect(Collectors.joining(" "));
		
		var claims = JwtClaimsSet.builder()
				.issuer("mybackend") 
				.subject(usuario.get().getId().toString())
				.issuedAt(now)
				.expiresAt(now.plusSeconds(expiresIn))
				.claim("scope", scopes)
				.build();
		
		var jwtValue = jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
		
		return new LoginResponse(jwtValue, expiresIn, usuario.get().getId(), scopes);
		
	}
	
}
