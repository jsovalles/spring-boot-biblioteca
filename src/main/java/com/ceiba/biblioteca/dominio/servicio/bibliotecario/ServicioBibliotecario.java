package com.ceiba.biblioteca.dominio.servicio.bibliotecario;

import com.ceiba.biblioteca.dominio.Libro;
import com.ceiba.biblioteca.dominio.Prestamo;
import com.ceiba.biblioteca.dominio.excepcion.PrestamoException;
import com.ceiba.biblioteca.dominio.repositorio.RepositorioLibro;
import com.ceiba.biblioteca.dominio.repositorio.RepositorioPrestamo;

import java.util.Arrays;

public class ServicioBibliotecario {

    public static final String EL_LIBRO_NO_SE_ENCUENTRA_DISPONIBLE = "El libro no se encuentra disponible";
    public static final String EL_LIBRO_ES_PALINDROMO = "los libros palíndromos solo se pueden utilizar en la biblioteca";

    private final RepositorioLibro repositorioLibro;
    private final RepositorioPrestamo repositorioPrestamo;

    public ServicioBibliotecario(RepositorioLibro repositorioLibro, RepositorioPrestamo repositorioPrestamo) {
        this.repositorioLibro = repositorioLibro;
        this.repositorioPrestamo = repositorioPrestamo;
    }

    public void prestar(Prestamo prestamo) {
        if (!esPrestado(prestamo.getLibro().getIsbn()) && !esPalindromo(prestamo.getLibro().getIsbn()))
            this.repositorioPrestamo.agregar(prestamo);
        else if (esPalindromo(prestamo.getLibro().getIsbn()))
            throw new PrestamoException(EL_LIBRO_ES_PALINDROMO);
        else
            throw new PrestamoException(EL_LIBRO_NO_SE_ENCUENTRA_DISPONIBLE);
    }

    public boolean esPrestado(String isbn) {
        Libro libro = this.repositorioPrestamo.obtenerLibroPrestadoPorIsbn(isbn);
        return (libro == null) ? false : true;
    }

    public boolean esPalindromo(String isbn) {
        return isbn.equals(new StringBuilder(isbn).reverse().toString());
    }
}
