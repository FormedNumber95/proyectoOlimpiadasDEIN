package ctrl;

import dao.DaoDeporte;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import model.ModeloDeporte;

/**
 * The Class EditarDeporteController.
 */
public class EditarDeporteController {

    /** The btn cancelar. */
    @FXML
    private Button btnCancelar;

    /** The btn guardar. */
    @FXML
    private Button btnGuardar;

    /** The txt nombre. */
    @FXML
    private TextField txtNombre;
    
    /** The cmb deportes. */
    @FXML
    private ComboBox<ModeloDeporte> cmbDeportes;
    
    /** The grid. */
    @FXML
    private GridPane grid;
    
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
    	if(cmbDeportes.getItems().size()>0) {
	    	Alert al=new Alert(AlertType.INFORMATION);
	    	al.setHeaderText(null);
	    	if(grid.isDisable()) {
	    		al.setAlertType(AlertType.CONFIRMATION);
	    		al.setContentText("Estas seguro que deseas eliminar el deporte?");
	    		al.showAndWait();
	    		if(al.getResult().getButtonData().name().equals("OK_DONE")) {
	    			DaoDeporte.eliminar(cmbDeportes.getSelectionModel().getSelectedItem().getId());
	    		}
	    	}else {
	    		String error="";
	        	String nombre=txtNombre.getText();
	        	boolean existe;
	        	if(txtNombre.getText().isEmpty()) {
	        		error="El campo nombre es obligatorio";
	        	}
	        	existe=validarExistencia(nombre);
		    	if(error.equals("")&&!existe) {
		    		DaoDeporte.modificar(cmbDeportes.getSelectionModel().getSelectedItem().getId(), nombre);
		    		error="Deporte modificado correctamente";
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
	    	}
	    	owner.close();
    	}
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
     * Elegir deporte.
     *
     * @param event the event
     */
    @FXML
    void elegirDeporte(ActionEvent event) {
    	txtNombre.setText(cmbDeportes.getSelectionModel().getSelectedItem().getNombre());
    }
    
    /**
     * Initialize.
     */
    @FXML
    private void initialize() {
    	cmbDeportes.setItems(DaoDeporte.listaDeprotes());
    	if(cmbDeportes.getItems().size()>0) {
    		cmbDeportes.getSelectionModel().select(0);
    		txtNombre.setText(cmbDeportes.getSelectionModel().getSelectedItem().getNombre());
    	}
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
