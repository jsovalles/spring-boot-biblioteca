package com.ceiba.biblioteca.aplicacion.fabrica;

import com.ceiba.biblioteca.dominio.Libro;
import com.ceiba.biblioteca.dominio.Prestamo;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class FabricaPrestamo {
    public Prestamo crearPrestamo(Libro libro, String nombreCliente){
        return new Prestamo(new Date(), libro, null, nombreCliente);
    }
}
