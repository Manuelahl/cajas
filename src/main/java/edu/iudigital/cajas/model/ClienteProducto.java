package edu.iudigital.cajas.model;

import java.util.List;

public class ClienteProducto {
    private String nombre;
    private List<Producto> productos;

    public ClienteProducto(String nombre, List<Producto> productos) {
        this.nombre = nombre;
        this.productos = productos;
    }

    // Getters
    public String getNombre() { 
        return nombre; 
    }

    public List<Producto> getProductos() { 
        return productos; 
    }
}
