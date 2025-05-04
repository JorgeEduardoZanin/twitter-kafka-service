package project.spring.services.pagamento;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class UsuarioPagamento {

	@Value("${asaas.api-key}")
	private String API_KEY;
	
}
