package es.juanics.pong;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

/**
 * JavaFX App
 */
public class App extends Application {

    @Override
    public void start(Stage stage) {
        StackPane root = new StackPane();//creo un nuevo objeto de tipo StackPane llamado root
        var scene = new Scene(root, 640, 480);//le digo a la escena el panel principal (root) y el tamaño de la pantalla
        stage.setScene(scene);
        stage.show();
        
        Circle circleBall = new Circle(); //Creamos una variable llamada circleBall(nuevo objeto -> new) de tipo Círculo(clase Círculo)
        circleBall.setCenterX(10);
        circleBall.setCenterY(30);
        circleBall.setRadius(7);
        
        root.getChildren().add(circleBall);//Hay que añadir la bola al StackPane(panel llamado root). Los objtos que contiene el panel son los Children. 
        
        
        //se le podía haber asignado directamente -> Circle circleBall = new Circle (10, 30, 10);
        int n = 0;
    }

    public static void main(String[] args) {
    }

}