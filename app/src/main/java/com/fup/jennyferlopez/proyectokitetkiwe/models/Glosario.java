package com.fup.jennyferlopez.proyectokitetkiwe.models;

/**
 * Creado por Hermosa Programación
 */
public class Glosario {
    private int imagen;
    private String nombre;
    private String nombreKitet;

    public Glosario(int imagen, String nombre, String nombreKitet) {
        this.imagen = imagen;
        this.nombre = nombre;
        this.nombreKitet = nombreKitet;
    }

    public int getImagen() {
        return imagen;
    }

    public void setImagen(int imagen) {
        this.imagen = imagen;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNombreKitet() {
        return nombreKitet;
    }

    public void setNombreKitet(String nombreKitet) {
        this.nombreKitet = nombreKitet;
    }
}
