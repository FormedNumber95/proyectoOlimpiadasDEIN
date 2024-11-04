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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import model.ModeloDeportista;
import model.ModeloParticipacion;

/**
 * The Class ParticipacionesController.
 */
public class ParticipacionesController {

    /** The btn aniadir. */
    @FXML
    private Button btnAniadir;

    /** The btn eliminar. */
    @FXML
    private Button btnEliminar;

    /** The btn modificar. */
    @FXML
    private Button btnModificar;

    /** The col edad. */
    @FXML
    private TableColumn<ModeloParticipacion, Integer> colEdad;

    /** The col equipo. */
    @FXML
    private TableColumn<ModeloParticipacion, String> colEquipo;

    /** The col medalla. */
    @FXML
    private TableColumn<ModeloParticipacion, String> colMedalla;
    
    /** The col evento. */
    @FXML
    private TableColumn<ModeloParticipacion, String> colEvento;

    /** The img deportista. */
    @FXML
    private ImageView imgDeportista;

    /** The men aniadir deporte. */
    @FXML
    private MenuItem menAniadirDeporte;

    /** The men aniadir equipo. */
    @FXML
    private MenuItem menAniadirEquipo;

    /** The men aniadir olimpiada. */
    @FXML
    private MenuItem menAniadirOlimpiada;

    /** The men eliminar deporte. */
    @FXML
    private MenuItem menEliminarDeporte;

    /** The men eliminar equipo. */
    @FXML
    private MenuItem menEliminarEquipo;

    /** The men eliminar olimpiada. */
    @FXML
    private MenuItem menEliminarOlimpiada;

    /** The men modificar deporte. */
    @FXML
    private MenuItem menModificarDeporte;

    /** The men modificar equipo. */
    @FXML
    private MenuItem menModificarEquipo;

    /** The men modificar olimpiada. */
    @FXML
    private MenuItem menModificarOlimpiada;

    /** The tabla participaciones. */
    @FXML
    private TableView<ModeloParticipacion> tablaParticipaciones;

    /** The txt filtro. */
    @FXML
    private TextField txtFiltro;
    
    /** The lista todas. */
    private static ObservableList<ModeloParticipacion>listaTodas;
    
    /** The filtro. */
    private FilteredList<ModeloParticipacion> filtro;
    
    /** The tabla deportistas. */
    private static TableView<ModeloDeportista> tablaDeportistas;
    
    /** The s. */
    private static Stage s;
    
    /** The es aniadir. */
    private static boolean esAniadir;

    /**
     * Accion filtrar.
     *
     * @param event the event
     */
    @FXML
    void accionFiltrar(ActionEvent event) {
    	tablaParticipaciones.setItems(filtro);
    	if(txtFiltro.getText().isEmpty()){
    		tablaParticipaciones.setItems(listaTodas);
    	}else {
    		filtro.setPredicate(persona -> persona.getEquipo().getNombre().contains(txtFiltro.getText()));
    	}
    }

    /**
     * Aniadir deporte.
     *
     * @param event the event
     */
    @FXML
    void aniadirDeporte(ActionEvent event) {
    	Properties connConfig =ConexionBBDD.loadProperties() ;
        String lang = connConfig.getProperty("language");
        Locale locale = new Locale.Builder().setLanguage(lang).build();
        ResourceBundle bundle = ResourceBundle.getBundle("idiomas/lang", locale);
        s=new Stage();
    	Scene scene;
    	try {
			 FXMLLoader controlador = new FXMLLoader(es.aketzagonzalez.proyectoOlimpiadasDEIN.Olimpiadas.class.getResource("/fxml/aniadirDeporte.fxml"),bundle);
			scene = new Scene(controlador.load());
			s.setScene(scene);
			AniadirDeporteController controller=controlador.getController();
			controller.setOwner(s);
    	} catch (IOException e) {
			e.printStackTrace();
		}
    	s.setResizable(false);
	     s.initOwner(TablaDeportistasController.getS());
	     s.initModality(javafx.stage.Modality.WINDOW_MODAL);
	     s.showAndWait();
    }

