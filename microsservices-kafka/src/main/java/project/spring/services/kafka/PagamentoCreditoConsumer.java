package project.spring.services.kafka;


import java.io.IOException;
import java.math.BigDecimal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Service;

import project.spring.avro.PagamentoRequest;
import project.spring.dto.request.PagamentoCreditoRequest;
import project.spring.dto.request.TitularCartaoCreditoRequest;
import project.spring.dto.request.UsuarioPagamentoRequest;
import project.spring.dto.wrapper.PagamentoCreditoWrapperRequest;
import project.spring.services.PagamentoService;
import project.spring.util.DecimalUtils;



@Service
public class PagamentoCreditoConsumer{

	private static final Logger LOGGER = LoggerFactory.getLogger(PagamentoCreditoConsumer.class);
	
	@Autowired
	private PagamentoService service;
	
	@KafkaListener(topics = "${spring.kafka.topico-assinatura-credito}", groupId = "${spring.kafka.consumer.credito.group-id}")
	public void pagamentoCreditoConsumer(PagamentoRequest pagamento, Acknowledgment ack) throws IOException {
		LOGGER.info("Consumindo mensagem {}",  pagamento);
		
		PagamentoCreditoRequest creditoRequest = PagamentoCreditoRequest.toCreditoRequest(pagamento);
		TitularCartaoCreditoRequest titularCartaoRequest = TitularCartaoCreditoRequest.toTitularCartaoRequest(pagamento);
		UsuarioPagamentoRequest usuarioRequest = UsuarioPagamentoRequest.toUsuarioRequest(pagamento);
		BigDecimal value  = DecimalUtils.toBigDecimal(pagamento.getValue(), 2);

		PagamentoCreditoWrapperRequest  wrapper = new PagamentoCreditoWrapperRequest(creditoRequest, titularCartaoRequest, usuarioRequest, value);
		service.createPagamentoCredito(wrapper);
			
		ack.acknowledge();
	}
	
	
	}
	

