package project.spring.services.pagamento;

import java.io.IOException;

import org.asynchttpclient.AsyncHttpClient;
import org.asynchttpclient.DefaultAsyncHttpClient;
import org.asynchttpclient.Response;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import project.spring.dto.response.PagamentoCreditoResponse;


@Component
public class PagamentoCreditoApi {

	@Value("${asaas.api-key}")
	 private String API_KEY;
	
	public PagamentoCreditoResponse createPagamento(PagamentoCreditoResponse response) throws IOException {
		AsyncHttpClient client = new DefaultAsyncHttpClient();
		try {
			Response resp = client.prepare("POST", "https://api-sandbox.asaas.com/v3/payments/")
			  .setHeader("accept", "application/json")
			  .setHeader("content-type", "application/json")
			  .setHeader("access_token", API_KEY)
			  .setBody("{\"billingType\":\"CREDIT_CARD\",\"creditCard\":{\"holderName\":\"John Doe\",\"number\":\"1234567890123456\",\"expiryMonth\":\"5\",\"expiryYear\":\"2025\",\"ccv\":\"123\"},\"creditCardHolderInfo\":{\"name\":\"John Doe\",\"email\":\"john.doe@asaas.com\",\"cpfCnpj\":\"247.213.100-30\",\"postalCode\":\"85010300\",\"addressNumber\":\"123\",\"phone\":\"321312321321\"},\"dueDate\":\"2027-06-10\",\"value\":129.9,\"customer\":\"cus_000006682352\"}")
			  .execute()
			  .toCompletableFuture()
			  .join();
			
			ObjectMapper mapper = JsonMapper.builder()
				    .addModule(new JavaTimeModule())
				    .build();
			
			return mapper.readValue(resp.getResponseBody(), PagamentoCreditoResponse.class);
		}finally {
			client.close();
		}
	}
	
}
