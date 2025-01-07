package com.aluracursos.Challenge_literalura.repository;

import com.aluracursos.Challenge_literalura.model.Autor;
import com.aluracursos.Challenge_literalura.model.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface LibroRepository extends JpaRepository<Libro, Long> {

    List<Libro> findByIdiomas(String idioma);

    @Query("SELECT l FROM Libro l WHERE l.idiomas=:idioma")
    List<Libro> listarLibroPorIdioma(@Param("idioma") String idioma);

    Optional<Libro> findByTitulo(String titulo);
}
