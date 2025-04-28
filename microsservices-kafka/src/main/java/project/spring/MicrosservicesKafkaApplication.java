package project.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;

@SpringBootApplication
@EnableKafka
public class MicrosservicesKafkaApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicrosservicesKafkaApplication.class, args);
	}

}
