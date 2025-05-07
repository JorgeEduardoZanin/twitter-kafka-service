package project.spring.resources;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import project.spring.dto.request.PagamentoCreditoRequest;

import project.spring.dto.request.TitularCartaoCreditoRequest;
import project.spring.dto.response.PagamentoCreditoResponse;
import project.spring.dto.wrapper.PagamentoCreditoWrapperRequest;
import project.spring.services.PagamentoService;

@RestController
@RequestMapping("/credito")
public class PagamentoCreditoResources {

	@Autowired
	private PagamentoService service;
	
	@PostMapping
	public ResponseEntity<PagamentoCreditoResponse> createPagamento(@RequestBody @Valid PagamentoCreditoWrapperRequest wrapper) throws IOException{
	
		System.out.println(wrapper);
		PagamentoCreditoRequest pagReq    = wrapper.pagamentoCredito();
		TitularCartaoCreditoRequest titReq = wrapper.titularCartao();
		
		return ResponseEntity.ok(service.createPagamentoCredito(pagReq, titReq));
	}
}
