package es.aketzagonzalez.ctrl;

import es.aketzagonzalez.dao.DaoEquipo;
import es.aketzagonzalez.model.ModeloEquipo;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

/**
 * The Class AniadirEquipoController.
 */
public class AniadirEquipoController {

    /** The btn cancelar. */
    @FXML
    private Button btnCancelar;

    /** The btn guardar. */
    @FXML
    private Button btnGuardar;

    /** The txt iniciales. */
    @FXML
    private TextField txtIniciales;

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
    	String iniciales=txtIniciales.getText();
    	boolean existe;
    	if(txtNombre.getText().isEmpty()) {
    		error="El campo nombre es obligatorio\n";
    	}
    	if(txtIniciales.getText().isEmpty()) {
    		error="El campo iniciales es obligatorio";
    	}
    	Alert al=new Alert(AlertType.INFORMATION);
    	al.setHeaderText(null);
    	existe=validarExistencia(nombre,iniciales);
    	if(error.equals("")&&!existe) {
    		DaoEquipo.aniadir(nombre, iniciales);
    		error="Equipo añadido correctamente";
    	}else {
    		if(error.equals("")) {
    			al.setAlertType(AlertType.WARNING);
    			error="El equipo ya existia";
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
	 * @param iniciales the iniciales
	 * @return true, if successful
	 */
	private boolean validarExistencia(String nombre, String iniciales) {
		ModeloEquipo eq=new ModeloEquipo(nombre, iniciales);
		for(ModeloEquipo e:DaoEquipo.listaEquipos()) {
			if(e.equals(eq)) {
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
