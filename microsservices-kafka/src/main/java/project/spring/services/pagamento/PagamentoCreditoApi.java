package project.spring.services.pagamento;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.asynchttpclient.AsyncHttpClient;
import org.asynchttpclient.DefaultAsyncHttpClient;
import org.asynchttpclient.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import project.spring.dto.request.PagamentoCreditoRequest;
import project.spring.dto.request.TitularCartaoCreditoRequest;
import project.spring.dto.response.PagamentoCreditoResponse;



@Component
public class PagamentoCreditoApi{

	@Value("${asaas.api-key}")
	private String API_KEY;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(PagamentoCreditoApi.class);
	
	public PagamentoCreditoResponse createPagamento(PagamentoCreditoRequest requestPagamento, TitularCartaoCreditoRequest titularRequest, String customer, BigDecimal value) throws IOException {
		AsyncHttpClient client = new DefaultAsyncHttpClient();
		try {
			String date = LocalDate.now().plusDays(1).format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
			
			Response resp = client.prepare("POST", "https://api-sandbox.asaas.com/v3/payments/")
			  .setHeader("accept", "application/json")
			  .setHeader("content-type", "application/json")
			  .setHeader("access_token", API_KEY)
			  .setBody("{\"billingType\":\"CREDIT_CARD\",\"creditCard\":{\"holderName\":\""+requestPagamento.nomeCartao()+"\","
			  		+ "\"number\":\""+requestPagamento.numeroCartao()+"\",\"expiryMonth\":\""+requestPagamento.mesExpiracao()+"\","
			  		+ "\"expiryYear\":\""+requestPagamento.anoExpiracao()+"\",\"ccv\":\""+requestPagamento.ccv()+"\"},"
			  		+ "\"creditCardHolderInfo\":{\"name\":\""+titularRequest.nomeTitularCartao()+"\",\"email\":\""+titularRequest.email()+"\","
			  		+ "\"cpfCnpj\":\""+titularRequest.cpf_cnpj()+"\",\"postalCode\":\""+titularRequest.codigoPostal()+"\",\"addressNumber\":\""+titularRequest.numeroEndereco()+"\","
			  		+ "\"phone\":\""+titularRequest.telefone()+"\"},\"dueDate\":\""+date+"\",\"value\":"+value+",\"customer\":\""+customer+"\"}")
			  .execute()
			  .toCompletableFuture()
			  .join();
			
			
			ObjectMapper mapper = JsonMapper.builder()
				    .addModule(new JavaTimeModule())
				    .build();
			LOGGER.info("Consumindo mensagem {}",  resp);
			LOGGER.info("Consumindo mensagem {}",  mapper);
			return mapper.readValue(resp.getResponseBody(), PagamentoCreditoResponse.class);
			
		}finally {
			client.close();
		}
	}
	
}
