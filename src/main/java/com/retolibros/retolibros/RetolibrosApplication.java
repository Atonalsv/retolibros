package com.retolibros.retolibros;

import com.retolibros.retolibros.main.Main;
import com.retolibros.retolibros.repository.LibroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.retolibros.retolibros.repository.AutorRepository;

@SpringBootApplication
public class RetolibrosApplication implements CommandLineRunner{

	public static void main(String[] args) {
		SpringApplication.run(RetolibrosApplication.class, args);

	}
	@Autowired
	private LibroRepository libroRepository;
	@Autowired
	private AutorRepository autorRepository;



	public void run(String[] args) {
		Main principal = new Main(libroRepository,autorRepository);

	}
}
