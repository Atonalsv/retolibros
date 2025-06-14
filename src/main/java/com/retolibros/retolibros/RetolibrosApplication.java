package com.retolibros.retolibros;

import Recursos.Autor;
import Recursos.Menu;
import Servicios.APIrequest;
import Servicios.ConvierteDatos;
import com.google.gson.Gson;
import modelos.DatosAutor;
import modelos.Datoslibro;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import repository.AutorRepository;

import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import static Recursos.Menu.menu;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
public class RetolibrosApplication implements CommandLineRunner{

	//@Autowired
	private AutorRepository repository;

	public static void main(String[] args) {
		SpringApplication.run(RetolibrosApplication.class, args);

		String url = "https://gutendex.com/books/?search=";
		Scanner scanner = new Scanner(System.in);
		var APIrequest = new APIrequest();
		boolean continuar = true;


		 while(continuar != false){
			 System.out.println(menu);
			 int opcion = scanner.nextInt();
			 scanner.nextLine();


			 switch (opcion){
				 case 1:
					 System.out.println("Ingrese el nombre del libro que desea buscar");
					 String libro = scanner.nextLine();
					 var json = APIrequest.getAPIrequest(url + libro.replace(" ","%20"));
					 System.out.println(json);
					 ConvierteDatos conversor = new ConvierteDatos();
					 var datos = conversor.obtenerDatos(json, Datoslibro.class);
					 System.out.println(datos);
					 break;
				 case 2:
					 System.out.println("<Lista> de libros registrados");
					 break;
				 case 3:
					 System.out.println("<lista> de autores registrados");
					 break;
				 case 4:
					 System.out.println("<Lista> de autores vivos en un determinado año");
					 break;
				 case 5:
					 System.out.println("<Lista> de libros por idioma");
					 break;
				 case 0:
					 System.out.println("Saliendo de la aplicacion...");
					 System.out.println("Gracias por usar RetoLibros");
					 continuar = false;
					 scanner.close();
					 break;
				 default:
					 System.out.println("Por favor, ingrese una opcion valida");
					 break;




			 }
		 }

	}


	@Override
	public void run(String... args) throws Exception {

	}
}
