package es.ieslosmontecillos.temporizador;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Esta clase extiende de Application y es la encargada de lanzar la escena
 */
public class TemporizadorExample extends Application {
    @Override
    public void start(Stage stage) throws Exception {

        //Crea una instancia de Temporizador con un tiempo
        Temporizador temp = new Temporizador(5);

        VBox root = new VBox();
        System.out.println(temp.getTime());

        stage.setScene(new Scene(root));
        stage.setTitle("Custom Control");
        stage.setWidth(300);
        stage.setHeight(200);
        stage.show();

        root.getChildren().add(temp);

        temp.setOnFinished(e -> {
            Label lbl = new Label("Se acabo el tiempo!!");
            root.getChildren().add(lbl);
        });

    }

    /**
     *Metodo que ejecuta la aplicacion
     * @param args argumentos de la aplicacion
     */
    public static void main(String[] args) {
        launch(args);
    }
}