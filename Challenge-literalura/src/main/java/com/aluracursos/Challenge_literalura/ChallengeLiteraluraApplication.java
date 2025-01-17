package com.aluracursos.Challenge_literalura;

import com.aluracursos.Challenge_literalura.principal.Principal;
import com.aluracursos.Challenge_literalura.repository.AutorRepository;
import com.aluracursos.Challenge_literalura.repository.LibroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class ChallengeLiteraluraApplication implements CommandLineRunner {

	@Autowired
	private LibroRepository repository;
	@Autowired
	private AutorRepository autorRepository;
	public static void main(String[] args) {
		SpringApplication.run(ChallengeLiteraluraApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Principal principal=new Principal(repository,autorRepository);
		principal.menu();

	}
}
