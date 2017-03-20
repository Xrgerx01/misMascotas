package com.idigital.mismascotas;

/**
 * Created by Roger on 19/03/2017.
 */

public class Mascotas {

    private int foto;
    private String nombre;
    private int favorito;

    public Mascotas(int foto, String nombre, int favorito) {
        this.foto = foto;
        this.nombre = nombre;
        this.favorito = favorito;
    }

    public int getFoto() {
        return foto;
    }

    public void setFoto(int foto) {
        this.foto = foto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getFavorito() {
        return favorito;
    }

    public void setFavorito(int favorito) {
        this.favorito = favorito;
    }
}
