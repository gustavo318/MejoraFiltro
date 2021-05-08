package com.jgmayer.recycler.model;

import java.io.Serializable;

public class ItemList implements Serializable {
    private String nombre;
    private String descripcion;
    private String contacto;
    private int imgResource;

    public ItemList(String nombre, String descripcion,String contacto, int imgResource) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.contacto = contacto;
        this.imgResource = imgResource;
    }



    public String getNombre() {
        return nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getContacto() {
        return contacto;
    }

    public int getImgResource() {
        return imgResource;
    }
}




