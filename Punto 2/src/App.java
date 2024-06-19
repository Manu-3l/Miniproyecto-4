import java.util.InputMismatchException;
import java.util.Scanner;

public class App {
    private static Inventario inventario = new Inventario();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        try {
            inventario.cargarInventario("inventario.txt");
        } catch (Exception e) {
            System.out.println("Error al cargar el inventario inicial: " + e.getMessage());
        }

        boolean continuar = true;

        while (continuar) {
            mostrarMenu();
            int opcion = -1;
            try {
                opcion = scanner.nextInt();
                scanner.nextLine(); // Consumir el salto de línea
            } catch (InputMismatchException e) {
                System.out.println("Opción no válida. Por favor, ingrese un número.");
                scanner.nextLine(); // Limpiar el buffer
                continue;
            }

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
                    continuar = false;
                    break;
                default:
                    System.out.println("Opción no válida. Intente de nuevo.");
            }
        }

        System.out.println("Gracias por usar el sistema de gestión de inventario.");

    }


    private static void mostrarMenu() {
        System.out.println("\nSistema de Gestión de Inventario");
        System.out.println("1. Agregar producto");
        System.out.println("2. Actualizar producto");
        System.out.println("3. Eliminar producto");
        System.out.println("4. Buscar producto");
        System.out.println("5. Listar productos");
        System.out.println("6. Guardar inventario y salir");
        System.out.print("Seleccione una opción: ");
    }


    private static void agregarProducto() {
        try {
            System.out.print("Ingrese el código del producto: ");
            String codigo = scanner.nextLine();
            System.out.print("Ingrese el nombre del producto: ");
            String nombre = scanner.nextLine();
            System.out.print("Ingrese la cantidad: ");
            int cantidad = scanner.nextInt();
            scanner.nextLine(); // Consumir el salto de línea

            inventario.agregarProducto(codigo, nombre, cantidad);
        } catch (InputMismatchException e) {
            System.out.println("Cantidad no válida. Por favor, ingrese un número.");
            scanner.nextLine(); // Limpiar el buffer
        } catch (Exception e) {
            System.out.println("Error al agregar producto: " + e.getMessage());
        }
    }


    private static void actualizarProducto() {
        try {
            System.out.print("Ingrese el código del producto: ");
            String codigo = scanner.nextLine();
            System.out.print("Ingrese el nuevo nombre: ");
            String nombre = scanner.nextLine();
            System.out.print("Ingrese la nueva cantidad: ");
            int cantidad = scanner.nextInt();
            scanner.nextLine(); // Consumir el salto de línea

            inventario.actualizarProducto(codigo, nombre, cantidad);
        } catch (InputMismatchException e) {
            System.out.println("Cantidad no válida. Por favor, ingrese un número.");
            scanner.nextLine(); // Limpiar el buffer
        } catch (Exception e) {
            System.out.println("Error al actualizar producto: " + e.getMessage());
        }
    }


    private static void eliminarProducto() {
        System.out.print("Ingrese el código del producto a eliminar: ");
        String codigo = scanner.nextLine();
        try {
            inventario.eliminarProducto(codigo);
        } catch (Exception e) {
            System.out.println("Error al eliminar producto: " + e.getMessage());
        }
    }


    private static void buscarProducto() {
        System.out.print("Ingrese el código del producto a buscar: ");
        String codigo = scanner.nextLine();
        try {
            Producto producto = inventario.buscarProducto(codigo);
            if (producto != null) {
                System.out.println("Código: " + producto.getCodigo() + ", Nombre: " + producto.getNombre() + ", Cantidad: " + producto.getCantidad());
            } else {
                System.out.println("Producto no encontrado.");
            }
        } catch (Exception e) {
            System.out.println("Error al buscar producto: " + e.getMessage());
        }
    }


    private static void listarProductos() {
        try {
            inventario.listarProductos();
        } catch (Exception e) {
            System.out.println("Error al listar productos: " + e.getMessage());
        }
    }


    private static void guardarInventario() {
        try {
            inventario.guardarInventario("inventario.txt");
        } catch (Exception e) {
            System.out.println("Error al guardar el inventario: " + e.getMessage());
        }
    }
}
