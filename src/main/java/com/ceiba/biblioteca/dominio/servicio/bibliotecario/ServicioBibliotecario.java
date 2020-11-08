package com.ceiba.biblioteca.dominio.servicio.bibliotecario;

import com.ceiba.biblioteca.dominio.Libro;
import com.ceiba.biblioteca.dominio.Prestamo;
import com.ceiba.biblioteca.dominio.excepcion.PrestamoException;
import com.ceiba.biblioteca.dominio.repositorio.RepositorioLibro;
import com.ceiba.biblioteca.dominio.repositorio.RepositorioPrestamo;

public class ServicioBibliotecario {

    public static final String EL_LIBRO_NO_SE_ENCUENTRA_DISPONIBLE = "El libro no se encuentra disponible";

    private final RepositorioLibro repositorioLibro;
    private final RepositorioPrestamo repositorioPrestamo;

    public ServicioBibliotecario(RepositorioLibro repositorioLibro, RepositorioPrestamo repositorioPrestamo) {
        this.repositorioLibro = repositorioLibro;
        this.repositorioPrestamo = repositorioPrestamo;
    }

    public void prestar(Prestamo prestamo) {
        if(!esPrestado(prestamo.getLibro().getIsbn()))
            this.repositorioPrestamo.agregar(prestamo);
        else
            throw new PrestamoException(EL_LIBRO_NO_SE_ENCUENTRA_DISPONIBLE);
    }

    public boolean esPrestado(String isbn) {
        Libro libro = this.repositorioPrestamo.obtenerLibroPrestadoPorIsbn(isbn);
        return (libro == null) ? false : true;
    }
}
