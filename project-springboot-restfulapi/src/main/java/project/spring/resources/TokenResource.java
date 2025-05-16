package project.spring.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import project.spring.dto.request.LoginRequest;
import project.spring.dto.response.LoginResponse;
import project.spring.services.TokenService;

@RestController
public class TokenResource {
	
	@Autowired
	private TokenService service;

	@PostMapping(value = "/login")
	public ResponseEntity<LoginResponse> login (@RequestBody LoginRequest loginRequest){
		return ResponseEntity.ok().body(service.login(loginRequest));
	}
	
}
