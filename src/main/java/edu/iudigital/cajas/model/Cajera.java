package edu.iudigital.cajas.model;

import java.util.Queue;

// Se implementa Runnable para ejecutarse en un hilo separado
public class Cajera implements Runnable {
    private String nombre;
    private Queue<ClienteProducto> colaClientes;

    public Cajera(String nombre, Queue<ClienteProducto> colaClientes) {
        this.nombre = nombre;
        this.colaClientes = colaClientes;
    }

    @Override
    public void run() {
        while (!colaClientes.isEmpty()) {
            ClienteProducto cliente;
            synchronized (colaClientes) { // Para evitar condiciones de carrera al acceder a la cola de clientes compartida entre múltiples hilos
                cliente = colaClientes.poll();
            }

            if (cliente != null) {
                procesarCompra(cliente);
            }
        }
    }

    private void procesarCompra(ClienteProducto cliente) {
        System.out.println("\n[CAJERA " + nombre + "] Atendiendo a " + cliente.getNombre());
        double total = 0;
        long tiempoCliente = 0;

        for (Producto producto : cliente.getProductos()) {
            try {
                Thread.sleep(producto.getTiempoProcesamiento()); // Simula el tiempo de procesamiento del producto (escaneo)
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }

            // Mostrar producto ACTUAL del cliente correcto
            System.out.printf("[%s] %s: $%.2f (%.2fs)\n", 
                nombre, producto.getNombre(), producto.getPrecio(),
                producto.getTiempoProcesamiento() / 1000.0);

            total += producto.getPrecio();
            tiempoCliente += producto.getTiempoProcesamiento();
        }

        System.out.printf("[%s] Tiempo total: %.2fs | Total cobrado: $%.2f\n",
            nombre, tiempoCliente / 1000.0, total);

        CobroGlobal.agregarTiempo(tiempoCliente); // Acumular tiempo global
    }
}
