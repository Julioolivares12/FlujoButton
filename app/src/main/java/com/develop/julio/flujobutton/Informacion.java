package com.develop.julio.flujobutton;

import java.util.ArrayList;

public class Informacion {
    private String nombre;
    private String tipo;
    private String correo;

    public static ArrayList<Informacion> usuarios = new ArrayList<>();


    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }
}
