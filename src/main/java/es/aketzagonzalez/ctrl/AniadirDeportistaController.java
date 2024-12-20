package es.aketzagonzalez.ctrl;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import es.aketzagonzalez.dao.DaoDeportista;
import es.aketzagonzalez.model.ModeloDeportista;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelReader;
import javafx.scene.image.WritablePixelFormat;
import javafx.stage.FileChooser;

/**
 * The Class AniadirDeportistaController.
 */
public class AniadirDeportistaController {

    /** The btn buscar foto. */
    @FXML
    private Button btnBuscarFoto;

    /** The btn cancelar. */
    @FXML
    private Button btnCancelar;

    /** The btn gaurdar. */
    @FXML
    private Button btnGaurdar;
    
    /** The img deportista. */
    @FXML
    private ImageView imgDeportista;
    
    /** The txt altura. */
    @FXML
    private TextField txtAltura;

    /** The txt nombre. */
    @FXML
    private TextField txtNombre;

    /** The txt peso. */
    @FXML
    private TextField txtPeso;

    /** The cmb sexo. */
    @FXML
    private ComboBox<String> cmbSexo;
    
    /** The tabla deportistas. */
    private TableView<ModeloDeportista> tablaDeportistas;

    /**
     * Accion cancelar.
     *
     * @param event the event
     */
    @FXML
    void accionCancelar(ActionEvent event) {
    	TablaDeportistasController.getS().close();
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
    	int peso=-1;
    	int altura=-1;
    	InputStream imagen=null;
    	boolean existe=false;
    	if(txtNombre.getText().isEmpty()) {
    		error+="El campo nombre es obligatorio\n";
    	}
    	if(txtAltura.getText().isEmpty()) {
    		error+="El campo altura es obligatorio\n";
    	}else {
    		try {
    			altura=Integer.parseInt(txtAltura.getText());
    			if(altura<100) {
    				throw new Exception();
    			}
    		}catch(NumberFormatException e) {
    			error+="La altura debe ser un numero\n";
    		}catch(Exception e) {
    			error+="La altura no puede ser menor que 100\n";
    		}
    	}
    	if(txtPeso.getText().isEmpty()) {
    		error+="El campo peso es obligatorio\n";
    	}else {
    		try {
    			peso=Integer.parseInt(txtPeso.getText());
    			if(peso<30) {
    				throw new Exception();
    			}
    		}catch(NumberFormatException e) {
    			error+="El peso debe ser un numero\n";
    		}catch(Exception e) {
    			error+="La altura no puede ser menor que 30\n";
    		}
    	}
    	imagen=getImageInputStream(imgDeportista);
    	if(imagen==null) {
    		error+="La imagen es obligatoria\n";
    	}
    	Alert al=new Alert(AlertType.INFORMATION);
    	al.setHeaderText(null);
    	existe=validarExistencia(nombre,peso,altura,cmbSexo.getSelectionModel().getSelectedItem());
    	if(TablaDeportistasController.isEsAniadir()) {
    		if(error.equals("")&&!existe) {
    			DaoDeportista.aniadir(nombre,cmbSexo.getSelectionModel().getSelectedItem().charAt(0), peso, altura, imagen);
    			error="Deportista añadido correctamente";
    		}else {
    			if(error.equals("")) {
    				al.setAlertType(AlertType.WARNING);
    				error="El deportista ya estaba en la lista";
    			}else {
    				al.setAlertType(AlertType.ERROR);
    			}
    		}
    	}else {
    		if(error.equals("")&&!existe) {
    			error="Deportista modificado correctamente";
    			DaoDeportista.modificar(tablaDeportistas.getSelectionModel().getSelectedItem().getId(), nombre,cmbSexo.getSelectionModel().getSelectedItem().charAt(0), peso, altura, imagen);
    		}else {
    			if(error.equals("")) {
    				al.setAlertType(AlertType.WARNING);
    				error="El deportista ya estaba en la lista";
    			}else {
    				al.setAlertType(AlertType.ERROR);
    			}
    		}
    	}
    	al.setContentText(error);
    	al.showAndWait();
    	tablaDeportistas.getSelectionModel().clearSelection();
    	TablaDeportistasController.setListaTodos(DaoDeportista.listaDeportistas());
    	tablaDeportistas.refresh();
    	TablaDeportistasController.getS().close();
    }

