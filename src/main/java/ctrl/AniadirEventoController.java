package ctrl;

import dao.DaoDeporte;
import dao.DaoEvento;
import dao.DaoOlimpiada;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import model.ModeloDeporte;
import model.ModeloEvento;
import model.ModeloOlimpiada;

/**
 * The Class AniadirEventoController.
 */
public class AniadirEventoController {

    /** The btn cancelar. */
    @FXML
    private Button btnCancelar;

    /** The btn guardar. */
    @FXML
    private Button btnGuardar;

    /** The cmb deporte. */
    @FXML
    private ComboBox<ModeloDeporte> cmbDeporte;

    /** The cmb olimpiada. */
    @FXML
    private ComboBox<ModeloOlimpiada> cmbOlimpiada;

    /** The txt nombre. */
    @FXML
    private TextField txtNombre;
    
    /** The owner. */
    private Stage owner;

    /**
     * Accion cancelar.
     *
     * @param event the event
     */
    @FXML
    void accionCancelar(ActionEvent event) {
    	owner.close();
    }

    /**
     * Accion guardar.
     *
     * @param event the event
     */
    @FXML
    void accionGuardar(ActionEvent event) {
    	String error="";
    	String nombre=txtNombre.getText();
    	boolean existe;
    	if(txtNombre.getText().isEmpty()) {
    		error="El campo nombre es obligatorio";
    	}
    	Alert al=new Alert(AlertType.INFORMATION);
    	al.setHeaderText(null);
    	existe=validarExistencia(nombre,cmbDeporte.getSelectionModel().getSelectedItem().getId(),cmbOlimpiada.getSelectionModel().getSelectedItem().getId());
    	if(error.equals("")&&!existe) {
    		DaoEvento.aniadir(nombre, cmbOlimpiada.getSelectionModel().getSelectedItem().getId(), cmbDeporte.getSelectionModel().getSelectedItem().getId());
    		error="Evento añadido correctamente";
    	}else {
    		if(error.equals("")) {
    			al.setAlertType(AlertType.WARNING);
    			error="El evento ya existia";
    		}else {
    			al.setAlertType(AlertType.ERROR);
    		}
    	}
    	al.setContentText(error);
    	al.showAndWait();
    	owner.close();
    }

	/**
	 * Initialize.
	 */
	@FXML
    private void initialize() {
    	cmbDeporte.setItems(DaoDeporte.listaDeprotes());
    	cmbDeporte.getSelectionModel().select(0);
    	cmbOlimpiada.setItems(DaoOlimpiada.listaOlimpiadas());
    	cmbOlimpiada.getSelectionModel().select(0);
    }
	
    /**
     * Validar existencia.
     *
     * @param nombre the nombre
     * @param idDeporte the id deporte
     * @param idOlimpiada the id olimpiada
     * @return true, if successful
     */
    private boolean validarExistencia(String nombre, int idDeporte, int idOlimpiada) {
		ModeloEvento ev=new ModeloEvento(nombre, idOlimpiada, idDeporte);
		for(ModeloEvento e:DaoEvento.listaEventos()) {
			if(ev.equals(e)) {
				return true;
			}
		}
		return false;
	}
    
    /**
     * Sets the owner.
     *
     * @param owner the new owner
     */
    public void setOwner(Stage owner) {
		this.owner = owner;
	}

}
