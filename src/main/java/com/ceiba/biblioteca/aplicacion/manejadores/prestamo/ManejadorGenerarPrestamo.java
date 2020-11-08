package com.ceiba.biblioteca.aplicacion.manejadores.prestamo;

import com.ceiba.biblioteca.aplicacion.comando.ComandoLibro;
import com.ceiba.biblioteca.aplicacion.fabrica.FabricaLibro;
import com.ceiba.biblioteca.aplicacion.fabrica.FabricaPrestamo;
import com.ceiba.biblioteca.dominio.Libro;
import com.ceiba.biblioteca.dominio.Prestamo;
import com.ceiba.biblioteca.dominio.servicio.bibliotecario.ServicioBibliotecario;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class ManejadorGenerarPrestamo {

    private final ServicioBibliotecario servicioBibliotecario;
    private final FabricaPrestamo fabricaPrestamo;
    private final FabricaLibro fabricaLibro;

    public ManejadorGenerarPrestamo(ServicioBibliotecario servicioBibliotecario, FabricaPrestamo fabricaPrestamo, FabricaLibro fabricaLibro) {
        this.servicioBibliotecario = servicioBibliotecario;
        this.fabricaPrestamo = fabricaPrestamo;
        this.fabricaLibro = fabricaLibro;

    }

    @Transactional
    public void ejecutar(ComandoLibro comandoLibro, String nombreCliente) {
        Libro libro = this.fabricaLibro.crearLibro(comandoLibro);
        Prestamo prestamo = this.fabricaPrestamo.crearPrestamo(libro,nombreCliente);
        this.servicioBibliotecario.prestar(prestamo);
    }
}
