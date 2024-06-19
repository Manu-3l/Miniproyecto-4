public class Producto {
    private String nombre;
    private int cantidad;
    private String codigo;

    public Producto(String codigo, String nombre, int cantidad) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.cantidad = cantidad;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setNombre(String nombre){
        this.nombre = nombre;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
}
