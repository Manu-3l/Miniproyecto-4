import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

public class Inventario {
    private HashMap<String, Producto> productos;

    public Inventario() {
        productos = new HashMap<>();
    }


    public void agregarProducto(String codigo, String nombre, int cantidad) {
        try {
            if (productos.containsKey(codigo)) {
                System.out.println("Ya existe un producto con este código.");
                System.out.println("Intenta añadir un producto con un código diferente.");
            } else {
                productos.put(codigo, new Producto(codigo, nombre, cantidad));
                System.out.println("Producto agregado exitosamente.");
            }
        } catch (Exception e) {
            System.out.println("Error al agregar producto: " + e.getMessage());
        }
    }


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

    
    public void actualizarProducto(String codigo, String nombre, int cantidad) {
        try {
            if (productos.containsKey(codigo)) {
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


    public Producto buscarProducto(String codigo) {
        try {
            return productos.getOrDefault(codigo, null);
        } catch (Exception e) {
            System.out.println("Error al buscar producto: " + e.getMessage());
            return null;
        }
    }


    public void listarProductos() {
        try {
            if (productos.isEmpty()) {
                System.out.println("No hay productos en el inventario.");
            } else {
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


    public void guardarInventario(String nombreArchivo) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(nombreArchivo))) {
            for (Producto producto : productos.values()) {
                writer.write(producto.getCodigo() + ": " + producto.getNombre() + ": " + producto.getCantidad());
                writer.newLine();
            }
            System.out.println("Inventario guardado exitosamente en " + nombreArchivo);
        } catch (IOException e) {
            System.out.println("Error al guardar el inventario: " + e.getMessage());
        }
    }


    public void cargarInventario(String nombreArchivo) {
        try (BufferedReader reader = new BufferedReader(new FileReader(nombreArchivo))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                String[] partes = linea.split(": ");
                if (partes.length == 3) {
                    String codigo = partes[0];
                    String nombre = partes[1];
                    int cantidad = Integer.parseInt(partes[2]);
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