    /**
     * Aniadir equipo.
     *
     * @param event the event
     */
    @FXML
    void aniadirEquipo(ActionEvent event) {
    	Properties connConfig =ConexionBBDD.loadProperties() ;
        String lang = connConfig.getProperty("language");
        Locale locale = new Locale.Builder().setLanguage(lang).build();
        ResourceBundle bundle = ResourceBundle.getBundle("idiomas/lang", locale);
        s=new Stage();
    	Scene scene;
    	try {
			 FXMLLoader controlador = new FXMLLoader(es.aketzagonzalez.proyectoOlimpiadasDEIN.Olimpiadas.class.getResource("/fxml/aniadirEquipo.fxml"),bundle);
			scene = new Scene(controlador.load());
			s.setScene(scene);
			AniadirEquipoController controller=controlador.getController();
			controller.setOwner(s);
    	} catch (IOException e) {
			e.printStackTrace();
		}
    	s.setResizable(false);
	     s.initOwner(TablaDeportistasController.getS());
	     s.initModality(javafx.stage.Modality.WINDOW_MODAL);
	     s.showAndWait();
    }
    
    /**
     * Aniadir evento.
     *
     * @param event the event
     */
    @FXML
    void aniadirEvento(ActionEvent event) {
    	Properties connConfig =ConexionBBDD.loadProperties() ;
        String lang = connConfig.getProperty("language");
        Locale locale = new Locale.Builder().setLanguage(lang).build();
        ResourceBundle bundle = ResourceBundle.getBundle("idiomas/lang", locale);
        s=new Stage();
    	Scene scene;
    	try {
			 FXMLLoader controlador = new FXMLLoader(es.aketzagonzalez.proyectoOlimpiadasDEIN.Olimpiadas.class.getResource("/fxml/aniadirEvento.fxml"),bundle);
			scene = new Scene(controlador.load());
			s.setScene(scene);
			AniadirEventoController controller=controlador.getController();
			controller.setOwner(s);
    	} catch (IOException e) {
			e.printStackTrace();
		}
    	s.setResizable(false);
	     s.initOwner(TablaDeportistasController.getS());
	     s.initModality(javafx.stage.Modality.WINDOW_MODAL);
	     s.showAndWait();
    }

    /**
     * Aniadir olimpiada.
     *
     * @param event the event
     */
    @FXML
    void aniadirOlimpiada(ActionEvent event) {
    	Properties connConfig =ConexionBBDD.loadProperties() ;
        String lang = connConfig.getProperty("language");
        Locale locale = new Locale.Builder().setLanguage(lang).build();
        ResourceBundle bundle = ResourceBundle.getBundle("idiomas/lang", locale);
        s=new Stage();
    	Scene scene;
    	try {
			 FXMLLoader controlador = new FXMLLoader(es.aketzagonzalez.proyectoOlimpiadasDEIN.Olimpiadas.class.getResource("/fxml/aniadirOlimpiada.fxml"),bundle);
			scene = new Scene(controlador.load());
			s.setScene(scene);
			AniadirOlimpiadaController controller=controlador.getController();
			controller.setOwner(s);
    	} catch (IOException e) {
			e.printStackTrace();
		}
    	s.setResizable(false);
	     s.initOwner(TablaDeportistasController.getS());
	     s.initModality(javafx.stage.Modality.WINDOW_MODAL);
	     s.showAndWait();
    }

    /**
     * Aniadir participacion.
     *
     * @param event the event
     */
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
			 FXMLLoader controlador = new FXMLLoader(es.aketzagonzalez.proyectoOlimpiadasDEIN.Olimpiadas.class.getResource("/fxml/aniadirParticipacion.fxml"),bundle);
			scene = new Scene(controlador.load());
			s.setScene(scene);
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

