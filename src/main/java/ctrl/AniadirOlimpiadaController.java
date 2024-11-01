package ctrl;

import dao.DaoOlimpiada;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import model.ModeloOlimpiada;

public class AniadirOlimpiadaController {

    @FXML
    private Button btnCancelar;

    @FXML
    private Button btnGuardar;

    @FXML
    private ComboBox<String> cmbTemporada;

    @FXML
    private TextField txtCiudad;

    @FXML
    private TextField txtEdad;

    @FXML
    private TextField txtNombre;
    
    private Stage owner;

    @FXML
    void accionCancelar(ActionEvent event) {
    	owner.close();
    }

    @FXML
    void accionGuardar(ActionEvent event) {
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
    	if(txtEdad.getText().isEmpty()) {
    		error+="El campo año es obligatorio\n";
    	}else {
    		try {
    			anio=Integer.parseInt(txtEdad.getText());
    			if(anio<1900) {
    				throw new Exception();
    			}
    		}catch(NumberFormatException e) {
    			error+="El año debe ser un numero\n";
    		}catch(Exception e) {
    			error+="El año debe ser mayor que 1900\n";
    		}
    	}
    	Alert al=new Alert(AlertType.INFORMATION);
    	al.setHeaderText(null);
    	existe=validarExistencia(nombre,anio,ciudad,cmbTemporada.getSelectionModel().getSelectedItem());
    	if(error.equals("")&&!existe) {
    		DaoOlimpiada.aniadir(nombre, anio,cmbTemporada.getSelectionModel().getSelectedItem(), ciudad);
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
    	owner.close();
    }
    
    @FXML
    private void initialize() {
    	cmbTemporada.getItems().addAll("Winter","Summer");
    	cmbTemporada.getSelectionModel().select(0);
    }

    private boolean validarExistencia(String nombre, int anio, String ciudad, String selectedItem) {
		ModeloOlimpiada ol=new ModeloOlimpiada(nombre, anio, selectedItem, ciudad);
		for(ModeloOlimpiada o:DaoOlimpiada.listaOlimpiadas()) {
			if(ol.equals(o)) {
				return true;
			}
		}
		return false;
	}

	public void setOwner(Stage owner) {
		this.owner = owner;
	}
    
}
