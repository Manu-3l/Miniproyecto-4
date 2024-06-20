public class Producto {
    // Variables privadas para almacenar los atributos del producto
    private String nombre;
    private int cantidad;
    private String codigo;

    // Constructor de la clase Producto que inicializa los atributos
    public Producto(String codigo, String nombre, int cantidad) {
        this.codigo = codigo;  // Inicializa el código del producto
        this.nombre = nombre;  // Inicializa el nombre del producto
        this.cantidad = cantidad;  // Inicializa la cantidad del producto
    }

    // Getters
    public String getCodigo() {
        return codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public int getCantidad() {
        return cantidad;
    }

    // Setters
    public void setNombre(String nombre){
        this.nombre = nombre;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
}
