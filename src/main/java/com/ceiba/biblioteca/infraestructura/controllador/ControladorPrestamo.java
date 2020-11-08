package com.ceiba.biblioteca.infraestructura.controllador;

import com.ceiba.biblioteca.aplicacion.comando.ComandoLibro;
import com.ceiba.biblioteca.aplicacion.manejadores.prestamo.ManejadorGenerarPrestamo;
import com.ceiba.biblioteca.aplicacion.manejadores.prestamo.ManejadorObtenerPrestamo;
import com.ceiba.biblioteca.dominio.Prestamo;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/prestamos")
public class ControladorPrestamo {

    private final ManejadorGenerarPrestamo manejadorGenerarPrestamo;
    private final ManejadorObtenerPrestamo manejadorObtenerPrestamo;

    public ControladorPrestamo(ManejadorObtenerPrestamo manejadorObtenerPrestamo, ManejadorGenerarPrestamo manejadorGenerarPrestamo) {
        this.manejadorObtenerPrestamo = manejadorObtenerPrestamo;
        this.manejadorGenerarPrestamo = manejadorGenerarPrestamo;
    }

    @PostMapping("/{isbn}/{nombreCliente}")
    public void prestar(@PathVariable(name = "isbn") String isbn, @PathVariable(name = "nombreCliente") String nombreCliente, @RequestBody ComandoLibro comandoLibro) {
        this.manejadorGenerarPrestamo.ejecutar(comandoLibro, nombreCliente);
    }

    @GetMapping("/{isbn}")
    public Prestamo obtenerLibroPrestadoPorIsbn(@PathVariable(name = "isbn") String isbn) {
        return this.manejadorObtenerPrestamo.ejecutar(isbn);
    }

}
