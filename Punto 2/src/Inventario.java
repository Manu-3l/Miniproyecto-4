import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

public class Inventario {
    private HashMap<String, Integer> productos;

    public Inventario() {
        productos = new HashMap<>();
    }

    public void agregarProducto(String codigo, int cantidad) {
        productos.put(codigo, productos.getOrDefault(codigo, 0) + cantidad);
    }

    public void eliminarProducto(String codigo) {
        productos.remove(codigo);
    }

    public void actualizarProducto(String codigo, int cantidad) {
        if (productos.containsKey(codigo)) {
            productos.put(codigo, cantidad);
        } else {
            System.out.println("Producto con código " + codigo + " no encontrado.");
        }
    }

    public Integer buscarProducto(String codigo) {
        return productos.getOrDefault(codigo, null);
    }

    public void listarProductos() {
        if (productos.isEmpty()) {
            System.out.println("No hay productos en el inventario.");
        } else {
            System.out.println("Listado de productos:");
            for (String codigo : productos.keySet()) {
                System.out.println("Código: " + codigo + ", Cantidad: " + productos.get(codigo));
            }
        }
    }

    public void guardarInventario(String nombreArchivo) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(nombreArchivo))) {
            for (String codigo : productos.keySet()) {
                writer.write(codigo + ": " + productos.get(codigo));
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error al guardar el inventario: " + e.getMessage());
        }
    }

    public void cargarInventario(String nombreArchivo) {
        try (BufferedReader reader = new BufferedReader(new FileReader(nombreArchivo))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                String[] partes = linea.split(": ");
                if (partes.length == 2) {
                    String codigo = partes[0];
                    int cantidad = Integer.parseInt(partes[1]);
                    productos.put(codigo, cantidad);
                }
            }
        } catch (IOException e) {
            System.out.println("Error al cargar el inventario: " + e.getMessage());
        }
    }
}