    /**
     * Eliminar deporte.
     *
     * @param event the event
     */
    @FXML
    void eliminarDeporte(ActionEvent event) {
    	Properties connConfig =ConexionBBDD.loadProperties() ;
        String lang = connConfig.getProperty("language");
        Locale locale = new Locale.Builder().setLanguage(lang).build();
        ResourceBundle bundle = ResourceBundle.getBundle("idiomas/lang", locale);
        s=new Stage();
    	Scene scene;
    	try {
			 FXMLLoader controlador = new FXMLLoader(es.aketzagonzalez.proyectoOlimpiadasDEIN.Olimpiadas.class.getResource("/fxml/editarDeporte.fxml"),bundle);
			scene = new Scene(controlador.load());
			s.setScene(scene);
			EditarDeporteController controller=controlador.getController();
			controller.setOwner(s);
			controller.getGrid().setDisable(true);
    	} catch (IOException e) {
			e.printStackTrace();
		}
    	s.setResizable(false);
	    s.initOwner(TablaDeportistasController.getS());
	    s.initModality(javafx.stage.Modality.WINDOW_MODAL);
	    s.showAndWait();
    }

    /**
     * Eliminar equipo.
     *
     * @param event the event
     */
    @FXML
    void eliminarEquipo(ActionEvent event) {
    	Properties connConfig =ConexionBBDD.loadProperties() ;
        String lang = connConfig.getProperty("language");
        Locale locale = new Locale.Builder().setLanguage(lang).build();
        ResourceBundle bundle = ResourceBundle.getBundle("idiomas/lang", locale);
        s=new Stage();
    	Scene scene;
    	try {
			 FXMLLoader controlador = new FXMLLoader(es.aketzagonzalez.proyectoOlimpiadasDEIN.Olimpiadas.class.getResource("/fxml/editarEquipo.fxml"),bundle);
			scene = new Scene(controlador.load());
			s.setScene(scene);
			EditarEquipoController controller=controlador.getController();
			controller.setOwner(s);
			controller.getGrid().setDisable(true);
    	} catch (IOException e) {
			e.printStackTrace();
		}
    	s.setResizable(false);
	    s.initOwner(TablaDeportistasController.getS());
	    s.initModality(javafx.stage.Modality.WINDOW_MODAL);
	    s.showAndWait();
    }
    
    /**
     * Eliminar evento.
     *
     * @param event the event
     */
    @FXML
    void eliminarEvento(ActionEvent event) {
    	Properties connConfig =ConexionBBDD.loadProperties() ;
        String lang = connConfig.getProperty("language");
        Locale locale = new Locale.Builder().setLanguage(lang).build();
        ResourceBundle bundle = ResourceBundle.getBundle("idiomas/lang", locale);
        s=new Stage();
    	Scene scene;
    	try {
			 FXMLLoader controlador = new FXMLLoader(es.aketzagonzalez.proyectoOlimpiadasDEIN.Olimpiadas.class.getResource("/fxml/editarEvento.fxml"),bundle);
			scene = new Scene(controlador.load());
			s.setScene(scene);
			EditarEventoController controller=controlador.getController();
			controller.setOwner(s);
			controller.getGrid().setDisable(true);
    	} catch (IOException e) {
			e.printStackTrace();
		}
    	s.setResizable(false);
	    s.initOwner(TablaDeportistasController.getS());
	    s.initModality(javafx.stage.Modality.WINDOW_MODAL);
	    s.showAndWait();
    }

