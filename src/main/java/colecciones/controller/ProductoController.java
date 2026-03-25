package colecciones.controller;

import colecciones.model.Producto;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ProductoController {

    private static final ObservableList<Producto> PRODUCTOS = FXCollections.observableArrayList();

    public ProductoController() {
    }

    public void registrarProducto(String nombre, String tipo, double precio, int cantidad, String descripcion) {
        PRODUCTOS.add(new Producto(nombre, tipo, precio, cantidad, descripcion));
    }

    public ObservableList<Producto> obtenerProductos() {
        return PRODUCTOS;
    }

    public Producto consultarProducto(int indice) {
        return PRODUCTOS.get(indice);
    }

    public void actualizarProducto(int indice, String nombre, String tipo, double precio, int cantidad,
            String descripcion) {
        PRODUCTOS.set(indice, new Producto(nombre, tipo, precio, cantidad, descripcion));
    }

    public void eliminarProducto(int indice) {
        PRODUCTOS.remove(indice);
    }

    public void eliminarProductos() {
        PRODUCTOS.clear();
    }
}