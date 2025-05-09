package project.spring.resources;


import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import project.spring.services.NotificacaoWebhookPagamentoService;



@RestController
@RequestMapping("/webhook")
public class WebhookResource {

    private static final Logger LOGGER = LoggerFactory.getLogger(WebhookResource.class);
    
    @Autowired
    private NotificacaoWebhookPagamentoService service;
    
    @PostMapping(consumes = "application/json")
    public ResponseEntity<Map<String, Boolean>> handleWebhook(@RequestBody Map<String, Object> body) {

    	service.notificacaoPagamento(body);
    	
     
       
        
   

     

        Map<String, Boolean> response = Map.of("received", Boolean.TRUE);
        LOGGER.info("Respondendo ao webhook com sucesso: {}", response);
        return ResponseEntity.ok(response);
    }

}
    