    /**
     * Eliminar olimpiada.
     *
     * @param event the event
     */
    @FXML
    void eliminarOlimpiada(ActionEvent event) {
    	Properties connConfig =ConexionBBDD.loadProperties() ;
        String lang = connConfig.getProperty("language");
        Locale locale = new Locale.Builder().setLanguage(lang).build();
        ResourceBundle bundle = ResourceBundle.getBundle("idiomas/lang", locale);
        s=new Stage();
    	Scene scene;
    	try {
			 FXMLLoader controlador = new FXMLLoader(es.aketzagonzalez.proyectoOlimpiadasDEIN.Olimpiadas.class.getResource("/fxml/editarOlimpiada.fxml"),bundle);
			scene = new Scene(controlador.load());
			s.setScene(scene);
			EditarOlimpiadaController controller=controlador.getController();
			controller.setOwner(s);
			controller.getGrid().setDisable(true);
    	} catch (IOException e) {
			e.printStackTrace();
		}
    	s.setResizable(false);
	    s.initOwner(TablaDeportistasController.getS());
	    s.initModality(javafx.stage.Modality.WINDOW_MODAL);
	    s.showAndWait();
    }

    /**
     * Eliminar participacion.
     *
     * @param event the event
     */
    @FXML
    void eliminarParticipacion(ActionEvent event) {
    	Alert al=new Alert(AlertType.CONFIRMATION);
    	al.setHeaderText(null);
    	if(tablaParticipaciones.getSelectionModel().getSelectedItem()!=null) {
    		al.setContentText("Estas seguro que deseas eliminar la participacion ?");
    		al.showAndWait();
    		if(al.getResult().getButtonData().name().equals("OK_DONE")) {
    			DaoParticipacion.eliminar(tablaParticipaciones.getSelectionModel().getSelectedItem().getIdDeportista(),tablaParticipaciones.getSelectionModel().getSelectedItem().getEvento().getId());
    			listaTodas=DaoParticipacion.listaParticipaciones(tablaDeportistas.getSelectionModel().getSelectedItem().getId());
    			accionFiltrar(event);
    			tablaParticipaciones.refresh();
    		}
    	}else {
    		al.setAlertType(AlertType.ERROR);
    		al.setContentText("No hay nadie seleccionado, asi que no se puede borrar a nadie");
        	al.showAndWait();
    	}
    	tablaDeportistas.getSelectionModel().clearSelection();
    }

    /**
     * Modificar deporte.
     *
     * @param event the event
     */
    @FXML
    void modificarDeporte(ActionEvent event) {
    	Properties connConfig =ConexionBBDD.loadProperties() ;
        String lang = connConfig.getProperty("language");
        Locale locale = new Locale.Builder().setLanguage(lang).build();
        ResourceBundle bundle = ResourceBundle.getBundle("idiomas/lang", locale);
        s=new Stage();
    	Scene scene;
    	try {
			 FXMLLoader controlador = new FXMLLoader(es.aketzagonzalez.proyectoOlimpiadasDEIN.Olimpiadas.class.getResource("/fxml/editarDeporte.fxml"),bundle);
			scene = new Scene(controlador.load());
			s.setScene(scene);
			EditarDeporteController controller=controlador.getController();
			controller.setOwner(s);
			controller.getGrid().setDisable(false);
    	} catch (IOException e) {
			e.printStackTrace();
		}
    	s.setResizable(false);
	    s.initOwner(TablaDeportistasController.getS());
	    s.initModality(javafx.stage.Modality.WINDOW_MODAL);
	    s.showAndWait();
    }

    /**
     * Modificar equipo.
     *
     * @param event the event
     */
    @FXML
    void modificarEquipo(ActionEvent event) {
    	Properties connConfig =ConexionBBDD.loadProperties() ;
        String lang = connConfig.getProperty("language");
        Locale locale = new Locale.Builder().setLanguage(lang).build();
        ResourceBundle bundle = ResourceBundle.getBundle("idiomas/lang", locale);
        s=new Stage();
    	Scene scene;
    	try {
			 FXMLLoader controlador = new FXMLLoader(es.aketzagonzalez.proyectoOlimpiadasDEIN.Olimpiadas.class.getResource("/fxml/editarEquipo.fxml"),bundle);
			scene = new Scene(controlador.load());
			s.setScene(scene);
			EditarEquipoController controller=controlador.getController();
			controller.setOwner(s);
			controller.getGrid().setDisable(false);
    	} catch (IOException e) {
			e.printStackTrace();
		}
    	s.setResizable(false);
	    s.initOwner(TablaDeportistasController.getS());
	    s.initModality(javafx.stage.Modality.WINDOW_MODAL);
	    s.showAndWait();
    }
    
