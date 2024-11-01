package ctrl;

import java.io.IOException;
import java.util.Locale;
import java.util.Properties;
import java.util.ResourceBundle;

import dao.DaoDeportista;
import db.ConexionBBDD;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
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
    
    private static boolean esAniadir;
    
    private static Stage s;

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
    	esAniadir=true;
			
    }

    @FXML
    void aniadirEquipo(ActionEvent event) {
    	esAniadir=true;
    }
    
    @FXML
    void aniadirEvento(ActionEvent event) {

    }

    @FXML
    void aniadirOlimpiada(ActionEvent event) {
    	esAniadir=true;
    }

    @FXML
    void aniadirPersona(ActionEvent event) {
    	Properties connConfig =ConexionBBDD.loadProperties() ;
        String lang = connConfig.getProperty("language");
        Locale locale = new Locale.Builder().setLanguage(lang).build();
        ResourceBundle bundle = ResourceBundle.getBundle("idiomas/lang", locale);
    	esAniadir=true;
    	s=new Stage();
    	Scene scene;
		try {
			 FXMLLoader controlador = new FXMLLoader(es.aketzagonzalez.proyectoOlimpiadasDEIN.MainApp.class.getResource("/fxml/aniadirDeportista.fxml"),bundle);
			scene = new Scene(controlador.load());
			s.setScene(scene);
			AniadirDeportistaController controller=controlador.getController();
			controller.getCmbSexo().getSelectionModel().select(0);
			controller.setTablaDeportistas(tablaDeportistas);
		} catch (IOException e) {
			e.printStackTrace();
		}
		 s.setResizable(false);
	     s.initOwner(es.aketzagonzalez.proyectoOlimpiadasDEIN.MainApp.getStage());
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
    void eliminarEvento(ActionEvent event) {

    }

    @FXML
    void eliminarOlimpiada(ActionEvent event) {

    }

    @FXML
    void eliminarPersona(ActionEvent event) {
    	Alert al=new Alert(AlertType.CONFIRMATION);
    	al.setHeaderText(null);
    	if(tablaDeportistas.getSelectionModel().getSelectedItem()!=null) {
    		al.setContentText("Estas seguro que deseas eliminar a "+tablaDeportistas.getSelectionModel().getSelectedItem().getNombre()+"?");
    		al.showAndWait();
    		if(al.getResult().getButtonData().name().equals("OK_DONE")) {
    			DaoDeportista.eliminar(tablaDeportistas.getSelectionModel().getSelectedItem().getId());
    			listaTodos=DaoDeportista.listaDeportistas();
    			accionFiltrar(event);
    			tablaDeportistas.refresh();
    		}
    	}else {
    		al.setAlertType(AlertType.ERROR);
    		al.setContentText("No hay nadie seleccionado, asi que no se puede borrar a nadie");
        	al.showAndWait();
    	}
    	tablaDeportistas.getSelectionModel().clearSelection();
    }

    @FXML
    void modificarDeporte(ActionEvent event) {
    	
    }

    @FXML
    void modificarEquipo(ActionEvent event) {
    	
    }
    
    @FXML
    void modificarEvento(ActionEvent event) {

    }

    @FXML
    void modificarOlimpiada(ActionEvent event) {
    	
    }

    @FXML
    void modificarPersona(ActionEvent event) {
    	Properties connConfig =ConexionBBDD.loadProperties() ;
        String lang = connConfig.getProperty("language");
        Locale locale = new Locale.Builder().setLanguage(lang).build();
        ResourceBundle bundle = ResourceBundle.getBundle("idiomas/lang", locale);
    	esAniadir=false;
    	s=new Stage();
    	Scene scene;
		try {
			 FXMLLoader controlador = new FXMLLoader(es.aketzagonzalez.proyectoOlimpiadasDEIN.MainApp.class.getResource("/fxml/aniadirDeportista.fxml"),bundle);
			scene = new Scene(controlador.load());
			s.setScene(scene);
			AniadirDeportistaController controller=controlador.getController();
			ModeloDeportista dep=tablaDeportistas.getSelectionModel().getSelectedItem();
			controller.setTablaDeportistas(tablaDeportistas);
			controller.getTxtAltura().setText(dep.getAltura()+"");
			controller.getTxtNombre().setText(dep.getNombre());
			controller.getTxtPeso().setText(dep.getPeso()+"");
			controller.getImgDeportista().setImage(new Image(dep.getFoto()));
			if(dep.getSexo().equals("M")) {
				controller.getCmbSexo().getSelectionModel().select(0);
			}else {
				controller.getCmbSexo().getSelectionModel().select(1);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		 s.setResizable(false);
	     s.initOwner(es.aketzagonzalez.proyectoOlimpiadasDEIN.MainApp.getStage());
	     s.initModality(javafx.stage.Modality.WINDOW_MODAL);
	     s.showAndWait();
	     accionFiltrar(event);
	     tablaDeportistas.refresh();
    }

    @FXML
    void mostrarDeportista(MouseEvent event) {
    	if(tablaDeportistas.getSelectionModel().getSelectedItem()!=null&&event.getClickCount()==2) {
    		Properties connConfig =ConexionBBDD.loadProperties() ;
            String lang = connConfig.getProperty("language");
            Locale locale = new Locale.Builder().setLanguage(lang).build();
            ResourceBundle bundle = ResourceBundle.getBundle("idiomas/lang", locale);
    		s=new Stage();
    		Scene scene;
    		try {
    			ParticipacionesController.setTablaDeportistas(tablaDeportistas);
	   			 FXMLLoader controlador = new FXMLLoader(es.aketzagonzalez.proyectoOlimpiadasDEIN.MainApp.class.getResource("/fxml/participaciones.fxml"),bundle);
	   			scene = new Scene(controlador.load());
	   			s.setScene(scene);
	   			ParticipacionesController controller=controlador.getController();
    		} catch (IOException e) {
    			e.printStackTrace();
    		}
    		s.setResizable(false);
    		s.initOwner(es.aketzagonzalez.proyectoOlimpiadasDEIN.MainApp.getStage());
   	     	s.initModality(javafx.stage.Modality.WINDOW_MODAL);
   	     	s.showAndWait();
    	}
    }
    
    @FXML
    private void initialize() {
    	colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
    	colSexo.setCellValueFactory(new PropertyValueFactory<>("sexo"));
    	colPeso.setCellValueFactory(new PropertyValueFactory<>("peso"));
    	colAltura.setCellValueFactory(new PropertyValueFactory<>("altura"));
    	listaTodos=DaoDeportista.listaDeportistas();
    	filtro = new FilteredList<ModeloDeportista>(listaTodos);
    	tablaDeportistas.setItems(listaTodos);
    }

    public static Stage getS() {
		return s;
	}
    
    public static boolean isEsAniadir() {
		return esAniadir;
	}
    
    public static void setListaTodos(ObservableList<ModeloDeportista> listaTodos) {
		TablaDeportistasController.listaTodos = listaTodos;
	}
    
}
