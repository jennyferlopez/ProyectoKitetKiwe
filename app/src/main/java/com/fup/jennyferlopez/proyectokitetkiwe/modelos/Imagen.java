package com.fup.jennyferlopez.proyectokitetkiwe.modelos;

/**
 * Created by Yuri on 16/04/2016.
 */
public class Imagen {

    private int id;
    private int id_c;
    private String direccion;

    public  Imagen() {}

    public Imagen(int id, int id_c, String direccion) {
        this.id = id;
        this.id_c = id_c;
        this.direccion = direccion;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getId_c() { return id_c; }
    public void setId_c(int id_c) { this.id_c = id_c; }

    public String getDireccion() { return direccion; }
    public void setDireccion(String direccion) { this.direccion = direccion; }
}
