package com.retolibros.retolibros.Recursos;

import jakarta.persistence.*;
import com.retolibros.retolibros.modelos.Datoslibro;


@Entity
@Table(name = "libros")
public class Libro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Column(unique = true)
    private String autor;
    private String titulo;
    private String idioma;
    private Integer numeroDeDescargas;

    public Libro (){}


    public Libro (Datoslibro datosLibro) {
        this.titulo = datosLibro.titulo();
        this.autor = datosLibro.listaAutor().get(0).nombreAutor();
        this.idioma = datosLibro.idioma();
        this.numeroDeDescargas = datosLibro.numeroDeDescargas();
    }





    @Override
    public String toString() {
        return "----- Libro -----" +
                "\n Titulo: " + titulo +
                "\n Autor: " + autor +
                "\n Idioma: " + idioma +
                "\n Número de descargas: " + numeroDeDescargas +
                "\n-----------------\n";
    }







    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        this.Id = id;
    }

    public int getNumeroDeDescargas() {
        return numeroDeDescargas;
    }

    public void setNumeroDeDescargas(int numeroDeDescargas) {
        this.numeroDeDescargas = numeroDeDescargas;
    }

    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
}
