package com.example.Candidat;

import com.example.Candidat.entity.Candidat;
import com.example.Candidat.entity.CandidatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CandidatApplication {

	public static void main(String[] args) {
		SpringApplication.run(CandidatApplication.class, args);
	}

	@Autowired
	private CandidatRepository repository;
	@Bean
	ApplicationRunner init() {
		return (args) -> {
			// save
			repository.save(new Candidat("Mariem", "Ch", "ma@esprit.tn"));
			repository.save(new Candidat("Sarra", "ab", "sa@esprit.tn"));
			repository.save(new Candidat("Mohamed", "ba", "mo@esprit.tn"));
			repository.save(new Candidat("Maroua", "dh", "maroua@esprit.tn"));
			// fetch
			repository.findAll().forEach(System.out::println);
		};
	}

}
