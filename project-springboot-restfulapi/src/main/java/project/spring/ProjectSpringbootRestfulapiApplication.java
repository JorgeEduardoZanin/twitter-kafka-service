package project.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.kafka.annotation.EnableKafka;


@SpringBootApplication
@EnableCaching
@EnableKafka
@EnableFeignClients
public class ProjectSpringbootRestfulapiApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProjectSpringbootRestfulapiApplication.class, args);
		
	}

}
