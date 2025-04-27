package project.spring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class OpenAPIConfig {

    @Bean
    OpenAPI customOpenAPI() {
		return new OpenAPI().info(new Info()
				.title("API boleto")
				.description("API de pagamento de boletos.")
				.contact(new Contact().name("Jorge Eduardo").email("jorgedevsoftware@hotmail.com"))
				.version("1.0.0")
				);		
	}

}