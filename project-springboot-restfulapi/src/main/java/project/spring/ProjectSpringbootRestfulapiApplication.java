package project.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.kafka.annotation.EnableKafka;


@SpringBootApplication
@EnableCaching
@EnableKafka
public class ProjectSpringbootRestfulapiApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProjectSpringbootRestfulapiApplication.class, args);
		//System.out.println(new BCryptPasswordEncoder().encode("jorginho"));
		
	}

}
