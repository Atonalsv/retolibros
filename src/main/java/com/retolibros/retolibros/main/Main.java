package com.retolibros.retolibros.main;


import com.retolibros.retolibros.Recursos.Autor;
import com.retolibros.retolibros.Recursos.Libro;
import com.retolibros.retolibros.Servicios.APIrequest;
import com.retolibros.retolibros.Servicios.ConvierteDatos;
import com.retolibros.retolibros.modelos.DatosResultado;
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

















}

