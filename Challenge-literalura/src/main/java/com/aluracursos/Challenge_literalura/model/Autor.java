package com.aluracursos.Challenge_literalura.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Entity
@Table(name="autores")
public class Autor {
    @OneToOne(mappedBy = "autor")
    private Libro libro;
    private String nombre;
    private Integer fechaDeNacimiento;
    private Integer fechaDeMuerte;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public Autor(DatosAutor datosAutor) {
        this.nombre= datosAutor.nombre();
        this.fechaDeNacimiento = Integer.valueOf(datosAutor.fechaDeNacimiento());
        this.fechaDeMuerte = Integer.valueOf(datosAutor.fechaDeMuerte());
    }

    @Override
    public String toString() {
        return  "----------------------\n"+
                "Nombre= " + nombre+"\n"+
                "Fecha de Nacimiento= "+fechaDeNacimiento+ "\n"+
                "Fecha de Muerte= "+ fechaDeMuerte+ "\n"+
                "Libro= " + libro.getTitulo()+"\n"+
                "----------------------\n";

    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getFechaDeNacimiento() {
        return fechaDeNacimiento;
    }

    public void setFechaDeNacimiento(Integer fechaDeNacimiento) {
        this.fechaDeNacimiento = fechaDeNacimiento;
    }

    public Integer getFechaDeMuerte() {
        return fechaDeMuerte;
    }

    public void setFechaDeMuerte(Integer fechaDeMuerte) {
        this.fechaDeMuerte = fechaDeMuerte;
    }

    public Autor() {

    }

    public Libro getLibro() {
        return libro;
    }

    public void setLibro(Libro libro) {
        this.libro = libro;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
