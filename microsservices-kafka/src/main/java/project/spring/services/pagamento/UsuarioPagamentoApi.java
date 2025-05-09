package project.spring.services.pagamento;

import java.io.IOException;

import org.asynchttpclient.AsyncHttpClient;
import org.asynchttpclient.DefaultAsyncHttpClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.asynchttpclient.Response;
import project.spring.dto.request.UsuarioPagamentoRequest;
import project.spring.dto.response.UsuarioPagamentoResponse;



@Component
public class UsuarioPagamentoApi {

	@Value("${asaas.api-key}")
	private String API_KEY;
	
	public UsuarioPagamentoResponse createUsuarioPagamento(UsuarioPagamentoRequest request) throws IOException {
		AsyncHttpClient client = new DefaultAsyncHttpClient();
		try {
			Response resp = client.prepare("POST", "https://api-sandbox.asaas.com/v3/customers")
				  .setHeader("accept", "application/json")
				  .setHeader("content-type", "application/json")
				  .setHeader("access_token", API_KEY)
				  .setBody("{\"name\":\""+request.nome()+"\",\"cpfCnpj\":\""+request.cpf_cnpj()+"\"}")
				  .execute()
				  .toCompletableFuture()
				  .join();
		
				return new ObjectMapper().findAndRegisterModules().readValue(resp.getResponseBody(), UsuarioPagamentoResponse.class);
		}finally {
				client.close();
		}
	}
	
}
