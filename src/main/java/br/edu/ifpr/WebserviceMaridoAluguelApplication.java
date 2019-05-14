package br.edu.ifpr;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan(basePackages = {"entidade"} )
@EnableJpaRepositories(basePackages = {"repositorio"})
public class WebserviceMaridoAluguelApplication {

	public static void main(String[] args) {
		SpringApplication.run(WebserviceMaridoAluguelApplication.class, args);
	}
}
