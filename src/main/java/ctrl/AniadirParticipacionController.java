package ctrl;

import dao.DaoEquipo;
import dao.DaoEvento;
import dao.DaoParticipacion;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import model.ModeloDeportista;
import model.ModeloEquipo;
import model.ModeloEvento;
import model.ModeloParticipacion;

/**
 * The Class AniadirParticipacionController.
 */
public class AniadirParticipacionController {

    /** The btn cancelar. */
    @FXML
    private Button btnCancelar;

    /** The btn guardar. */
    @FXML
    private Button btnGuardar;

    /** The cmb equipo. */
    @FXML
    private ComboBox<ModeloEquipo> cmbEquipo;

    /** The cmb evento. */
    @FXML
    private ComboBox<ModeloEvento> cmbEvento;

    /** The cmb medalla. */
    @FXML
    private ComboBox<String> cmbMedalla;

    /** The txt edad. */
    @FXML
    private TextField txtEdad;
    
    /** The tabla participaciones. */
    private static TableView<ModeloParticipacion> tablaParticipaciones;
    
    /** The tabla deportistas. */
    private static TableView<ModeloDeportista> tablaDeportistas;

    /**
     * Accion cancelar.
     *
     * @param event the event
     */
    @FXML
    void accionCancelar(ActionEvent event) {
    	ParticipacionesController.getS().close();
    }

    /**
     * Accion guardar.
     *
     * @param event the event
     */
    @FXML
    void accionGuardar(ActionEvent event) {
    	String error="";
    	int edad=-1;
    	boolean existe;
    	if(txtEdad.getText().isEmpty()) {
    		error="El campo edad es obligatorio";
    	}else {
    		try {
    			edad=Integer.parseInt(txtEdad.getText());
    			if(edad<=0) {
    				throw new Exception();
    			}
    		}catch(NumberFormatException e) {
    			error="La edad debe ser un numero";
    		}catch(Exception e) {
    			error="La edad no puede ser menor que 1";
    		}
    	}
    	Alert al=new Alert(AlertType.INFORMATION);
    	al.setHeaderText(null);
    	existe=validarExistencia(cmbEvento.getSelectionModel().getSelectedItem(),tablaDeportistas.getSelectionModel().getSelectedItem(),edad);
    	if(error.equals("")&&!existe) {
    		if(ParticipacionesController.isEsAniadir()) {
        		DaoParticipacion.aniadir(tablaDeportistas.getSelectionModel().getSelectedItem().getId(), cmbEvento.getSelectionModel().getSelectedItem().getId(), cmbEquipo.getSelectionModel().getSelectedItem().getId(), edad, cmbMedalla.getSelectionModel().getSelectedItem());
        		error="Participacion añadida correctamente";
    		}else {
    			DaoParticipacion.modificar(tablaDeportistas.getSelectionModel().getSelectedItem().getId(), cmbEvento.getSelectionModel().getSelectedItem().getId(), cmbEquipo.getSelectionModel().getSelectedItem().getId(), edad, cmbMedalla.getSelectionModel().getSelectedItem());
        		error="Participacion modificada correctamente";
        	}
    	}else {
    		if(error.equals("")) {
				al.setAlertType(AlertType.WARNING);
				error="La participacion ya estaba en la lista";
			}else {
				al.setAlertType(AlertType.ERROR);
			}
    	}
    	al.setContentText(error);
    	al.showAndWait();
    }
    
    /**
     * Validar existencia.
     *
     * @param selectedItem the selected item
     * @param modeloDeportista the modelo deportista
     * @param edad the edad
     * @return true, if successful
     */
    private boolean validarExistencia(ModeloEvento selectedItem, ModeloDeportista modeloDeportista,int edad) {
		ModeloParticipacion par=new ModeloParticipacion(selectedItem, modeloDeportista.getId(), cmbEquipo.getSelectionModel().getSelectedItem(), edad, cmbMedalla.getSelectionModel().getSelectedItem());
    	for(ModeloParticipacion p:DaoParticipacion.listaParticipaciones(modeloDeportista.getId())) {
			if(p.equals(par)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Initialize.
	 */
	@FXML
    private void initialize() {
    	cmbMedalla.getItems().addAll("NO","Bronze","Silver","Gold");
    	cmbEquipo.getItems().addAll(DaoEquipo.listaEquipos());
    	cmbEvento.getItems().addAll(DaoEvento.listaEventos());
    }
    
    /**
     * Sets the tabla participaciones.
     *
     * @param tablaParticipaciones the new tabla participaciones
     */
    public static void setTablaParticipaciones(TableView<ModeloParticipacion> tablaParticipaciones) {
		AniadirParticipacionController.tablaParticipaciones = tablaParticipaciones;
	}
    
    /**
     * Gets the cmb equipo.
     *
     * @return the cmb equipo
     */
    public ComboBox<ModeloEquipo> getCmbEquipo() {
		return cmbEquipo;
	}
    
    /**
     * Gets the cmb evento.
     *
     * @return the cmb evento
     */
    public ComboBox<ModeloEvento> getCmbEvento() {
		return cmbEvento;
	}
    
    /**
     * Gets the cmb medalla.
     *
     * @return the cmb medalla
     */
    public ComboBox<String> getCmbMedalla() {
		return cmbMedalla;
	}
    
    /**
     * Sets the tabla deportistas.
     *
     * @param tablaDeportistas the new tabla deportistas
     */
    public static void setTablaDeportistas(TableView<ModeloDeportista> tablaDeportistas) {
		AniadirParticipacionController.tablaDeportistas = tablaDeportistas;
	}

}
