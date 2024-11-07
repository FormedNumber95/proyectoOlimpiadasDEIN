package es.aketzagonzalez.ctrl;

import es.aketzagonzalez.dao.DaoEquipo;
import es.aketzagonzalez.model.ModeloEquipo;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 * The Class EditarEquipoController.
 */
public class EditarEquipoController {

    /** The btn cancelar. */
    @FXML
    private Button btnCancelar;

    /** The btn guardar. */
    @FXML
    private Button btnGuardar;

    /** The cmb equipo. */
    @FXML
    private ComboBox<ModeloEquipo> cmbEquipo;

    /** The grid. */
    @FXML
    private GridPane grid;

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
    	if(cmbEquipo.getItems().size()>0) {
	    	Alert al=new Alert(AlertType.INFORMATION);
	    	al.setHeaderText(null);
	    	if(grid.isDisable()) {
	    		al.setAlertType(AlertType.CONFIRMATION);
	    		al.setContentText("Estas seguro que deseas eliminar el equipo?");
	    		al.showAndWait();
	    		if(al.getResult().getButtonData().name().equals("OK_DONE")) {
	    			DaoEquipo.eliminar(cmbEquipo.getSelectionModel().getSelectedItem().getId());
	    		}
	    	}else {
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
	        	existe=validarExistencia(nombre,iniciales);
	        	if(error.equals("")&&!existe) {
	        		DaoEquipo.modificar(cmbEquipo.getSelectionModel().getSelectedItem().getId(),nombre, iniciales);
	        		error="Equipo editado correctamente";
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
	    	}
	    	owner.close();
    	}
    }

    /**
     * Elegir equipo.
     *
     * @param event the event
     */
    @FXML
    void elegirEquipo(ActionEvent event) {
    	txtIniciales.setText(cmbEquipo.getSelectionModel().getSelectedItem().getIniciales());
		txtNombre.setText(cmbEquipo.getSelectionModel().getSelectedItem().getNombre());
    }
    
    /**
     * Initialize.
     */
    @FXML
    private void initialize() {
    	cmbEquipo.setItems(DaoEquipo.listaEquipos());
    	if(cmbEquipo.getItems().size()>0) {
    		cmbEquipo.getSelectionModel().select(0);
    		txtIniciales.setText(cmbEquipo.getSelectionModel().getSelectedItem().getIniciales());
    		txtNombre.setText(cmbEquipo.getSelectionModel().getSelectedItem().getNombre());
    	}
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
    
    /**
     * Gets the grid.
     *
     * @return the grid
     */
    public GridPane getGrid() {
		return grid;
	}

}
