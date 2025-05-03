package project.spring.services.pagamento;

import java.io.IOException;

import org.asynchttpclient.AsyncHttpClient;
import org.asynchttpclient.DefaultAsyncHttpClient;
import org.asynchttpclient.Response;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import project.spring.dto.request.PagamentoPixRequest;
import project.spring.dto.response.PagamentoPixResponse;

@Component
public class PagamentoPixApi {
	
	@Value("API_KEY")
	 private static String API_KEY;
	
	public String createKeyPix() throws IOException{
		
		  AsyncHttpClient client = new DefaultAsyncHttpClient();
	        try {
	          
	            Response resp = client.prepare("POST", "https://api-sandbox.asaas.com/v3/pix/addressKeys")
	                .setHeader("accept", "application/json")
	                .setHeader("content-type", "application/json")
	                .setHeader("access_token", API_KEY)
	                .setBody("{\"type\":\"EVP\"}")
	                .execute()
	                .toCompletableFuture()
	                .join();

	            ObjectMapper mapper = new ObjectMapper();
	            JsonNode root = mapper.readTree(resp.getResponseBody());
	            return root.get("keyValue").asText();

	        } finally {
	            client.close();
	        }
	    }
	
	
	public PagamentoPixResponse createPayment(PagamentoPixRequest request) throws IOException {
		AsyncHttpClient client = new DefaultAsyncHttpClient();
		 
		try {
			
			Response resp = client.prepare("POST", "https://api-sandbox.asaas.com/v3/payments")
			  .setHeader("accept", "application/json")
			  .setHeader("content-type", "application/json")
			  .setHeader("access_token", API_KEY)
			  .setBody("{\"billingType\":\"PIX\",\"customer\":\""+request.customerId()+"\",\"value\":"+request.amount()+",\"dueDate\":\""+request.dueDate()+"\"}")
			  .execute()
			  .toCompletableFuture()
			  .join();
			
			ObjectMapper mapper = new ObjectMapper();
			return mapper.readValue(resp.getResponseBody(), PagamentoPixResponse.class);
			
		}finally{
			client.close();
		}
		
	}

}
