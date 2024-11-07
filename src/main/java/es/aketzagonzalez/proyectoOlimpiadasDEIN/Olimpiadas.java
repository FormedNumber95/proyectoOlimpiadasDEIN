package es.aketzagonzalez.proyectoOlimpiadasDEIN;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Locale;
import java.util.Properties;
import java.util.ResourceBundle;

import es.aketzagonzalez.db.ConexionBBDD;

/**
 * The Class Olimpiadas.
 */
public class Olimpiadas extends Application {
    
    /** The stage. */
    private static Stage stage;

    /**
     * Start.
     *
     * @param s the s
     * @throws IOException Signals that an I/O exception has occurred.
     */
    @Override
    public void start(@SuppressWarnings("exports") Stage s) throws IOException {
    	Properties connConfig =ConexionBBDD.loadProperties() ;
        String lang = connConfig.getProperty("language");
        Locale locale = new Locale.Builder().setLanguage(lang).build();
        ResourceBundle bundle = ResourceBundle.getBundle("idiomas/lang", locale);
        stage=s;
        Image imagen=new Image(getClass().getResource("/imagenes/agenda.png").toString());
        stage.getIcons().add(imagen);
        stage.setResizable(false);
        try {
			ConexionBBDD db=new ConexionBBDD();
			setRoot("deportistas","DEPORTISTAS",bundle);
		} catch (SQLException e) {
			Alert al=new Alert(AlertType.ERROR);
			al.setHeaderText(null);
			al.setContentText("Error en la coneion a la base de datos");
			al.showAndWait();
		}
    }

    /**
     * Sets the root.
     *
     * @param fxml the fxml
     * @param bundle the bundle
     * @throws IOException Signals that an I/O exception has occurred.
     */
    static void setRoot(String fxml,ResourceBundle bundle) throws IOException {
        setRoot(fxml,stage.getTitle(),bundle);
    }

    /**
     * Sets the root.
     *
     * @param fxml the fxml
     * @param title the title
     * @param bundle the bundle
     * @throws IOException Signals that an I/O exception has occurred.
     */
    static void setRoot(String fxml, String title,ResourceBundle bundle) throws IOException {
        Scene scene = new Scene(loadFXML(fxml,bundle));
        stage.setTitle(title);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Load FXML.
     *
     * @param fxml the fxml
     * @param bundle the bundle
     * @return the parent
     * @throws IOException Signals that an I/O exception has occurred.
     */
    private static Parent loadFXML(String fxml,ResourceBundle bundle) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Olimpiadas.class.getResource("/fxml/"+fxml + ".fxml"),bundle);
        return fxmlLoader.load();
    }


    /**
     * The main method.
     *
     * @param args the arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
    /**
     * Gets the stage.
     *
     * @return the stage
     */
    public static Stage getStage() {
		return stage;
	}

}
