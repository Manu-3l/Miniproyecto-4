import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

public class Inventario {
    // Se utilizo nuevamente el metodo de tablas hash para minimizar el tiempo de busqueda
    // dandole a cada producto un codigo único como llave que lo identifique 
    // Mapa para almacenar productos con su código como clave
    private HashMap<String, Producto> productos;

    // Constructor que inicializa el mapa de productos
    public Inventario() {
        productos = new HashMap<>();
    }

    // Método para agregar un producto al inventario
    public void agregarProducto(String codigo, String nombre, int cantidad) {
        try {
             // Verifica si ya existe un producto con el mismo código
            if (productos.containsKey(codigo)) {
                System.out.println("Ya existe un producto con este código.");
                System.out.println("Intenta añadir un producto con un código diferente.");
            } else {
                // Si no existe, agrega el nuevo producto al mapa
                productos.put(codigo, new Producto(codigo, nombre, cantidad));
                System.out.println("Producto agregado exitosamente.");
            }
        } catch (Exception e) {
            System.out.println("Error al agregar producto: " + e.getMessage());
        }
    }

    // Método para eliminar un producto del inventario
    public void eliminarProducto(String codigo) {
        try {
            if (productos.remove(codigo) != null) {
                System.out.println("Producto eliminado exitosamente.");
            } else {
                System.out.println("Producto con código " + codigo + " no encontrado.");
            }
        } catch (Exception e) {
            System.out.println("Error al eliminar producto: " + e.getMessage());
        }
    }

    // Método para actualizar los datos de un producto
    public void actualizarProducto(String codigo, String nombre, int cantidad) {
        try {
            // Verifica si el producto existe en el mapa
            if (productos.containsKey(codigo)) {
                // Si existe, actualiza su nombre y cantidad
                productos.get(codigo).setNombre(nombre);
                productos.get(codigo).setCantidad(cantidad);
                System.out.println("Producto actualizado exitosamente.");
            } else {
                System.out.println("Producto con código " + codigo + " no fue encontrado.");
            }
        } catch (Exception e) {
            System.out.println("Error al actualizar producto: " + e.getMessage());
        }
    }

    // Método para buscar un producto por su código
    public Producto buscarProducto(String codigo) {
        try {
            // Devuelve el producto si existe o null si no existe
            return productos.getOrDefault(codigo, null);
        } catch (Exception e) {
            System.out.println("Error al buscar producto: " + e.getMessage());
            return null;
        }
    }

    // Método para listar todos los productos en el inventario
    public void listarProductos() {
        try {
            // Verifica si el inventario está vacío
            if (productos.isEmpty()) {
                System.out.println("No hay productos en el inventario.");
            } else {
                // Recorre y muestra cada producto del inventario
                System.out.println("Listado de productos:");
                System.out.println("------------------------------------");
                for (Producto producto : productos.values()) {
                    System.out.println("----------------------------------------------------------------");
                    System.out.println("Código: " + producto.getCodigo() + ", Nombre: " + producto.getNombre() + ", Cantidad: " + producto.getCantidad());
                }
            }
        } catch (Exception e) {
            System.out.println("Error al listar productos: " + e.getMessage());
        }
    }

    // Método para guardar el inventario en un archivo
    public void guardarInventario(String nombreArchivo) {
        // Usa try-with-resources para asegurar que el BufferedWriter se cierre automáticamente
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(nombreArchivo))) {
            // Escribe cada producto en el archivo
            for (Producto producto : productos.values()) {
                writer.write(producto.getCodigo() + ": " + producto.getNombre() + ": " + producto.getCantidad());
                writer.newLine();
            }
            System.out.println("Inventario guardado exitosamente en " + nombreArchivo);
        } catch (IOException e) {
            System.out.println("Error al guardar el inventario: " + e.getMessage());
        }
    }

    // Método para cargar el inventario desde un archivo
    public void cargarInventario(String nombreArchivo) {
        try (BufferedReader reader = new BufferedReader(new FileReader(nombreArchivo))) {
            String linea;
            // Lee cada línea del archivo
            while ((linea = reader.readLine()) != null) {
                // Divide la línea en partes usando ": " como delimitador
                String[] partes = linea.split(": ");
                // Verifica que la línea tenga el formato correcto
                if (partes.length == 3) {
                    String codigo = partes[0];
                    String nombre = partes[1];
                    int cantidad = Integer.parseInt(partes[2]);
                    // Agrega el producto al mapa
                    productos.put(codigo, new Producto(codigo, nombre, cantidad));
                }
            }
        } catch (IOException e) {
            System.out.println("Error al cargar el inventario: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Error en el formato del archivo de inventario: " + e.getMessage());
        }
    }
}
