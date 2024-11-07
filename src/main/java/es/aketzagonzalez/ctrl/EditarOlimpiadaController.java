package es.aketzagonzalez.ctrl;

import es.aketzagonzalez.dao.DaoOlimpiada;
import es.aketzagonzalez.model.ModeloOlimpiada;
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
 * The Class EditarOlimpiadaController.
 */
public class EditarOlimpiadaController {

    /** The btn cancelar. */
    @FXML
    private Button btnCancelar;

    /** The btn guardar. */
    @FXML
    private Button btnGuardar;

    /** The cmb olimpiada. */
    @FXML
    private ComboBox<ModeloOlimpiada> cmbOlimpiada;

    /** The cmb temporada. */
    @FXML
    private ComboBox<String> cmbTemporada;

    /** The grid. */
    @FXML
    private GridPane grid;

    /** The txt anio. */
    @FXML
    private TextField txtAnio;

    /** The txt ciudad. */
    @FXML
    private TextField txtCiudad;

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
    	if(cmbOlimpiada.getItems().size()>0) {
    		Alert al=new Alert(AlertType.INFORMATION);
	    	al.setHeaderText(null);
    		if(grid.isDisable()) {
    			al.setAlertType(AlertType.CONFIRMATION);
	    		al.setContentText("Estas seguro que deseas eliminar la olimpiada?");
	    		al.showAndWait();
	    		if(al.getResult().getButtonData().name().equals("OK_DONE")) {
	    			DaoOlimpiada.eliminar(cmbOlimpiada.getSelectionModel().getSelectedItem().getId());
	    		}
    		}else {
    			String error="";
    	    	String nombre=txtNombre.getText();
    	    	int anio=-1;
    	    	String ciudad=txtCiudad.getText();
    	    	boolean existe;
    	    	if(txtNombre.getText().isEmpty()) {
    	    		error="El campo nombre es obligatorio\n";
    	    	}
    	    	if(txtCiudad.getText().isEmpty()) {
    	    		error+="El campo ciudad es obligatorio\n";
    	    	}
    	    	if(txtAnio.getText().isEmpty()) {
    	    		error+="El campo año es obligatorio\n";
    	    	}else {
    	    		try {
    	    			anio=Integer.parseInt(txtAnio.getText());
    	    			if(anio<1900) {
    	    				throw new Exception();
    	    			}
    	    		}catch(NumberFormatException e) {
    	    			error+="El año debe ser un numero\n";
    	    		}catch(Exception e) {
    	    			error+="El año debe ser mayor que 1900\n";
    	    		}
    	    	}
    	    	existe=validarExistencia(nombre,anio,ciudad,cmbTemporada.getSelectionModel().getSelectedItem());
    	    	if(error.equals("")&&!existe) {
    	    		DaoOlimpiada.modificar(cmbOlimpiada.getSelectionModel().getSelectedItem().getId(),nombre, anio,cmbTemporada.getSelectionModel().getSelectedItem(), ciudad);
    	    		error="Olimpiada añadida correctamente";
    	    	}else {
    	    		if(error.equals("")) {
    	    			al.setAlertType(AlertType.WARNING);
    	    			error="La olimpiada ya existia";
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
     * Elegir olimpiada.
     *
     * @param event the event
     */
    @FXML
    void elegirOlimpiada(ActionEvent event) {
    	txtNombre.setText(cmbOlimpiada.getSelectionModel().getSelectedItem().getNombre());
		txtAnio.setText(cmbOlimpiada.getSelectionModel().getSelectedItem().getAnio()+"");
		txtCiudad.setText(cmbOlimpiada.getSelectionModel().getSelectedItem().getCiudad());
		cmbTemporada.getSelectionModel().select(cmbOlimpiada.getSelectionModel().getSelectedItem().getTemporada());
    }
    
    /**
     * Initialize.
     */
    @FXML
    private void initialize() {
    	cmbOlimpiada.setItems(DaoOlimpiada.listaOlimpiadas());
    	cmbTemporada.getItems().addAll("Winter","Summer");
    	if(cmbOlimpiada.getItems().size()>0) {
    		cmbOlimpiada.getSelectionModel().select(0);
    		txtNombre.setText(cmbOlimpiada.getSelectionModel().getSelectedItem().getNombre());
    		txtAnio.setText(cmbOlimpiada.getSelectionModel().getSelectedItem().getAnio()+"");
    		txtCiudad.setText(cmbOlimpiada.getSelectionModel().getSelectedItem().getCiudad());
    		cmbTemporada.getSelectionModel().select(cmbOlimpiada.getSelectionModel().getSelectedItem().getTemporada());
    	}
    }
    
    /**
     * Validar existencia.
     *
     * @param nombre the nombre
     * @param anio the anio
     * @param ciudad the ciudad
     * @param selectedItem the selected item
     * @return true, if successful
     */
    private boolean validarExistencia(String nombre, int anio, String ciudad, String selectedItem) {
		ModeloOlimpiada ol=new ModeloOlimpiada(nombre, anio, selectedItem, ciudad);
		for(ModeloOlimpiada o:DaoOlimpiada.listaOlimpiadas()) {
			if(ol.equals(o)) {
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
