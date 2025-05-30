package edu.iudigital.cajas.model;

// Clase utilitaria para acumular el tiempo total de cobro de todas las compras.
public class CobroGlobal {
    private static long tiempoTotal = 0;

    public static synchronized void agregarTiempo(long tiempo) {
        tiempoTotal += tiempo;
    }

    public static long getTiempoTotal() {
        return tiempoTotal;
    }
}
