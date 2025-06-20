package com.retolibros.retolibros.main;


import com.retolibros.retolibros.Recursos.Autor;
import com.retolibros.retolibros.Recursos.Libro;
import com.retolibros.retolibros.Servicios.APIrequest;
import com.retolibros.retolibros.Servicios.ConvierteDatos;
import com.retolibros.retolibros.modelos.DatosResultado;
import com.retolibros.retolibros.modelos.Datoslibro;
import com.retolibros.retolibros.repository.AutorRepository;
import com.retolibros.retolibros.repository.LibroRepository;


import java.awt.print.Book;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import static com.retolibros.retolibros.Recursos.Menu.menu;

public class Main {
    private APIrequest APIrequest = new APIrequest();
    private Scanner scanner = new Scanner(System.in);
    private String urlBase ="https://gutendex.com/books/";
    private ConvierteDatos convierteDatos = new ConvierteDatos();
    private LibroRepository libroRepository;
    private AutorRepository autorRepository;
    private List<Libro> libros;
    private List<Autor> autores;


    public Main(LibroRepository libroRepository, AutorRepository autorRepository) {
        this.libroRepository = libroRepository;
        this.autorRepository = autorRepository;
    }

    public void principal(){
        boolean continuar = true;


        while(continuar != false) {
            System.out.println(menu);
            int option = 0;
            try {
                option = scanner.nextInt();
                scanner.nextLine();
            } catch (Exception e) {
                System.out.println("Ingrese una opcion valida");
            }

            switch (option) {
                case 1:
                    buscarLibro();
                    break;
                case 2:
                    consultarLibros();
                    break;
                case 3:
                    consultarAutores();
                    break;
                case 4:
                    consultarAutoresPorAno();
                    break;
                case 5:
                    consultarLibrosLenguaje();
                    break;
                case 0:
                    System.out.println("Hasta luego");
                    break;
                default:
                    System.out.println("Ingresa una opcion valida");
            }
        }
    }


    //Usar la Api para obtener datos
    private Datoslibro obtenerDatos(){
        System.out.println("Ingrese el nombre del libro");
        String busqueda = scanner.nextLine().toLowerCase().replace(" ","%20");
        String json = APIrequest.getAPIrequest(urlBase + "?search=" + busqueda);
        Datoslibro datoslibro = convierteDatos.obtenerDatos(json, Datoslibro.class);
        return datoslibro;
    }

    //Busca un libro y lo guarda en la base de datos
    private void buscarLibro(){
        Datoslibro datoslibro = obtenerDatos();

        try {
            Libro libro = new Libro(datoslibro.resultados().get(0));
            Autor autor = new Autor(datoslibro.resultados().get(0).autorList().get(0));

            System.out.println("""
                    libro[
                        titulo: %s
                        author: %s
                        lenguaje: %s
                        descargas: %s
                    ]
                    """.formatted(libro.getTitulo(),
                    libro.getAutor(),
                    libro.getIdioma(),
                    libro.getNumeroDeDescargas().toString()));

            libroRepository.save(libro);
            autorRepository.save(autor);

        } catch (Exception e) {
            System.out.println("no se encontro ese libro");
        }


    }
    // Trae los libros guardados en la BD
    private void consultarLibros() {
        libros = libroRepository.findAll();
        libros.stream().forEach(l -> {
            System.out.println("""    
                        Titulo: %s
                        Author: %s
                        Lenguaje: %s
                        Descargas: %s
                    """.formatted(l.getTitulo(),
                    l.getAutor(),
                    l.getIdioma(),
                    l.getNumeroDeDescargas().toString()));
        });
    }

    // Trae todos los autores de los libros consultados en la BD
    private void consultarAutores() {
        autores = autorRepository.findAll();
        autores.stream().forEach(a -> {
            System.out.println("""
                        Autor: %s
                        Año de nacimiento: %s
                        Año de defuncion: %s
                    """.formatted(a.getAutor(),
                    a.getFechaDeNacimiento().toString(),
                    a.getFechaDeFallecimiento().toString()));
        });
    }

    // Trae a los autores apartir de cierto año
    public void consultarAutoresPorAno() {
        System.out.println("Ingresa el año a partir del cual buscar:");
        int anoBusqueda = scanner.nextInt();
        scanner.nextLine();

        List<Autor> autores = autorRepository.autorPorFecha(anoBusqueda);
        if (autores.isEmpty()) {
            System.out.println("No hay autores disponibles en la fecha seleccionada");
        } else {
            autores.forEach(a -> {
                System.out.println("""
                        Nombre: %s
                        Fecha de nacimiento: %s
                        Fecha de defuncion: %s
                        """.formatted(a.getAutor(), a.getFechaDeNacimiento().toString(), a.getFechaDeFallecimiento().toString()));
            });
        }
    }
    private void consultarLibrosLenguaje(){
        System.out.println("""
                ****************************************************************    
                    Selcciona el lenguaje de los libros que deseas consultar
                ****************************************************************
                1 - En (Ingles)
                2 - Es (Español)
                """);

        try {

            int opcion2 = scanner.nextInt();
            scanner.nextLine();

            switch (opcion2) {
                case 1:
                    libros = libroRepository.findByLenguaje("en");
                    break;
                case 2:
                    libros = libroRepository.findByLenguaje("es");
                    break;

                default:
                    System.out.println("Ingresa una opcion valida");
                    break;
            }

            libros.stream().forEach(l -> {
                System.out.println("""    
                        Titulo: %s
                        Author: %s
                        Lenguaje: %s
                        Descargas: %s
                    """.formatted(l.getTitulo(),
                        l.getAutor(),
                        l.getIdioma(),
                        l.getNumeroDeDescargas().toString()));
            });

        } catch (Exception e){
            System.out.println("Ingresa un valor valido");
        }
    }

}

