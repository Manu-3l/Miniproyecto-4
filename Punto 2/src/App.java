import java.util.InputMismatchException;
import java.util.Scanner;

// Definición de la clase principal de la aplicación
public class App {
    // Instancia de Inventario para gestionar los productos
    private static Inventario inventario = new Inventario();
    // Scanner para leer la entrada del usuario
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        try {
            // Intenta cargar el inventario desde un archivo al inicio
            inventario.cargarInventario("inventario.txt");
        } catch (Exception e) {
            System.out.println("Error al cargar el inventario inicial: " + e.getMessage());
        }

        // Bucle para mantener el programa ejecutándose hasta que el usuario decida salir
        boolean continuar = true;

        while (continuar) {
            // Muestra el menú de opciones al usuario
            mostrarMenu();
            int opcion = -1;
            try {
                // Lee la opción seleccionada por el usuario
                opcion = scanner.nextInt();
                scanner.nextLine(); // Consumir el salto de línea
            } catch (InputMismatchException e) {
                System.out.println("Opción no válida. Por favor, ingrese un número.");
                scanner.nextLine(); // Limpiar el buffer
                continue;
            }

            // Ejecuta la acción correspondiente a la opción seleccionada
            switch (opcion) {
                case 1:
                    agregarProducto();
                    break;
                case 2:
                    actualizarProducto();
                    break;
                case 3:
                    eliminarProducto();
                    break;
                case 4:
                    buscarProducto();
                    break;
                case 5:
                    listarProductos();
                    break;
                case 6:
                    guardarInventario();
                    continuar = false; // Finaliza el bucle y cierra la aplicación
                    break;
                default:
                    System.out.println("Opción no válida. Intente de nuevo.");
            }
        }

        System.out.println("Gracias por usar el sistema de gestión de inventario.");

    }

    // Método para mostrar el menú de opciones al usuario
    private static void mostrarMenu() {
        System.out.println("------------------------------------");
        System.out.println("Sistema de Gestión de Inventario");
        System.out.println("------------------------------------");
        System.out.println("1. Agregar producto");
        System.out.println("2. Actualizar producto");
        System.out.println("3. Eliminar producto");
        System.out.println("4. Buscar producto");
        System.out.println("5. Listar productos");
        System.out.println("6. Guardar inventario y salir");
        System.out.println("------------------------------------");
        System.out.print("Seleccione una opción:\n");
    }

    // Método para agregar un producto al inventario
    private static void agregarProducto() {
        try {
            System.out.println("------------------------------------");
            System.out.print("Ingrese el código del producto:\n");
            String codigo = scanner.nextLine();
            System.out.println("------------------------------------");
            System.out.print("Ingrese el nombre del producto:\n");
            String nombre = scanner.nextLine();
            System.out.println("------------------------------------");
            System.out.print("Ingrese la cantidad:\n");
            int cantidad = scanner.nextInt();
            System.out.println("------------------------------------");
            scanner.nextLine(); // Consumir el salto de línea

            inventario.agregarProducto(codigo, nombre, cantidad);
        } catch (InputMismatchException e) {
            System.out.println("Cantidad no válida. Por favor, ingrese un número.");
            scanner.nextLine(); // Limpiar el buffer
        } catch (Exception e) {
            System.out.println("Error al agregar producto: " + e.getMessage());
        }
    }

    // Método para actualizar un producto existente en el inventario
    private static void actualizarProducto() {
        try {
            System.out.println("------------------------------------");
            System.out.print("Ingrese el código del producto:\n");
            String codigo = scanner.nextLine();
            System.out.println("------------------------------------");
            System.out.print("Ingrese el nuevo nombre:\n");
            String nombre = scanner.nextLine();
            System.out.println("------------------------------------");
            System.out.print("Ingrese la nueva cantidad:\n");
            int cantidad = scanner.nextInt();
            System.out.println("------------------------------------");
            scanner.nextLine(); // Consumir el salto de línea

            inventario.actualizarProducto(codigo, nombre, cantidad);
        } catch (InputMismatchException e) {
            System.out.println("Cantidad no válida. Por favor, ingrese un número.");
            scanner.nextLine(); // Limpiar el buffer
        } catch (Exception e) {
            System.out.println("Error al actualizar producto: " + e.getMessage());
        }
    }

    // Método para eliminar un producto del inventario
    private static void eliminarProducto() {
        System.out.println("------------------------------------");
        System.out.print("Ingrese el código del producto a eliminar:\n");
        String codigo = scanner.nextLine();
        System.out.println("------------------------------------");
        try {
            inventario.eliminarProducto(codigo);
        } catch (Exception e) {
            System.out.println("Error al eliminar producto: " + e.getMessage());
        }
    }

    // Método para buscar un producto en el inventario
    private static void buscarProducto() {
        System.out.println("------------------------------------");
        System.out.print("Ingrese el código del producto a buscar:\n");
        String codigo = scanner.nextLine();
        System.out.println("------------------------------------");
        try {
            Producto producto = inventario.buscarProducto(codigo);
            if (producto != null) {
                System.out.println("------------------------------------\n");
                System.out.println("Código: " + producto.getCodigo() + ", Nombre: " + producto.getNombre() + ", Cantidad: " + producto.getCantidad());
                System.out.println("\n------------------------------------");
            } else {
                System.out.println("Producto no encontrado.");
            }
        } catch (Exception e) {
            System.out.println("Error al buscar producto: " + e.getMessage());
        }
    }

    // Método para listar todos los productos en el inventario
    private static void listarProductos() {
        try {
            System.out.println("------------------------------------");
            inventario.listarProductos();
            System.out.println("----------------------------------------------------------------\n");
        } catch (Exception e) {
            System.out.println("Error al listar productos: " + e.getMessage());
        }
    }

    // Método para guardar el inventario en un archivo y finalizar la aplicación
    private static void guardarInventario() {
        try {
            inventario.guardarInventario("inventario.txt");
        } catch (Exception e) {
            System.out.println("Error al guardar el inventario: " + e.getMessage());
        }
    }
}
