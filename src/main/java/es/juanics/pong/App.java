package es.juanics.pong;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * JavaFX App
 */
public class App extends Application {
    //declaramos todas las variables generales aquí
    int ballCenterX = 0;//Si declarara la variable dentro no serviría poque la estaría declarando cada vez que entra en el bucle
    int ballDirectionX = 1; //Si la dirección es a la derecha (suma) y si es a la izquierda (resta)
    int ballCurrentSpeedX = 6;//El incremento que irá tomando. Velocidad
    //sumar -1 es restar 1. (+-1) es igual que -1
    int ballCenterY = 0;//Si declarara la variable dentro no serviría poque la estaría declarando cada vez que entra en el bucle
    int ballDirectionY = 1; //Si la dirección es arriba (restando) y si es abajo (sumando)
    int ballCurrentSpeedY = 6;//El incremento que irá tomando.Velocidad
    //sumar -1 es restar 1. (+-1) es igual que -1
    
    
    
    @Override
    public void start(Stage stage) {
        //StackPane (apila una cosa encima de otra en el panel. Así que no nos sirve, porque se pisan
       // StackPane root = new StackPane();//creo un nuevo objeto de tipo StackPane llamado root
        Pane root = new Pane();
        var scene = new Scene(root, 640, 480);//le digo a la escena el panel principal (root) y el tamaño de la pantalla
        scene.setFill(Color.BLACK);//Le damos el color de fondo a la escena
        stage.setScene(scene);
        stage.show();
        
        //new Circle() => Crear un objeto de la clase Circle
        Circle circleBall = new Circle(); //Creamos una variable llamada circleBall(nuevo objeto -> new) de tipo Círculo(clase Círculo)
        //Llamamos a MÉTODOS del objeto circleBall
        circleBall.setCenterX(10);
        circleBall.setCenterY(30);
        circleBall.setRadius(7);
        //Es igual que--> Circle circleBall = new Circle(10, 30, 7) [porque el primer número (10) pertenece al método setCenterX, el segundo (30) pertenece a setCenterY, y el último (7) a setRadius]
        circleBall.setFill(Color.BLUE);//Darle color a la bola. Nos pide un  objeto paintPaint (un color de los que tiene establecidos)
        //se le podía haber asignado directamente -> Circle circleBall = new Circle (10, 30, 10);
               
        root.getChildren().add(circleBall);//Hay que añadir la bola al StackPane(panel llamado root). Los objtos que contiene el panel son los Children. 
                      
        
        // Game loop usando Timeline
        Timeline timeline = new Timeline(//Sirve para lo que lo que metamos aquí. Podemos utilizar varios TimeLine con diferentes velocidades para diferentes cosas
            // 0.017 ~= 60 FPS (equivalencia de segundos a Frames por Segundo)
            new KeyFrame(Duration.seconds(0.017), new EventHandler<ActionEvent>() {
                public void handle(ActionEvent ae) {//Sólo puede haber un handle en el timeline
                    circleBall.setCenterX(ballCenterX);
                    ballCenterX += ballCurrentSpeedX * ballDirectionX; //le va sumando a la posición de la bola la variable ballCurrentSpeed (3) * la dirección (+1 o -1)
                    
                    if (ballCenterX >= 640){//Si la bola llega al final de la pantalla 640 tendrá que cambiar la dirección a la izquierda restando                      
                        ballDirectionX = -1; //cambia la dirección a la izquierda
                    } else if (ballCenterX<=0){//Cuando llegue al principio de la pantalla tendrá que ir otra vez hacia la derecha sumando
                        ballDirectionX = 1; //vuelve a cambiar la dirección a la derecha
                    }
                    
                    circleBall.setCenterY(ballCenterY);
                    ballCenterY += ballCurrentSpeedY * ballDirectionY; //le va sumando a la posición de la bola la variable ballCurrentSpeed (3) * la dirección (+1 o -1)
                    
                    if (ballCenterY >= 480){//Si la bola llega al final de la pantalla 640 tendrá que cambiar la dirección a la izquierda restando                      
                        ballDirectionY = -1; //cambia la dirección a la izquierda
                    } else if (ballCenterY<=0){//Cuando llegue al principio de la pantalla tendrá que ir otra vez hacia la derecha sumando
                        ballDirectionY = 1; //vuelve a cambiar la dirección a la derecha
                    } 
                }
            })                
        );
        timeline.setCycleCount(Timeline.INDEFINITE);//Llama al método setCycleCount (para que la animación siga indefinidamente
        timeline.play(); //Llama al método Play para echar a andar la animación
    }

    public static void main(String[] args) {
        launch();
    }

}