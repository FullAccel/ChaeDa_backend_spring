package Chaeda_spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class ChaedaSpringApplication {

	public static void main(String[] args) {
		SpringApplication.run(ChaedaSpringApplication.class, args);
	}

}
