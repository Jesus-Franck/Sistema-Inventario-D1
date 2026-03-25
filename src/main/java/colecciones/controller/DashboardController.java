package colecciones.controller;

import java.net.URL;
import java.util.ResourceBundle;

import colecciones.model.Producto;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class DashboardController implements Initializable {

    @FXML
    private Button btnAgregarProducto;
    @FXML
    private Button btnEditarProducto;
    @FXML
    private Button btnEliminarProducto;
    @FXML
    private Button btnEliminarProductos;

    @FXML
    private ComboBox<String> cmbTipoProducto;

    @FXML
    private ImageView imgLogo;

    @FXML
    private Label lblCantidad;
    @FXML
    private Label lblDescripcion;
    @FXML
    private Label lblNombreProducto;
    @FXML
    private Label lblPrecio;
    @FXML
    private Label lblText;
    @FXML
    private Label lblTipoProducto;

    @FXML
    private Spinner<Double> spnPrecio;
    @FXML
    private Spinner<Integer> spnCantidad;

    @FXML
    private TableView<Producto> tblProductos;
    @FXML
    private TableColumn<Producto, String> tbNOMBRE;
    @FXML
    private TableColumn<Producto, String> tbcTIPO;
    @FXML
    private TableColumn<Producto, Double> tbcPRECIO;
    @FXML
    private TableColumn<Producto, Integer> tbcCANTIDAD;
    @FXML
    private TableColumn<Producto, String> tbcDESCRIPCION;

    @FXML
    private TextField txtNombreProducto;
    @FXML
    private TextArea txtDescripcion;

    private final ProductoController productoController = new ProductoController();
    private int indiceSeleccionado = -1;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cmbTipoProducto.getItems().addAll(
                "Abarrotes y Despensa",
                "Lácteos y Huevos",
                "Carnes y Embutidos",
                "Panadería y Galletas",
                "Bebidas y Jugos",
                "Snacks y Mecato",
                "Aseo Personal",
                "Aseo del Hogar");

        spnPrecio.setValueFactory(
                new SpinnerValueFactory.DoubleSpinnerValueFactory(0, 9_999_999, 0, 500));
        spnPrecio.setEditable(true);

        spnCantidad.setValueFactory(
                new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 9_999, 0, 1));
        spnCantidad.setEditable(true);

        tbNOMBRE.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        tbcTIPO.setCellValueFactory(new PropertyValueFactory<>("tipo"));
        tbcPRECIO.setCellValueFactory(new PropertyValueFactory<>("precio"));
        tbcCANTIDAD.setCellValueFactory(new PropertyValueFactory<>("cantidad"));
        tbcDESCRIPCION.setCellValueFactory(new PropertyValueFactory<>("descripcion"));

        tblProductos.setItems(productoController.obtenerProductos());

        btnEditarProducto.setDisable(true);
        btnEliminarProducto.setDisable(true);

        tblProductos.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal != null) {
                indiceSeleccionado = tblProductos.getSelectionModel().getSelectedIndex();
                txtNombreProducto.setText(newVal.getNombre());
                cmbTipoProducto.setValue(newVal.getTipo());
                spnPrecio.getValueFactory().setValue(newVal.getPrecio());
                spnCantidad.getValueFactory().setValue(newVal.getCantidad());
                txtDescripcion.setText(newVal.getDescripcion());
                btnEditarProducto.setDisable(false);
                btnEliminarProducto.setDisable(false);
            }
        });
    }

    @FXML
    void agregarProducto(ActionEvent event) {
        if (!validar())
            return;

        productoController.registrarProducto(
                txtNombreProducto.getText().trim(),
                cmbTipoProducto.getValue(),
                spnPrecio.getValue(),
                spnCantidad.getValue(),
                txtDescripcion.getText().trim());

        mostrarAlerta(Alert.AlertType.INFORMATION, "Producto agregado correctamente.");
        limpiarFormulario();
    }

    @FXML
    void editarProducto(ActionEvent event) {
        if (indiceSeleccionado < 0) {
            mostrarAlerta(Alert.AlertType.WARNING, "Seleccione un producto de la tabla.");
            return;
        }
        if (!validar())
            return;

        productoController.actualizarProducto(
                indiceSeleccionado,
                txtNombreProducto.getText().trim(),
                cmbTipoProducto.getValue(),
                spnPrecio.getValue(),
                spnCantidad.getValue(),
                txtDescripcion.getText().trim());

        mostrarAlerta(Alert.AlertType.INFORMATION, "Producto actualizado correctamente.");
        limpiarFormulario();
    }

    @FXML
    void eliminarProducto(ActionEvent event) {
        if (indiceSeleccionado < 0) {
            mostrarAlerta(Alert.AlertType.WARNING, "Seleccione un producto de la tabla.");
            return;
        }

        productoController.eliminarProducto(indiceSeleccionado);
        mostrarAlerta(Alert.AlertType.INFORMATION, "Producto eliminado correctamente.");
        limpiarFormulario();
    }

    @FXML
    void eliminarProductos(ActionEvent event) {
        if (productoController.obtenerProductos().isEmpty()) {
            mostrarAlerta(Alert.AlertType.WARNING, "No hay productos para eliminar.");
            return;
        }

        Alert confirm = new Alert(Alert.AlertType.CONFIRMATION,
                "¿Desea eliminar TODOS los productos?",
                ButtonType.YES, ButtonType.NO);
        confirm.setTitle("Confirmar");
        confirm.showAndWait().ifPresent(resp -> {
            if (resp == ButtonType.YES) {
                productoController.eliminarProductos();
                mostrarAlerta(Alert.AlertType.INFORMATION, "Todos los productos eliminados.");
                limpiarFormulario();
            }
        });
    }

    @FXML
    void seleccionarFila(MouseEvent event) {
    }

    @FXML
    void cantidad(MouseEvent event) {
    }

    @FXML
    void precio(MouseEvent event) {
    }

    @FXML
    void tipoProductos(ActionEvent event) {
    }

    private boolean validar() {
        if (txtNombreProducto.getText().trim().isEmpty()) {
            mostrarAlerta(Alert.AlertType.WARNING, "Ingrese el nombre del producto.");
            return false;
        }
        if (cmbTipoProducto.getValue() == null) {
            mostrarAlerta(Alert.AlertType.WARNING, "Seleccione el tipo de producto.");
            return false;
        }
        if (txtDescripcion.getText().trim().isEmpty()) {
            mostrarAlerta(Alert.AlertType.WARNING, "Ingrese la descripción del producto.");
            return false;
        }
        return true;
    }

    private void limpiarFormulario() {
        txtNombreProducto.clear();
        txtDescripcion.clear();
        cmbTipoProducto.setValue(null);
        spnPrecio.getValueFactory().setValue(0.0);
        spnCantidad.getValueFactory().setValue(0);
        tblProductos.getSelectionModel().clearSelection();
        indiceSeleccionado = -1;
        btnEditarProducto.setDisable(true);
        btnEliminarProducto.setDisable(true);
    }

    private void mostrarAlerta(Alert.AlertType tipo, String mensaje) {
        Alert alert = new Alert(tipo, mensaje, ButtonType.OK);
        alert.setHeaderText(null);
        alert.showAndWait();
    }
}