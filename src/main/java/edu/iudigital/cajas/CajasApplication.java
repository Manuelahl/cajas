package edu.iudigital.cajas;

import edu.iudigital.cajas.model.*;
import java.util.*;
import java.util.concurrent.*;

public class CajasApplication {
    public static void main(String[] args) throws InterruptedException {
        Scanner scanner = new Scanner(System.in); // Advertencia ignorada deliberadamente
        Queue<ClienteProducto> colaClientes = new LinkedList<>();

        // 1. Mostrar productos
        System.out.println("PRODUCTOS DISPONIBLES:");
        CatalogoProducto.PRODUCTOS.forEach(p -> 
            System.out.printf("- %s: $%.2f\n", p.getNombre(), p.getPrecio())
        );

        // 2. Ingresar clientes
        System.out.print("\nNúmero de clientes: ");
        int numClientes = scanner.nextInt();
        scanner.nextLine();

        for (int i = 0; i < numClientes; i++) {
            System.out.print("\nNombre del cliente " + (i + 1) + ": ");
            String nombre = scanner.nextLine();
            List<Producto> productos = new ArrayList<>();

            // 3. Selección de productos
            while (true) {
                System.out.print("Producto a añadir (o 'fin'): ");
                String input = scanner.nextLine();
                if (input.equalsIgnoreCase("fin")) break;

                Producto producto = CatalogoProducto.buscarPorNombre(input);
                if (producto != null) {
                    productos.add(producto);
                    System.out.println("Añadido: " + producto.getNombre());
                } else {
                    System.out.println("Producto no válido.");
                }
            }

            colaClientes.add(new ClienteProducto(nombre, productos));
        }

        // 4. Cajeras procesan la compra 
        System.out.print("\nNúmero de cajeras: ");
        int numCajeras = scanner.nextInt();
        ExecutorService executor = Executors.newFixedThreadPool(numCajeras); // Solo una declaración

        for (int i = 0; i < numCajeras; i++) {
            executor.execute(new Cajera("Cajera-" + (i + 1), colaClientes));
        }

        executor.shutdown();
        executor.awaitTermination(1, TimeUnit.MINUTES);

        // 5. Resultados
        System.out.printf("\n TIEMPO TOTAL DE TODAS LAS COMPRAS: %.2f segundos\n",
            CobroGlobal.getTiempoTotal() / 1000.0);
    }
}
