package project.spring.resources;

import java.io.IOException;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import project.spring.services.pagamento.NotificacaoPagamentoService;

@RestController
@RequestMapping("/webhook")
public class WebhookResource {

    private static final Logger LOGGER = LoggerFactory.getLogger(WebhookResource.class);

    


    @PostMapping(consumes = "application/json")
    public ResponseEntity<Map<String, Boolean>> handleWebhook(
            @RequestBody Map<String, Object> body) {

        String event = (String) body.get("event");
        @SuppressWarnings("unchecked")
        Map<String, Object> payment = (Map<String, Object>) body.get("payment");

        LOGGER.info("Webhook recebido: event='{}', payment.id='{}'",
                    event,
                    payment.get("id"));

        switch (event) {
            case "PAYMENT_CREATED":
                LOGGER.info("Processando PAYMENT_CREATED para id={}", payment.get("id"));
                // sua lógica para PAYMENT_CREATED…
                break;

            case "PAYMENT_RECEIVED":
                LOGGER.info("Processando PAYMENT_RECEIVED para id={}", payment.get("id"));
                // sua lógica para PAYMENT_RECEIVED…
                break;

            // ... trate outros eventos
            default:
                LOGGER.warn("Evento não suportado: {}", event);
                break;
        }

        // Retorna um Map simples dizendo que recebeu com sucesso
        Map<String, Boolean> response = Map.of("received", Boolean.TRUE);
        LOGGER.info("Respondendo ao webhook com sucesso: {}", response);
        return ResponseEntity.ok(response);
    }

}
    
