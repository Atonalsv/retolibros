package com.retolibros.retolibros.repository;

import com.retolibros.retolibros.Recursos.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LibroRepository extends JpaRepository<Libro, Long> {

    @Query("SELECT l FROM Libro l WHERE l.idioma ILIKE %:lenguaje%")
    List<Libro> findByLenguaje(String lenguaje);

}
