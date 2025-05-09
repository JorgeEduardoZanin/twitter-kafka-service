package project.spring.services;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import project.spring.dto.request.UsuarioPagamentoRequest;
import project.spring.dto.response.UsuarioPagamentoResponse;
import project.spring.repository.UsuarioPagamentoRepository;
import project.spring.services.pagamento.UsuarioPagamentoApi;

@Service
public class UsuarioPagamentoService {

	@Autowired
	private UsuarioPagamentoApi usuarioPagamento;

	@Autowired
	private UsuarioPagamentoRepository usuarioPagamentoRepository;
	
	public UsuarioPagamentoResponse UsuarioPagamento(UsuarioPagamentoRequest request) throws IOException {
		var usuario = usuarioPagamento.createUsuarioPagamento(request);

		usuarioPagamentoRepository.save(usuario.toEntity(request.usuarioId()));
		return usuario;
	}
	
}