	/**
	 * Buscar.
	 *
	 * @param event the event
	 */
	@FXML
    void buscar(ActionEvent event) {
    	FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extFilterJPG = new FileChooser.ExtensionFilter("Archivos JPG (*.jpg)", "*.jpg");
        FileChooser.ExtensionFilter extFilterPNG = new FileChooser.ExtensionFilter("Archivos PNG (*.png)", "*.png");
        fileChooser.getExtensionFilters().addAll(extFilterJPG, extFilterPNG); 
        File file = fileChooser.showOpenDialog(((Node)event.getSource()).getScene().getWindow());
        if(file!=null) {
	        imgDeportista.setImage(new Image(file.toURI().toString()));
	        imgDeportista.setFitWidth(100);
	        imgDeportista.setFitHeight(100);
        }
    }
    
    /**
     * Initialize.
     */
    @FXML
    private void initialize() {
    	cmbSexo.getItems().addAll("M", "F");
    }
    
    /**
     * Gets the image input stream.
     *
     * @param imageView the image view
     * @return the image input stream
     */
    public static InputStream getImageInputStream(ImageView imageView) {
        Image image = imageView.getImage();
        if (image == null) {
            return null;
        }

        // Obtener las dimensiones de la imagen
        int width = (int) image.getWidth();
        int height = (int) image.getHeight();

        // Crear un BufferedImage con el mismo tamaño que la imagen de JavaFX
        BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

        // Obtener el lector de píxeles de la imagen de JavaFX
        PixelReader pixelReader = image.getPixelReader();
        if (pixelReader != null) {
            // Leer píxeles de la imagen y escribirlos en BufferedImage
            int[] pixels = new int[width * height];
            pixelReader.getPixels(0, 0, width, height, WritablePixelFormat.getIntArgbInstance(), pixels, 0, width);
            bufferedImage.setRGB(0, 0, width, height, pixels, 0, width);
        }

        try {
            // Escribir el BufferedImage en un ByteArrayOutputStream
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            ImageIO.write(bufferedImage, "png", outputStream);
            outputStream.flush();

            // Convertir el ByteArrayOutputStream a InputStream
            return new ByteArrayInputStream(outputStream.toByteArray());
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
    
    /**
     * Validar existencia.
     *
     * @param nombre the nombre
     * @param peso the peso
     * @param altura the altura
     * @param sexo the sexo
     * @return true, if successful
     */
    private boolean validarExistencia(String nombre, int peso, int altura, String sexo) {
		ModeloDeportista dep=new ModeloDeportista(nombre,sexo, peso, altura, null);
		for(ModeloDeportista mod:DaoDeportista.listaDeportistas()) {
			if(mod.equals(dep)) {
				return true;
			}
		}
		return false;
	}

    /**
     * Sets the tabla deportistas.
     *
     * @param tablaDeportistas the new tabla deportistas
     */
    public void setTablaDeportistas(TableView<ModeloDeportista> tablaDeportistas) {
		this.tablaDeportistas = tablaDeportistas;
	}
    
    /**
     * Gets the txt altura.
     *
     * @return the txt altura
     */
    public TextField getTxtAltura() {
		return txtAltura;
	}
    
    /**
     * Gets the txt nombre.
     *
     * @return the txt nombre
     */
    public TextField getTxtNombre() {
		return txtNombre;
	}
    
    /**
     * Gets the txt peso.
     *
     * @return the txt peso
     */
    public TextField getTxtPeso() {
		return txtPeso;
	}
    
    /**
     * Gets the cmb sexo.
     *
     * @return the cmb sexo
     */
    public ComboBox<String> getCmbSexo() {
		return cmbSexo;
	}
    
    /**
     * Gets the img deportista.
     *
     * @return the img deportista
     */
    public ImageView getImgDeportista() {
		return imgDeportista;
	}
}
