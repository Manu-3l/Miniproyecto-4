import java.util.Scanner;

public class App {
    private static Inventario inventario = new Inventario();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        inventario.cargarInventario("inventario.txt");

        boolean continuar = true;

        while (continuar) {
            mostrarMenu();
            int opcion = scanner.nextInt();
            scanner.nextLine();  // Consumir el salto de línea

            switch (opcion) {
                case 1:
                    agregarProducto();
                    break;
                case 2:
                    eliminarProducto();
                    break;
                case 3:
                    buscarProducto();
                    break;
                case 4:
                    listarProductos();
                    break;
                case 5:
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
        System.out.println("2. Eliminar producto");
        System.out.println("3. Buscar producto");
        System.out.println("4. Listar productos");
        System.out.println("5. Guardar inventario y salir");
        System.out.print("Seleccione una opción: ");
    }

    private static void agregarProducto() {
        System.out.print("Ingrese el código del producto: ");
        String codigo = scanner.nextLine();
        System.out.print("Ingrese la cantidad: ");
        int cantidad = scanner.nextInt();
        scanner.nextLine();  // Consumir el salto de línea

        inventario.agregarProducto(codigo, cantidad);
        System.out.println("Producto agregado exitosamente.");
    }

    private static void eliminarProducto() {
        System.out.print("Ingrese el código del producto a eliminar: ");
        String codigo = scanner.nextLine();

        inventario.eliminarProducto(codigo);
        System.out.println("Producto eliminado exitosamente.");
    }

    private static void buscarProducto() {
        System.out.print("Ingrese el código del producto a buscar: ");
        String codigo = scanner.nextLine();

        Integer cantidad = inventario.buscarProducto(codigo);
        if (cantidad != null) {
            System.out.println("Cantidad del producto " + codigo + ": " + cantidad);
        } else {
            System.out.println("Producto no encontrado.");
        }
    }

    private static void listarProductos() {
        inventario.listarProductos();
    }

    private static void guardarInventario() {
        inventario.guardarInventario("inventario.txt");
        System.out.println("Inventario guardado exitosamente en inventario.txt");
    }
}
