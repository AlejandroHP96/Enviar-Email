package dad;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Controller implements Initializable {

    // model

    private StringProperty servidoProperty = new SimpleStringProperty();
    private StringProperty puertoProperty = new SimpleStringProperty();
    private BooleanProperty SSLProperty = new SimpleBooleanProperty();
    private StringProperty emailRProperty = new SimpleStringProperty();
    private StringProperty passwordProperty = new SimpleStringProperty();
    private StringProperty emailDProperty = new SimpleStringProperty();
    private StringProperty asuntoProperty = new SimpleStringProperty();
    private StringProperty textProperty = new SimpleStringProperty();

    @FXML
    private TextField asuntoTextField;

    @FXML
    private CheckBox sslcheckbox;

    @FXML
    private Button cerrarButton;

    @FXML
    private TextField contraseñaTextField;

    @FXML
    private TextField destinatarioTextField;

    @FXML
    private Button enviarButton;

    @FXML
    private TextArea mensajeTexto;

    @FXML
    private TextField puertoTextField;

    @FXML
    private TextField remitenteTextField;

    @FXML
    private TextField servidorTextField;

    @FXML
    private Button vaciarButton;

    @FXML
    private GridPane view;

    public Controller() throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Email.fxml"));
        loader.setController(this);
        loader.load();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        servidoProperty.bind(servidorTextField.textProperty());
        puertoProperty.bind(puertoTextField.textProperty());
        SSLProperty.bind(sslcheckbox.selectedProperty());
        emailRProperty.bind(remitenteTextField.textProperty());
        passwordProperty.bind(contraseñaTextField.textProperty());
        emailDProperty.bind(destinatarioTextField.textProperty());
        asuntoProperty.bind(asuntoTextField.textProperty());
        textProperty.bind(mensajeTexto.textProperty());
    }

    @FXML
    void oCerrarButton(ActionEvent event) {

        Stage stage = (Stage) view.getScene().getWindow();
        stage.close();

    }

    @FXML
    void onEnviarButton(ActionEvent event) throws EmailException {

        try {

            Email email = new SimpleEmail();

            email.setHostName(servidoProperty.get());
            email.setSmtpPort(Integer.parseInt(puertoProperty.get()));
            email.setAuthenticator(new DefaultAuthenticator(emailRProperty.get(), passwordProperty.get()));
            email.setSSLOnConnect(SSLProperty.get());
            email.setFrom(emailRProperty.get());
            email.setSubject(asuntoProperty.get());
            email.setMsg(textProperty.get());
            email.addTo(emailDProperty.get());
            email.send();

            destinatarioTextField.clear();
            asuntoTextField.clear();
            mensajeTexto.clear();

            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("Mensaje Enviado");
            alert.setHeaderText("Mensaje enviado con éxito a '" + emailDProperty.get().toString() + "'");
            
            // Get the Stage.
            Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
            
            // Add a custom icon.
            stage.getIcons().add(new Image(getClass().getResourceAsStream("/imagen/email-send-icon-32x32.png")));
            
            stage.showAndWait();

        } catch (Exception e) {

            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("No se pudo enviar el email");
            alert.setContentText("Invalid message supplied");
            
            // Get the Stage.
            Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
            
            // Add a custom icon.
            stage.getIcons().add(new Image(getClass().getResourceAsStream("/imagen/email-send-icon-32x32.png")));
            
            stage.showAndWait();
        }

    }

    @FXML
    void onVaciarButton(ActionEvent event) {

        servidorTextField.clear();
        puertoTextField.clear();
        sslcheckbox.setSelected(false);
        ;
        remitenteTextField.clear();
        contraseñaTextField.clear();
        destinatarioTextField.clear();
        asuntoTextField.clear();
        mensajeTexto.clear();

    }

    public GridPane getView() {
        return view;
    }

}
