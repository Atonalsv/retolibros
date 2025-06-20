package com.retolibros.retolibros.repository;

import com.retolibros.retolibros.Recursos.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AutorRepository extends JpaRepository<Autor,Long> {

    @Query(value = "SELECT * FROM Autores WHERE :anoBusqueda >= fecha_de_nacimiento AND :anoBusqueda <= fecha_de_fallecimiento", nativeQuery = true)
    List<Autor> autorPorFecha(int anoBusqueda);


}
