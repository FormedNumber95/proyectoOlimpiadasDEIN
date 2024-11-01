package ctrl;

import dao.DaoDeporte;
import dao.DaoEquipo;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import model.ModeloEquipo;

public class AniadirEquipoController {

    @FXML
    private Button btnCancelar;

    @FXML
    private Button btnGuardar;

    @FXML
    private TextField txtIniciales;

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

	private boolean validarExistencia(String nombre, String iniciales) {
		ModeloEquipo eq=new ModeloEquipo(nombre, iniciales);
		for(ModeloEquipo e:DaoEquipo.listaEquipos()) {
			if(e.equals(eq)) {
				return true;
			}
		}
		return false;
	}
	
	public void setOwner(Stage owner) {
		this.owner = owner;
	}

}
