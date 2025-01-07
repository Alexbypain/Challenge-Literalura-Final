package com.aluracursos.Challenge_literalura.principal;

import com.aluracursos.Challenge_literalura.model.Autor;
import com.aluracursos.Challenge_literalura.model.DatosLibro;
import com.aluracursos.Challenge_literalura.model.DatosResultados;
import com.aluracursos.Challenge_literalura.model.Libro;
import com.aluracursos.Challenge_literalura.repository.AutorRepository;
import com.aluracursos.Challenge_literalura.repository.LibroRepository;
import com.aluracursos.Challenge_literalura.service.ConsumoAPI;
import com.aluracursos.Challenge_literalura.service.ConvierteDatos;

import java.util.Optional;
import java.util.Scanner;

public class Principal {
    private ConsumoAPI consumoAPI=new ConsumoAPI();
    private final String URL_BASE ="https://gutendex.com/books/";
    private ConvierteDatos conversor=new ConvierteDatos();
    Scanner teclado=new Scanner(System.in);
    private int opcion=6;
    String opcionLibro="";
    private LibroRepository libroRepository;
    private AutorRepository autorRepository;

    public Principal(LibroRepository libroRepository,AutorRepository autorRepository ) {
        this.autorRepository=autorRepository;
        this.libroRepository=libroRepository;
    }

    public void menu() {
        while (opcion != 0) {
            System.out.println("-----");
            System.out.println("""
                    Elige la opción a traves de su número
                    1- buscar libro por titulo
                    2- listar libros registrados
                    3- listar autores registrados
                    4- listar autores vivos en un determinado año 
                    5- listar libros por idioma 
                    0- salir""");
            opcion = teclado.nextInt();
            teclado.nextLine();
           switch (opcion) {
                case 1:
                    buscarLibrosPorTitulo();
                    break;
               case 2:
                   listarLibroRegistrados();
                   break;
               case 3:
                   listarAutoresRegistrados();
                   break;
               case 4:
                   System.out.println("Digite un año");
                   var opcionAno=teclado.nextInt();
                   teclado.nextLine();
                   listarAutoresPorAno(opcionAno);
                   break;
               case 5:
                   System.out.println("""
                           Digite un idioma
                           es- español
                           en- inglés
                           fr- francés
                           pt- portuges""");
                   var opcionIdioma=teclado.nextLine();
                   listarLibrosPorIdioma(opcionIdioma);
                   break;

                default:
                    System.out.println("Opcion no valida");
                    break;
            }


        }
    }

        public void buscarLibrosPorTitulo(){
            System.out.println("Ingrese el nombre del libro que desea buscar");
            opcionLibro=teclado.nextLine();

                var JsonBusqueda = consumoAPI.obtenerDatos(URL_BASE+"?search="+opcionLibro);
                var datosBusqueda = conversor.obtenerDatos(JsonBusqueda, DatosResultados.class);
                    Optional<DatosLibro> libroBuscado = datosBusqueda.resultado().stream()
                            .filter(l -> l.titulo().toUpperCase().contains(opcionLibro.toUpperCase()))
                            .findFirst();

                    if (libroBuscado.isPresent()) {
                        Libro libro = new Libro(libroBuscado.get());
                        Optional<Libro> libroExistente=libroRepository.findByTitulo(libroBuscado.get().titulo());

                        if(libroExistente.isPresent()){
                            System.out.println("El libro ya existe en la base de datos: ");
                        }else {
                            libroRepository.save(libro);
                            System.out.println("Libro registrado"+libro.toString());
                        }

                    } else {
                        System.out.println("El libro no existe");
                    }





        }
        public void listarLibroRegistrados(){
            libroRepository.findAll().forEach(System.out::println);
        }
        public void listarAutoresRegistrados(){
            autorRepository.findAll().forEach(System.out::println);
        }
        public void listarAutoresPorAno(int ano){
            autorRepository.listarAutoresPorAno(ano).forEach(System.out::println);
        }
        public void listarLibrosPorIdioma(String idioma){
            libroRepository.listarLibroPorIdioma(idioma).forEach(System.out::println);
        }







}