    /**
     * Modificar evento.
     *
     * @param event the event
     */
    @FXML
    void modificarEvento(ActionEvent event) {
    	Properties connConfig =ConexionBBDD.loadProperties() ;
        String lang = connConfig.getProperty("language");
        Locale locale = new Locale.Builder().setLanguage(lang).build();
        ResourceBundle bundle = ResourceBundle.getBundle("idiomas/lang", locale);
        s=new Stage();
    	Scene scene;
    	try {
			 FXMLLoader controlador = new FXMLLoader(es.aketzagonzalez.proyectoOlimpiadasDEIN.Olimpiadas.class.getResource("/fxml/editarEvento.fxml"),bundle);
			scene = new Scene(controlador.load());
			s.setScene(scene);
			EditarEventoController controller=controlador.getController();
			controller.setOwner(s);
			controller.getGrid().setDisable(false);
    	} catch (IOException e) {
			e.printStackTrace();
		}
    	s.setResizable(false);
	    s.initOwner(TablaDeportistasController.getS());
	    s.initModality(javafx.stage.Modality.WINDOW_MODAL);
	    s.showAndWait();
    }

    /**
     * Modificar olimpiada.
     *
     * @param event the event
     */
    @FXML
    void modificarOlimpiada(ActionEvent event) {
    	Properties connConfig =ConexionBBDD.loadProperties() ;
        String lang = connConfig.getProperty("language");
        Locale locale = new Locale.Builder().setLanguage(lang).build();
        ResourceBundle bundle = ResourceBundle.getBundle("idiomas/lang", locale);
        s=new Stage();
    	Scene scene;
    	try {
			 FXMLLoader controlador = new FXMLLoader(es.aketzagonzalez.proyectoOlimpiadasDEIN.Olimpiadas.class.getResource("/fxml/editarOlimpiada.fxml"),bundle);
			scene = new Scene(controlador.load());
			s.setScene(scene);
			EditarOlimpiadaController controller=controlador.getController();
			controller.setOwner(s);
			controller.getGrid().setDisable(false);
    	} catch (IOException e) {
			e.printStackTrace();
		}
    	s.setResizable(false);
	    s.initOwner(TablaDeportistasController.getS());
	    s.initModality(javafx.stage.Modality.WINDOW_MODAL);
	    s.showAndWait();
    }

    /**
     * Modificar participacion.
     *
     * @param event the event
     */
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
			 FXMLLoader controlador = new FXMLLoader(es.aketzagonzalez.proyectoOlimpiadasDEIN.Olimpiadas.class.getResource("/fxml/aniadirParticipacion.fxml"),bundle);
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
    
    /**
     * Initialize.
     */
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

    /**
     * Sets the lista todas.
     *
     * @param listaTodas the new lista todas
     */
    public static void setListaTodas(ObservableList<ModeloParticipacion> listaTodas) {
		ParticipacionesController.listaTodas = listaTodas;
	}
    
    /**
     * Sets the tabla deportistas.
     *
     * @param tablaDeportistas the new tabla deportistas
     */
    public static void setTablaDeportistas(TableView<ModeloDeportista> tablaDeportistas) {
		ParticipacionesController.tablaDeportistas = tablaDeportistas;
	}
    
    /**
     * Checks if is es aniadir.
     *
     * @return true, if is es aniadir
     */
    public static boolean isEsAniadir() {
		return esAniadir;
	}
    
    /**
     * Gets the s.
     *
     * @return the s
     */
    public static Stage getS() {
		return s;
	}
    
    /**
     * Gets the img deportista.
     *
     * @return the img deportista
     */
    public ImageView getImgDeportista() {
		return imgDeportista;
	}
    
}
