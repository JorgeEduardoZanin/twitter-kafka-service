package project.spring.services.pagamento;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.asynchttpclient.AsyncHttpClient;
import org.asynchttpclient.DefaultAsyncHttpClient;
import org.asynchttpclient.Response;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import project.spring.dto.request.PagamentoPixRequest;
import project.spring.dto.response.KeyPixResponse;
import project.spring.dto.response.PixResponse;

@Component
public class PagamentoPixApi {
	
	@Value("${asaas.api-key}")
	 private String API_KEY;
	
	public PixResponse createPayment(PagamentoPixRequest request, String customer) throws IOException {
		AsyncHttpClient client = new DefaultAsyncHttpClient();
		 
		try {
			String date = LocalDate.now().plusDays(1).format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
			
			Response resp = client.prepare("POST", "https://api-sandbox.asaas.com/v3/payments")
			  .setHeader("accept", "application/json")
			  .setHeader("content-type", "application/json")
			  .setHeader("access_token", API_KEY)
			  .setBody("{\"billingType\":\"PIX\",\"customer\":\""+customer+"\",\"value\":"+request.amount()+",\"dueDate\":\""+date+"\"}")
			  .execute()
			  .toCompletableFuture()
			  .join();
			
			ObjectMapper mapper = JsonMapper.builder()
				    .addModule(new JavaTimeModule())
				    .build();
			
			return mapper.readValue(resp.getResponseBody(), PixResponse.class);
			
		}finally{
			client.close();
		}
		
	}
	
	public KeyPixResponse getPixKey(String payloadId) throws IOException {
		AsyncHttpClient client = new DefaultAsyncHttpClient();
		try {
		Response resp = client.prepare("GET", "https://api-sandbox.asaas.com/v3/payments/"+payloadId+"/pixQrCode")
		  .setHeader("accept", "application/json")
		  .setHeader("access_token", API_KEY)
		  .execute()
		  .toCompletableFuture()
		  .join();

		return new ObjectMapper().readValue(resp.getResponseBody(), KeyPixResponse.class);
		}finally {
		client.close();
		}
	}

}
