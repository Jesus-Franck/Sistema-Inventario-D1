package colecciones.model;

public class Producto {
    private String nombre;
    private String tipo;
    private double precio;
    private int cantidad;
    private String descripcion;

    public Producto() {
    }

    public Producto(String nombre, String tipo, double precio, int cantidad, String descripcion) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.precio = precio;
        this.cantidad = cantidad;
        this.descripcion = descripcion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public String toString() {
        return "Producto [nombre=" + nombre + ", tipo=" + tipo + ", precio=" + precio + ", cantidad=" + cantidad
                + ", descripcion=" + descripcion + "]";
    }

}
