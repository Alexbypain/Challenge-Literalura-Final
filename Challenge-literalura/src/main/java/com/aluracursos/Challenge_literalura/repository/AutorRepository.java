package com.aluracursos.Challenge_literalura.repository;

import com.aluracursos.Challenge_literalura.model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface AutorRepository extends JpaRepository<Autor, Long> {
    @Query("SELECT a FROM Autor a WHERE a.fechaDeNacimiento <= :ano AND a.fechaDeMuerte IS NULL OR  a.fechaDeMuerte  > :ano")
    List<Autor> listarAutoresPorAno(@Param("ano") int ano);


    Optional<Autor> findByNombre(String nombre);
}
