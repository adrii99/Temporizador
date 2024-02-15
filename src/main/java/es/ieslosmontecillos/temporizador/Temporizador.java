package es.ieslosmontecillos.temporizador;

import java.io.IOException;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

/**
 * Esta clase es un temporizador grafico que realiza una cuenta atras
 * @author Adrian Gonzalez
 * @version 1.0
 * @since JDK 17
 */

public final class Temporizador extends VBox{
    //Objetos del fxml
    @FXML private Label lbl;
    private Integer time;

    /**
     * Contructor de la clase Temporizador al que le entra un valor de tipo Integer llamado time.
     * @param time tipo Integer
     */
    //Constructor
    public Temporizador(Integer time)
    {
        this.setTime(time);

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Temporizador.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try
        {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        lbl.setText(time.toString());

        Timeline tl = new Timeline();

        tl.setCycleCount(getTime());

        KeyFrame kf =new KeyFrame(Duration.seconds(1), e -> {
            setTime(getTime()-1);
            lbl.setText(getTime().toString());
        });

        tl.getKeyFrames().addAll(kf);

        //Pasamos esta propiedad desde el constructor
        tl.setOnFinished((ActionEvent event) -> {
            onFinishedProperty().get().handle(event);
        });

        tl.play();
    }

    /**
     * Constructor vacio
     */
    //Constructor vacio
    public Temporizador(){}

    /**
     * Metodo que te retorna el valor del tiempo
     * @return Integer
     */
    //Getters y setters
    public Integer getTime() {
        return time;
    }

    /**
     * Metodo que se usa para cambiar el valor del tiempo
     * le entra un valor de tipo Integer llamado time
     * @param time
     */
    private void setTime(Integer time) {
        this.time = time;
    }

    //Creamos un objeto privado de un Action Event
    private ObjectProperty<EventHandler<ActionEvent>> propertyOnFinished
            = new SimpleObjectProperty<EventHandler<ActionEvent>>();

    /**
     * Metodo getter que devuelve la propiedad propertyOnFinished creada anteriormente.
     * Al ser de tipo ObjectProperty, proporciona la capacidad de observar cambios en el objeto almacenado.
     * @return ObjectProperty
     */
    //Creamos un metodo para recuperar ese objeto privado de tipo object property action event
    public final ObjectProperty<EventHandler<ActionEvent>> onFinishedProperty() {
        return propertyOnFinished;
    }


    /**
     * Metodo setter que permite establecer el manejador de eventos proporcionado en la propiedad propertyOnFinished
     * @param handler manejador de eventos que se usara
     */
    //Creamos un setter de esta propiedad
    public final void setOnFinished(EventHandler<ActionEvent> handler) {
        propertyOnFinished.set(handler);
    }

    /**
     * Metodo getter que devuelve el manejador de eventos actualmente establecido en la propiedad propertyOnFinished.
     * @return manejador de eventos actualmente establecido
     */
    //Creamos un getter
    public final EventHandler<ActionEvent> getOnFinished() {
        return propertyOnFinished.get();
    }

}