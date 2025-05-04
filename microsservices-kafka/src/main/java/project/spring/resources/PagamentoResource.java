package project.spring.resources;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import project.spring.dto.request.PagamentoPixRequest;
import project.spring.dto.response.PagamentoPixResponse;
import project.spring.services.PagamentoService;

@RestController
@RequestMapping("/pagamento")
public class PagamentoResource {

	@Autowired
	private PagamentoService service;
	
	@PostMapping
	public ResponseEntity<PagamentoPixResponse> createPagamento(@RequestBody PagamentoPixRequest request) throws IOException{
		System.out.println(request);
		return ResponseEntity.ok(service.createPagamentoPix(request));
	}
}
