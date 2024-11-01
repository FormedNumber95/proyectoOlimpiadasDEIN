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

public class AniadirEventoController {

    @FXML
    private Button btnCancelar;

    @FXML
    private Button btnGuardar;

    @FXML
    private ComboBox<ModeloDeporte> cmbDeporte;

    @FXML
    private ComboBox<ModeloOlimpiada> cmbOlimpiada;

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

	@FXML
    private void initialize() {
    	cmbDeporte.setItems(DaoDeporte.listaDeprotes());
    	cmbDeporte.getSelectionModel().select(0);
    	cmbOlimpiada.setItems(DaoOlimpiada.listaOlimpiadas());
    	cmbOlimpiada.getSelectionModel().select(0);
    }
	
    private boolean validarExistencia(String nombre, int idDeporte, int idOlimpiada) {
		ModeloEvento ev=new ModeloEvento(nombre, idOlimpiada, idDeporte);
		for(ModeloEvento e:DaoEvento.listaEventos()) {
			if(ev.equals(e)) {
				return true;
			}
		}
		return false;
	}
    
    public void setOwner(Stage owner) {
		this.owner = owner;
	}

}
