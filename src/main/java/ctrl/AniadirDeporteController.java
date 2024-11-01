package ctrl;

import dao.DaoDeporte;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import model.ModeloDeporte;

public class AniadirDeporteController {

    @FXML
    private Button btnCancelar;

    @FXML
    private Button btnGuardar;

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
    
    private boolean validarExistencia(String nombre) {
    	ModeloDeporte dep=new ModeloDeporte(nombre);
    	for(ModeloDeporte d:DaoDeporte.listaDeprotes()) {
    		if(d.equals(dep)) {
    			return true;
    		}
    	}
		return false;
	}

	public void setOwner(Stage owner) {
		this.owner = owner;
	}

}
