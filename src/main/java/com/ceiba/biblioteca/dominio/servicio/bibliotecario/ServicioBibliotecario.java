package com.ceiba.biblioteca.dominio.servicio.bibliotecario;

import com.ceiba.biblioteca.dominio.Libro;
import com.ceiba.biblioteca.dominio.Prestamo;
import com.ceiba.biblioteca.dominio.excepcion.PrestamoException;
import com.ceiba.biblioteca.dominio.repositorio.RepositorioLibro;
import com.ceiba.biblioteca.dominio.repositorio.RepositorioPrestamo;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.Date;

public class ServicioBibliotecario {

    public static final String EL_LIBRO_NO_SE_ENCUENTRA_DISPONIBLE = "El libro no se encuentra disponible";
    public static final String EL_LIBRO_ES_PALINDROMO = "los libros palíndromos solo se pueden utilizar en la biblioteca";
    public static final String EL_LIBRO_NO_EXISTE = "El libro no existe en la base de datos, por favor verificar";

    final static int DIAS_MAXIMO_ENTREGA = 15;
    final static int SUMA_MAXIMA_ISBN = 30;

    private final RepositorioLibro repositorioLibro;
    private final RepositorioPrestamo repositorioPrestamo;

    public ServicioBibliotecario(RepositorioLibro repositorioLibro, RepositorioPrestamo repositorioPrestamo) {
        this.repositorioLibro = repositorioLibro;
        this.repositorioPrestamo = repositorioPrestamo;
    }

    public void prestar(String isbn, Prestamo prestamo) {
        excepcionesPrestamo(isbn);
        prestamo.setFechaEntregaMaxima(fechaEntregaMaxima(isbn));
        this.repositorioPrestamo.agregar(prestamo);
    }

    public boolean esPrestado(String isbn) {
        Libro libro = this.repositorioPrestamo.obtenerLibroPrestadoPorIsbn(isbn);
        return (libro == null) ? false : true;
    }

    public boolean esPalindromo(String isbn) {
        return isbn.equals(new StringBuilder(isbn).reverse().toString());
    }

    public Date fechaEntregaMaxima(String isbn) {
        int sumaNumeros = Arrays.stream(isbn.split("")).filter(s -> s.matches("[0-9]+")).mapToInt(Integer::parseInt).sum();
        return (sumaNumeros >= SUMA_MAXIMA_ISBN) ? calcularFechaEntrega(LocalDate.now()) : null;
    }

    public Date calcularFechaEntrega(LocalDate fechaSolicitud) {
        LocalDate fechaEntrega = fechaSolicitud;
        int contadorDias = 1;
        while (contadorDias < DIAS_MAXIMO_ENTREGA) {
            fechaEntrega = fechaEntrega.plusDays(1);
            if (!(fechaEntrega.getDayOfWeek() == DayOfWeek.SUNDAY)) {
                contadorDias++;
            }
        }
        return Date.from(fechaEntrega.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
    }

    public void excepcionesPrestamo(String isbn) {

        Libro libro = repositorioLibro.obtenerPorIsbn(isbn);

        if (libro == null)
            throw new PrestamoException(EL_LIBRO_NO_EXISTE);
        if (esPalindromo(isbn))
            throw new PrestamoException(EL_LIBRO_ES_PALINDROMO);
        else if (esPrestado(isbn))
            throw new PrestamoException(EL_LIBRO_NO_SE_ENCUENTRA_DISPONIBLE);

    }
}
