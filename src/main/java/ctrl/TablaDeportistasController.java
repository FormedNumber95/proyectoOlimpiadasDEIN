package ctrl;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import model.ModeloDeportista;

public class TablaDeportistasController {

    @FXML
    private TableColumn<ModeloDeportista, Integer> colAltura;

    @FXML
    private Button btnAniadir;

    @FXML
    private Button btnEliminar;

    @FXML
    private Button btnModificar;

    @FXML
    private TableColumn<ModeloDeportista, String> colNombre;

    @FXML
    private TableColumn<ModeloDeportista, Integer>colPeso;

    @FXML
    private TableColumn<ModeloDeportista, String> colSexo;

    @FXML
    private MenuItem menAniadirDeporte;

    @FXML
    private MenuItem menAniadirEquipo;

    @FXML
    private MenuItem menAniadirOlimpiada;

    @FXML
    private MenuItem menEliminarDeporte;

    @FXML
    private MenuItem menEliminarEquipo;

    @FXML
    private MenuItem menEliminarOlimpiada;

    @FXML
    private MenuItem menModificarDeporte;

    @FXML
    private MenuItem menModificarEquipo;

    @FXML
    private MenuItem menModificarOlimpiada;

    @FXML
    private TableView<ModeloDeportista> tablaDeportistas;

    @FXML
    private TextField txtFiltro;
    
    private static ObservableList<ModeloDeportista> listaTodos;
    
    private FilteredList<ModeloDeportista> filtro;

    @FXML
    void accionFiltrar(ActionEvent event) {
    	tablaDeportistas.setItems(filtro);
    	if(txtFiltro.getText().isEmpty()){
    		tablaDeportistas.setItems(listaTodos);
    	}else {
    		filtro.setPredicate(persona -> persona.getNombre().contains(txtFiltro.getText()));
    	}
    }

    @FXML
    void aniadirDeporte(ActionEvent event) {

    }

    @FXML
    void aniadirEquipo(ActionEvent event) {

    }

    @FXML
    void aniadirOlimpiada(ActionEvent event) {

    }

    @FXML
    void aniadirPersona(ActionEvent event) {

    }

    @FXML
    void eliminarDeporte(ActionEvent event) {

    }

    @FXML
    void eliminarEquipo(ActionEvent event) {

    }

    @FXML
    void eliminarOlimpiada(ActionEvent event) {

    }

    @FXML
    void eliminarPersona(ActionEvent event) {

    }

    @FXML
    void modificarDeporte(ActionEvent event) {

    }

    @FXML
    void modificarEquipo(ActionEvent event) {

    }

    @FXML
    void modificarOlimpiada(ActionEvent event) {

    }

    @FXML
    void modificarPersona(ActionEvent event) {

    }

    @FXML
    void mostrarMenuContextual(MouseEvent event) {

    }

}
