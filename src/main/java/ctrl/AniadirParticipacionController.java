package ctrl;

import dao.DaoEquipo;
import dao.DaoEvento;
import dao.DaoParticipacion;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import model.ModeloDeportista;
import model.ModeloEquipo;
import model.ModeloEvento;
import model.ModeloParticipacion;

public class AniadirParticipacionController {

    @FXML
    private Button btnCancelar;

    @FXML
    private Button btnGuardar;

    @FXML
    private ComboBox<ModeloEquipo> cmbEquipo;

    @FXML
    private ComboBox<ModeloEvento> cmbEvento;

    @FXML
    private ComboBox<String> cmbMedalla;

    @FXML
    private TextField txtEdad;
    
    private static TableView<ModeloParticipacion> tablaParticipaciones;
    
    private static TableView<ModeloDeportista> tablaDeportistas;

    @FXML
    void accionCancelar(ActionEvent event) {
    	ParticipacionesController.getS().close();
    }

    @FXML
    void accionGuardar(ActionEvent event) {
    	String error="";
    	int edad=-1;
    	boolean existe;
    	if(txtEdad.getText().isEmpty()) {
    		error="El campo edad es obligatorio";
    	}else {
    		try {
    			edad=Integer.parseInt(txtEdad.getText());
    			if(edad<=0) {
    				throw new Exception();
    			}
    		}catch(NumberFormatException e) {
    			error="La edad debe ser un numero";
    		}catch(Exception e) {
    			error="La edad no puede ser menor que 1";
    		}
    	}
    	Alert al=new Alert(AlertType.INFORMATION);
    	al.setHeaderText(null);
    	existe=validarExistencia(cmbEvento.getSelectionModel().getSelectedItem(),tablaDeportistas.getSelectionModel().getSelectedItem(),edad);
    	if(error.equals("")&&!existe) {
    		if(ParticipacionesController.isEsAniadir()) {
        		DaoParticipacion.aniadir(tablaDeportistas.getSelectionModel().getSelectedItem().getId(), cmbEvento.getSelectionModel().getSelectedItem().getId(), cmbEquipo.getSelectionModel().getSelectedItem().getId(), edad, cmbMedalla.getSelectionModel().getSelectedItem());
        		error="Participacion añadida correctamente";
    		}else {
    			DaoParticipacion.modificar(tablaDeportistas.getSelectionModel().getSelectedItem().getId(), cmbEvento.getSelectionModel().getSelectedItem().getId(), cmbEquipo.getSelectionModel().getSelectedItem().getId(), edad, cmbMedalla.getSelectionModel().getSelectedItem());
        		error="Participacion modificada correctamente";
        	}
    	}else {
    		if(error.equals("")) {
				al.setAlertType(AlertType.WARNING);
				error="La participacion ya estaba en la lista";
			}else {
				al.setAlertType(AlertType.ERROR);
			}
    	}
    	al.setContentText(error);
    	al.showAndWait();
    }
    
    private boolean validarExistencia(ModeloEvento selectedItem, ModeloDeportista modeloDeportista,int edad) {
		ModeloParticipacion par=new ModeloParticipacion(selectedItem, modeloDeportista.getId(), cmbEquipo.getSelectionModel().getSelectedItem(), edad, cmbMedalla.getSelectionModel().getSelectedItem());
    	for(ModeloParticipacion p:DaoParticipacion.listaParticipaciones(modeloDeportista.getId())) {
			if(p.equals(par)) {
				return true;
			}
		}
		return false;
	}

	@FXML
    private void initialize() {
    	cmbMedalla.getItems().addAll("NO","Bronze","Silver","Gold");
    	cmbEquipo.getItems().addAll(DaoEquipo.listaEquipos());
    	cmbEvento.getItems().addAll(DaoEvento.listaEventos());
    }
    
    public static void setTablaParticipaciones(TableView<ModeloParticipacion> tablaParticipaciones) {
		AniadirParticipacionController.tablaParticipaciones = tablaParticipaciones;
	}
    
    public ComboBox<ModeloEquipo> getCmbEquipo() {
		return cmbEquipo;
	}
    
    public ComboBox<ModeloEvento> getCmbEvento() {
		return cmbEvento;
	}
    
    public ComboBox<String> getCmbMedalla() {
		return cmbMedalla;
	}
    
    public static void setTablaDeportistas(TableView<ModeloDeportista> tablaDeportistas) {
		AniadirParticipacionController.tablaDeportistas = tablaDeportistas;
	}

}
