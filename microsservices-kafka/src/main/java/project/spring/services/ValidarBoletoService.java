package project.spring.services;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import project.spring.entities.Boleto;
import project.spring.enums.StatusBoleto;
import project.spring.mapper.BoletoMapper;
import project.spring.repository.BoletoRepository;
import project.spring.services.kafka.BoletoProducer;

@Service
public class ValidarBoletoService {

    private final BoletoMapper boletoMapper;

	@Autowired
	private BoletoRepository boletoRepository;
	
	@Autowired
	private BoletoProducer producer;

    ValidarBoletoService(BoletoMapper boletoMapper) {
        this.boletoMapper = boletoMapper;
    }
	
	public void validarBoleto(Boleto boleto) throws InterruptedException {
		var codigoBarras = Integer.valueOf(boleto.getCodigoBarras().substring(0,1));
		var boletoAtualizado = boletoRepository.findByCodigoBarras(boleto.getCodigoBarras());
		boletoAtualizado.setDataAtualizacao(LocalDateTime.now());
		
		if(codigoBarras % 2 == 0) {
			boletoAtualizado.setStatusBoleto(StatusBoleto.ERRO_VALIDACAO);
			boletoRepository.saveAndFlush(boletoAtualizado);
			producer.enviarMensagem(boletoMapper.toBoletoAvro(boletoAtualizado));
			return;
		}
		
		boletoAtualizado.setStatusBoleto(StatusBoleto.VALIDADO);
		boletoRepository.saveAndFlush(boletoAtualizado);
		producer.enviarMensagem(boletoMapper.toBoletoAvro(boletoAtualizado));
		
		
	    String codigoBarrasNumeros = boleto.getCodigoBarras().replaceAll("[^0-9]", "");
		
		Thread.sleep(1000);
		
		if(codigoBarrasNumeros.length() > 13 ) {
			boletoAtualizado.setStatusBoleto(StatusBoleto.ERRO_PAGAMENTO);
			boletoRepository.saveAndFlush(boletoAtualizado);
			producer.enviarMensagem(boletoMapper.toBoletoAvro(boletoAtualizado));
			return;
		}
		boletoAtualizado.setStatusBoleto(StatusBoleto.PAGO);
		boletoRepository.saveAndFlush(boletoAtualizado);
		producer.enviarMensagem(boletoMapper.toBoletoAvro(boletoAtualizado));
		
		
	}
	
}
