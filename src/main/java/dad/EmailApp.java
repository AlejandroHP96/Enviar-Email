package dad;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class EmailApp extends Application {

    private Controller controller;

    @Override
    public void start(Stage primaryStage) throws Exception {
        
        controller = new Controller();

        Scene scene = new Scene(controller.getView());

        primaryStage.setTitle("Enviar Email");
        primaryStage.getIcons().addAll(new Image("/imagen/email-send-icon-32x32.png"));
        primaryStage.setScene(scene);
        primaryStage.show();
        
    }

    public static void main(String[] args) {
        launch(args);
    }

    
}
