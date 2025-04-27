package project.spring.services;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Service;

import project.spring.dto.request.BoletoRequest;
import project.spring.dto.response.BoletoResponse;
import project.spring.entities.Boleto;
import project.spring.enums.StatusBoleto;
import project.spring.exceptions.ThisTicketAlreadyExists;
import project.spring.exceptions.UserIsAlreadyASubscriber;
import project.spring.mapper.BoletoMapper;
import project.spring.repository.BoletoRepository;
import project.spring.repository.UsuarioRepository;
import project.spring.services.kafka.producer.BoletoProducer;

@Service
public class BoletoServices {
	
	@Autowired
	private BoletoRepository repository;
	
	@Autowired
	private BoletoProducer boletoProducer;
	
	@Autowired
	private UsuarioRepository usuarioRepository;


	public BoletoResponse saveBoleto(BoletoRequest boletoRequest, JwtAuthenticationToken token) {
		var boleto = repository.findByCodigoBarras(boletoRequest.codigoBarras());
		if(boleto.isPresent()) {
			throw new ThisTicketAlreadyExists();
		}
		
		UUID idUser = UUID.fromString(token.getName());
		var usuario = usuarioRepository.findById(idUser);
		
		if(usuario.get().isAssinante() == true) {
			throw new UserIsAlreadyASubscriber();
		}
		
		Boleto criarBoleto = new Boleto(boletoRequest.codigoBarras(), StatusBoleto.INICIALIZADO, LocalDateTime.now(), LocalDateTime.now(), usuario.get());
		
		repository.save(criarBoleto);
		boletoProducer.enviarMensagem(BoletoMapper.boletoAvro(criarBoleto));
		
		return BoletoMapper.boletoDTO(criarBoleto);
	}
	
}
