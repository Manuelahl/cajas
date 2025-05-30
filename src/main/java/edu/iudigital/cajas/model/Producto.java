package edu.iudigital.cajas.model;

public class Producto {
    private String nombre;
    private double precio;
    private int tiempoProcesamiento; // en milisegundos

    public Producto(String nombre, double precio, int tiempoProcesamiento) {
        this.nombre = nombre;
        this.precio = precio;
        this.tiempoProcesamiento = tiempoProcesamiento;
    }

    // Getters (no setters para inmutabilidad)
    public String getNombre() { 
        return nombre; 
    }

    public double getPrecio() { 
        return precio; 
    }

    public int getTiempoProcesamiento() { 
        return tiempoProcesamiento; 
    }
}
