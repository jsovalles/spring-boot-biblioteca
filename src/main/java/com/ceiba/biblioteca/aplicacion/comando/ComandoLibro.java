package com.ceiba.biblioteca.aplicacion.comando;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ComandoLibro {

    private final String isbn;
    private final String titulo;
    private final int anio;

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public ComandoLibro(@JsonProperty("isbn") String isbn, @JsonProperty("titulo") String titulo, @JsonProperty("anio") int anio) {
        this.isbn = isbn;
        this.titulo = titulo;
        this.anio = anio;
    }

    public String getIsbn() {
        return isbn;
    }

    public String getTitulo() {
        return titulo;
    }

    public int getAnio() {
        return anio;
    }
}

