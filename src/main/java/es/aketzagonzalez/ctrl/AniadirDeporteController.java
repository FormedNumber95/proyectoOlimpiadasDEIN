package es.aketzagonzalez.ctrl;

import es.aketzagonzalez.dao.DaoDeporte;
import es.aketzagonzalez.model.ModeloDeporte;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

/**
 * The Class AniadirDeporteController.
 */
public class AniadirDeporteController {

    /** The btn cancelar. */
    @FXML
    private Button btnCancelar;

    /** The btn guardar. */
    @FXML
    private Button btnGuardar;

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
    	existe=validarExistencia(nombre);
    	if(error.equals("")&&!existe) {
    		DaoDeporte.aniadir(nombre);
    		error="Deporte añadido correctamente";
    	}else {
    		if(error.equals("")) {
    			al.setAlertType(AlertType.WARNING);
    			error="El deporte ya existia";
    		}else {
    			al.setAlertType(AlertType.ERROR);
    		}
    	}
    	al.setContentText(error);
    	al.showAndWait();
    	owner.close();
    }
    
    /**
     * Validar existencia.
     *
     * @param nombre the nombre
     * @return true, if successful
     */
    private boolean validarExistencia(String nombre) {
    	ModeloDeporte dep=new ModeloDeporte(nombre);
    	for(ModeloDeporte d:DaoDeporte.listaDeprotes()) {
    		if(d.equals(dep)) {
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
