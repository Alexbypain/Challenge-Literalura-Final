package com.aluracursos.Challenge_literalura.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name="libros")
public class Libro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique=true)
    private String titulo;

    @ManyToOne
    private Autor autor;
    private String idiomas;
    private Double numeroDeDescargas;



    public Libro(DatosLibro datosLibro) {
        this.titulo= datosLibro.titulo();
        this.idiomas= datosLibro.idiomas().get(0).replace("[","").replace("]","");
        this.numeroDeDescargas= datosLibro.numeroDeDescargas();

    }

    public Libro(DatosLibro datosLibro, Autor autorExistente) {
        this.titulo= datosLibro.titulo();
        this.idiomas= datosLibro.idiomas().get(0);
        this.numeroDeDescargas= datosLibro.numeroDeDescargas();
        this.autor = autorExistente;

    }


    @Override
    public String toString() {
        return "------LIBRO----\n" +
                "Titulo='" + titulo + '\n' +
                "Autor=" + autor.getNombre() + "\n"+
                "Idiomas='" + idiomas + '\'' +"\n"+
                "NumeroDeDescargas=" + numeroDeDescargas +"\n"+
                "------------";
    }

    public Libro() {

    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    public String getIdiomas() {
        return idiomas;
    }

    public void setIdiomas(String idiomas) {
        this.idiomas = idiomas;
    }

    public Double getNumeroDeDescargas() {
        return numeroDeDescargas;
    }

    public void setNumeroDeDescargas(Double numeroDeDescargas) {
        this.numeroDeDescargas = numeroDeDescargas;
    }
}
