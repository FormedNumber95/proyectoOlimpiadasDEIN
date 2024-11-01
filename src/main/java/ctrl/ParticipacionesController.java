package ctrl;

import java.io.IOException;
import java.util.Locale;
import java.util.Properties;
import java.util.ResourceBundle;

import dao.DaoParticipacion;
import db.ConexionBBDD;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import model.ModeloDeportista;
import model.ModeloParticipacion;

public class ParticipacionesController {

    @FXML
    private Button btnAniadir;

    @FXML
    private Button btnEliminar;

    @FXML
    private Button btnModificar;

    @FXML
    private TableColumn<ModeloParticipacion, Integer> colEdad;

    @FXML
    private TableColumn<ModeloParticipacion, String> colEquipo;

    @FXML
    private TableColumn<ModeloParticipacion, String> colMedalla;
    @FXML
    private TableColumn<ModeloParticipacion, String> colEvento;

    @FXML
    private ImageView imgDeportista;

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
    private TableView<ModeloParticipacion> tablaParticipaciones;

    @FXML
    private TextField txtFiltro;
    
    private static ObservableList<ModeloParticipacion>listaTodas;
    
    private FilteredList<ModeloParticipacion> filtro;
    
    private static TableView<ModeloDeportista> tablaDeportistas;
    
    private static Stage s;
    
    private static boolean esAniadir;

    @FXML
    void accionFiltrar(ActionEvent event) {
    	tablaParticipaciones.setItems(filtro);
    	if(txtFiltro.getText().isEmpty()){
    		tablaParticipaciones.setItems(listaTodas);
    	}else {
    		filtro.setPredicate(persona -> persona.getEquipo().getNombre().contains(txtFiltro.getText()));
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
    void aniadirParticipacion(ActionEvent event) {
    	Properties connConfig =ConexionBBDD.loadProperties() ;
        String lang = connConfig.getProperty("language");
        Locale locale = new Locale.Builder().setLanguage(lang).build();
        ResourceBundle bundle = ResourceBundle.getBundle("idiomas/lang", locale);
    	esAniadir=true;
    	s=new Stage();
    	Scene scene;
		try {
			 FXMLLoader controlador = new FXMLLoader(es.aketzagonzalez.proyectoOlimpiadasDEIN.MainApp.class.getResource("/fxml/aniadirParticipacion.fxml"),bundle);
			scene = new Scene(controlador.load());
			s.setScene(scene);
			AniadirParticipacionController controller=controlador.getController();
			AniadirParticipacionController.setTablaParticipaciones(tablaParticipaciones);
			AniadirParticipacionController.setTablaDeportistas(tablaDeportistas);
		} catch (IOException e) {
			e.printStackTrace();
		}
		 s.setResizable(false);
	     s.initOwner(TablaDeportistasController.getS());
	     s.initModality(javafx.stage.Modality.WINDOW_MODAL);
	     s.showAndWait();
	     accionFiltrar(event);
	     tablaDeportistas.refresh();
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
    void eliminarParticipacion(ActionEvent event) {

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
    void modificarParticipacion(ActionEvent event) {
    	Properties connConfig =ConexionBBDD.loadProperties() ;
        String lang = connConfig.getProperty("language");
        Locale locale = new Locale.Builder().setLanguage(lang).build();
        ResourceBundle bundle = ResourceBundle.getBundle("idiomas/lang", locale);
    	esAniadir=false;
    	s=new Stage();
    	Scene scene;
		try {
			 FXMLLoader controlador = new FXMLLoader(es.aketzagonzalez.proyectoOlimpiadasDEIN.MainApp.class.getResource("/fxml/aniadirParticipacion.fxml"),bundle);
			scene = new Scene(controlador.load());
			s.setScene(scene);
			AniadirParticipacionController controller=controlador.getController();
			AniadirParticipacionController.setTablaParticipaciones(tablaParticipaciones);
			AniadirParticipacionController.setTablaDeportistas(tablaDeportistas);
			controller.getCmbEquipo().getSelectionModel().select(tablaParticipaciones.getSelectionModel().getSelectedItem().getEquipo());
			controller.getCmbEvento().getSelectionModel().select(tablaParticipaciones.getSelectionModel().getSelectedItem().getEvento());
			controller.getCmbMedalla().getSelectionModel().select(tablaParticipaciones.getSelectionModel().getSelectedItem().getMedalla());
		} catch (IOException e) {
			e.printStackTrace();
		}
		 s.setResizable(false);
	     s.initOwner(TablaDeportistasController.getS());
	     s.initModality(javafx.stage.Modality.WINDOW_MODAL);
	     s.showAndWait();
	     accionFiltrar(event);
	     tablaDeportistas.refresh();
    }
    
    @FXML
    private void initialize() {
    	colEquipo.setCellValueFactory(cellData -> 
        new SimpleStringProperty(cellData.getValue().getEquipo().getNombre()));
    	colEvento.setCellValueFactory(cellData -> 
        new SimpleStringProperty(cellData.getValue().getEvento().getNombre()));
    	colEdad.setCellValueFactory(new PropertyValueFactory<>("edad"));
    	colMedalla.setCellValueFactory(new PropertyValueFactory<>("medalla"));
    	listaTodas=DaoParticipacion.listaParticipaciones(tablaDeportistas.getSelectionModel().getSelectedItem().getId());
    	filtro=new FilteredList<ModeloParticipacion>(listaTodas);
    }

    public static void setListaTodas(ObservableList<ModeloParticipacion> listaTodas) {
		ParticipacionesController.listaTodas = listaTodas;
	}
    
    public static void setTablaDeportistas(TableView<ModeloDeportista> tablaDeportistas) {
		ParticipacionesController.tablaDeportistas = tablaDeportistas;
	}
    
    public static boolean isEsAniadir() {
		return esAniadir;
	}
    
    public static Stage getS() {
		return s;
	}
    
}
