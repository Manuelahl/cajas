package edu.iudigital.cajas.model;

import java.util.List;
import java.util.Arrays;

public class CatalogoProducto {
    public static final List<Producto> PRODUCTOS = Arrays.asList(
        new Producto("Leche", 2.50, 1500),
        new Producto("Pan", 1.00, 500),
        new Producto("Arroz", 1.80, 1000),
        new Producto("Huevos", 3.20, 1200)
    );

    public static Producto buscarPorNombre(String nombre) {
        return PRODUCTOS.stream()
            .filter(p -> p.getNombre().equalsIgnoreCase(nombre))
            .findFirst()
            .orElse(null);
    }
}
