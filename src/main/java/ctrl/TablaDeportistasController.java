package ctrl;

import java.io.IOException;
import java.util.Locale;
import java.util.Properties;
import java.util.ResourceBundle;

import dao.DaoDeportista;
import db.ConexionBBDD;
import es.aketzagonzalez.proyectoOlimpiadasDEIN.Olimpiadas;
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

/**
 * The Class TablaDeportistasController.
 */
public class TablaDeportistasController {

    /** The col altura. */
    @FXML
    private TableColumn<ModeloDeportista, Integer> colAltura;

    /** The btn aniadir. */
    @FXML
    private Button btnAniadir;

    /** The btn eliminar. */
    @FXML
    private Button btnEliminar;

    /** The btn modificar. */
    @FXML
    private Button btnModificar;

    /** The col nombre. */
    @FXML
    private TableColumn<ModeloDeportista, String> colNombre;

    /** The col peso. */
    @FXML
    private TableColumn<ModeloDeportista, Integer>colPeso;

    /** The col sexo. */
    @FXML
    private TableColumn<ModeloDeportista, String> colSexo;

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

    /** The tabla deportistas. */
    @FXML
    private TableView<ModeloDeportista> tablaDeportistas;

    /** The txt filtro. */
    @FXML
    private TextField txtFiltro;
    
    /** The lista todos. */
    private static ObservableList<ModeloDeportista> listaTodos;
    
    /** The filtro. */
    private FilteredList<ModeloDeportista> filtro;
    
    /** The es aniadir. */
    private static boolean esAniadir;
    
    /** The s. */
    private static Stage s;

    /**
     * Accion filtrar.
     *
     * @param event the event
     */
    @FXML
    void accionFiltrar(ActionEvent event) {
    	tablaDeportistas.setItems(filtro);
    	if(txtFiltro.getText().isEmpty()){
    		tablaDeportistas.setItems(listaTodos);
    	}else {
    		filtro.setPredicate(persona -> persona.getNombre().contains(txtFiltro.getText()));
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
	    s.initOwner(Olimpiadas.getStage());
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
	     s.initOwner(Olimpiadas.getStage());
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
	     s.initOwner(Olimpiadas.getStage());
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
	     s.initOwner(Olimpiadas.getStage());
	     s.initModality(javafx.stage.Modality.WINDOW_MODAL);
	     s.showAndWait();
    }

    /**
     * Aniadir persona.
     *
     * @param event the event
     */
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
			 FXMLLoader controlador = new FXMLLoader(es.aketzagonzalez.proyectoOlimpiadasDEIN.Olimpiadas.class.getResource("/fxml/aniadirDeportista.fxml"),bundle);
			scene = new Scene(controlador.load());
			s.setScene(scene);
			AniadirDeportistaController controller=controlador.getController();
			controller.getCmbSexo().getSelectionModel().select(0);
			controller.setTablaDeportistas(tablaDeportistas);
		} catch (IOException e) {
			e.printStackTrace();
		}
		 s.setResizable(false);
	     s.initOwner(es.aketzagonzalez.proyectoOlimpiadasDEIN.Olimpiadas.getStage());
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
	    s.initOwner(Olimpiadas.getStage());
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
	    s.initOwner(Olimpiadas.getStage());
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
	    s.initOwner(Olimpiadas.getStage());
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
	    s.initOwner(Olimpiadas.getStage());
	    s.initModality(javafx.stage.Modality.WINDOW_MODAL);
	    s.showAndWait();
    }

    /**
     * Eliminar persona.
     *
     * @param event the event
     */
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
	    s.initOwner(Olimpiadas.getStage());
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
	    s.initOwner(Olimpiadas.getStage());
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
	    s.initOwner(Olimpiadas.getStage());
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
	    s.initOwner(Olimpiadas.getStage());
	    s.initModality(javafx.stage.Modality.WINDOW_MODAL);
	    s.showAndWait();
    }

    /**
     * Modificar persona.
     *
     * @param event the event
     */
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
			 FXMLLoader controlador = new FXMLLoader(es.aketzagonzalez.proyectoOlimpiadasDEIN.Olimpiadas.class.getResource("/fxml/aniadirDeportista.fxml"),bundle);
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
	     s.initOwner(es.aketzagonzalez.proyectoOlimpiadasDEIN.Olimpiadas.getStage());
	     s.initModality(javafx.stage.Modality.WINDOW_MODAL);
	     s.showAndWait();
	     accionFiltrar(event);
	     tablaDeportistas.refresh();
    }

    /**
     * Mostrar deportista.
     *
     * @param event the event
     */
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
	   			 FXMLLoader controlador = new FXMLLoader(es.aketzagonzalez.proyectoOlimpiadasDEIN.Olimpiadas.class.getResource("/fxml/participaciones.fxml"),bundle);
	   			scene = new Scene(controlador.load());
	   			s.setScene(scene);
	   			ParticipacionesController controller=controlador.getController();
	   			if(tablaDeportistas.getSelectionModel().getSelectedItem().getFoto()!=null) {
	   				controller.getImgDeportista().setImage(new Image(tablaDeportistas.getSelectionModel().getSelectedItem().getFoto()));
	   			}
	   			} catch (IOException e) {
    			e.printStackTrace();
    		}
    		s.setResizable(false);
    		s.initOwner(es.aketzagonzalez.proyectoOlimpiadasDEIN.Olimpiadas.getStage());
   	     	s.initModality(javafx.stage.Modality.WINDOW_MODAL);
   	     	s.showAndWait();
    	}
    }
    
    /**
     * Initialize.
     */
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

    /**
     * Gets the s.
     *
     * @return the s
     */
    public static Stage getS() {
		return s;
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
     * Sets the lista todos.
     *
     * @param listaTodos the new lista todos
     */
    public static void setListaTodos(ObservableList<ModeloDeportista> listaTodos) {
		TablaDeportistasController.listaTodos = listaTodos;
	}
    
}
