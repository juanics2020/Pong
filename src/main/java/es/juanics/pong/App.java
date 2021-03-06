package es.juanics.pong;

import java.util.Random;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * JavaFX App
 */
public class App extends Application {
//declaramos todas las variables generales aquí
    
    final short SCENE_HEIGHT = 480; //constante con el alto de la pantalla (scene)
    final short SCENE_WIDTH = 640; //constante para el ancho de la pantalla (scene)
    final short TEXT_SIZE = 24;
    
    short ballCenterX = 0;//Si declarara la variable dentro no serviría poque la estaría declarando cada vez que entra en el bucle
    byte ballDirectionX = 1; //Si la dirección es a la derecha (suma) y si es a la izquierda (resta)
    byte ballCurrentSpeedX = 6;//El incremento que irá tomando. Velocidad
    //sumar -1 es restar 1. (+-1) es igual que -1
    short ballCenterY = 0;//Si declarara la variable dentro no serviría poque la estaría declarando cada vez que entra en el bucle
    byte ballDirectionY = 1; //Si la dirección es arriba (restando) y si es abajo (sumando)
    byte ballCurrentSpeedY = 6;//El incremento que irá tomando.Velocidad
    //sumar -1 es restar 1. (+-1) es igual que -1
    
    short stickHeight = 50;//variable con la altura del rectángulo
    short stickPosY = (short)((SCENE_HEIGHT-stickHeight)/2);// si se pone el tipo de dato delante de un valor entre paréntesis lo convierte a ese tipo de datos. En este caso convertimos el resultado de toda la operación.
    byte stickCurrentSpeed = 6; //Velocidad en la que se moverá la pala
    byte stickDirection = 0; //Si multiplica por 1 iría hacia abajo y -1 hacia arriba. Empieza en 0 para que la pala aparezca primero parada
        
    int score = 0;//Puntuación actual    
    int highScore = 0;//Puntuación más alta
    Text textScore = new Text("0");//Para utilizar en la caja de texto que mostrará la puntuación actual
    
    
    //DECLARACIÓN MÉTODO PARA REINICIAR PARTIDA
    private void resetGame(){
        //Reiniciar partida
        score = 0;
        textScore.setText(String.valueOf(score));
        ballCenterX = 10;
        ballCurrentSpeedY = 3;
        //Posición inicial de la bola ALEATORIA
        Random random = new Random();
        ballCenterY = (short)(random.nextInt(SCENE_HEIGHT));
    }
    
    
    @Override
    public void start(Stage stage) {
       
        //StackPane (apila una cosa encima de otra en el panel. Así que no nos sirve, porque se pisan
        // StackPane root = new StackPane();//creo un nuevo objeto de tipo StackPane llamado root
        Pane root = new Pane();
        var scene = new Scene(root, SCENE_WIDTH, SCENE_HEIGHT);//le digo a la escena el panel principal (root) y el tamaño de la pantalla
        scene.setFill(Color.BLACK);//Le damos el color de fondo a la escena
        stage.setScene(scene);
        stage.setResizable(false);//Para que el usuario no pueda cambiar el tamaño de la pantalla
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
                      
        
        Rectangle rectStick = new Rectangle();//Creamos un nuevo objeto de clase Rectangle (en una variable)
        rectStick.setWidth(10);//Le damos ancho
        rectStick.setHeight(stickHeight);//Le damos alto
        rectStick.setX(SCENE_WIDTH-40);//Le damos posición horizontal. Origen de coordenadas en la esquina superior izquierda del rectángulo. PARA QUE SALGA CENTRADO HAY QUE RESTARLE LA MITAD DEL ANCHO
        rectStick.setY(stickPosY);//Le damos posición vertical. HAY QUE RESTARLE LA MITAD DEL ALTO DEL RECTÁNGULO para que salga centrado.
        //(480/2-100/2) es igual que ((480-100)/2). la segunda opción es mejor.
        rectStick.setFill(Color.BLUE);//Le damos color
        
        root.getChildren().add(rectStick);//Para que aparezca en la pantalla (panel root)
        
        for (int i=0; i<SCENE_HEIGHT; i+=30){
            Line line = new Line (SCENE_WIDTH/2, i, SCENE_WIDTH/2, i+10);
            line.setStroke(Color.BLUE);
            line.setStrokeWidth(4);
            root.getChildren().add(line);        
        }
        
        
        //LAYOUTS PARA MOSTRAR PUNTUACIONES
        //Layout principal
        HBox paneScores = new HBox();
        paneScores.setTranslateY(20);
        paneScores.setMinWidth(SCENE_WIDTH);
        paneScores.setAlignment(Pos.CENTER);
        paneScores.setSpacing(100);
        root.getChildren().add(paneScores);
        //Layout para puntuación actual
        HBox paneCurrentScore = new HBox();
        paneCurrentScore.setSpacing(10);
        paneScores.getChildren().add(paneCurrentScore);
        //Layout para puntuación máxima
        HBox paneHighScore = new HBox();
        paneHighScore.setSpacing (10);
        paneScores.getChildren().add(paneHighScore);
        //Texto de etiqueta para la puntuación
        Text textTitleScore = new Text("Score:");
        textTitleScore.setFont(Font.font(TEXT_SIZE));
        textTitleScore.setFill(Color.WHITE);
        //Texto para la puntuación
        //Text textScore = new Text("0");
        textScore.setFont(Font.font(TEXT_SIZE));
        textScore.setFill(Color.WHITE);
        //Texto de etiqueta para la puntuación máxima
        Text textTitleHighScore = new Text("Max. Score:");
        textTitleHighScore.setFont(Font.font(TEXT_SIZE));
        textTitleHighScore.setFill(Color.WHITE);
        //Texto para la puntuación máxima
        Text textHighScore = new Text("0");
        textHighScore.setFont(Font.font(TEXT_SIZE));
        textHighScore.setFill(Color.WHITE);
        //Añadir los textos a los layouts reservados para ellos
        paneCurrentScore.getChildren().add(textTitleScore);//caja de texto 1, puntuación actual
        paneCurrentScore.getChildren().add(textScore);//texto dentro de la caja de texto 1, puntuación actual
        paneHighScore.getChildren().add(textTitleHighScore);//caja de texto 2, puntuación máxima
        paneHighScore.getChildren().add(textHighScore);//texto dentro de la caja de texto 2, puntuación máxima
            
        
        scene.setOnKeyPressed(new EventHandler<KeyEvent>(){//Llama al método setOnKeyPressed. Cuando detecte que se pulsa una tecla en la escena (se puede hacer que en vez que en la escena se detecte cuando pulse dentro de un campo de texto)
            public void handle(final KeyEvent keyEvent){
                switch(keyEvent.getCode()){//Según la tecla pulsada
                    case UP:
                        stickDirection = -1;
                        break;
                     case DOWN:
                        stickDirection = 1;
                        break;
                }
                   
            }
        });
                
        
        // Game loop usando Timeline
        Timeline timeline = new Timeline(//Sirve para lo que lo que metamos aquí. Podemos utilizar varios TimeLine con diferentes velocidades para diferentes cosas
            // 0.017 ~= 60 FPS (equivalencia de segundos a Frames por Segundo)
            new KeyFrame(Duration.seconds(0.017), new EventHandler<ActionEvent>() {
                public void handle(ActionEvent ae) {//Sólo puede haber un handle en el timeline
                    circleBall.setCenterX(ballCenterX);
                    ballCenterX += ballCurrentSpeedX * ballDirectionX; //le va sumando a la posición de la bola la variable ballCurrentSpeed (3) * la dirección (+1 o -1)
                    
                    if (ballCenterX >= SCENE_WIDTH){//Si la bola llega al final de la pantalla 640 tendrá que cambiar la dirección a la izquierda restando                      
                        if (score > highScore){//Comprobar si hay una puntuación más alta
                            highScore = score;//guardamos la puntuación actual como la más alta
                            textHighScore.setText(String.valueOf(highScore));//Mostramos la nueva puntuación máxima en la caja de texto de la pantalla
                            //ballDirectionX = -1; //cambia la dirección a la izquierda
                        }
                        //Reiniciar partida
                        resetGame(); //Se usa el método de esta clase
                        // score = 0;
                        // textScore.setText(String.valueOf(score));
                        // ballCenterX = 10;
                        // ballCurrentSpeedY = 3;
                    } else if (ballCenterX<=0){//Cuando llegue al principio de la pantalla tendrá que ir otra vez hacia la derecha sumando
                        ballDirectionX = 1; //vuelve a cambiar la dirección a la derecha
                    }
                    
                    circleBall.setCenterY(ballCenterY);
                    ballCenterY += ballCurrentSpeedY * ballDirectionY; //le va sumando a la posición de la bola la variable ballCurrentSpeed (3) * la dirección (+1 o -1)
                    
                    if (ballCenterY >= SCENE_HEIGHT){//Si la bola llega al final de la pantalla 640 tendrá que cambiar la dirección a la izquierda restando                      
                        ballDirectionY = -1; //cambia la dirección a la izquierda
                    } else if (ballCenterY<=0){//Cuando llegue al principio de la pantalla tendrá que ir otra vez hacia la derecha sumando
                        ballDirectionY = 1; //vuelve a cambiar la dirección a la derecha
                    } 
                    // Si quisiéramos que la pala se vaya moviendo cada vez que se pulse la tecla lo pondríamos en el switch de la tecla para la pala.
                    //Como queremos que se siga moviendo con sólo pulsar una tecla se hace en la animación (timeline).
                    rectStick.setY(stickPosY);
                    stickPosY += stickCurrentSpeed * stickDirection; // sumo (la velocidad * (stickDirection 1 o -1)). Cuando stickDirection es 0 la pala no se mueve.
                    if (stickPosY <=0){//Si la pala sale por arriba o se sale por abajo la paro y la pongo en la posición correcta
                        stickDirection = 0;
                        stickPosY = 0;
                    }else if(stickPosY >= SCENE_HEIGHT-stickHeight){
                        stickDirection = 0;
                        stickPosY = (short)(SCENE_HEIGHT-stickHeight);// si da problemas de compatibilidad de variables le ponermos short delante                        
                    }
                    //Método intersect de la clase Shape. Como es STATIC no hace falta crear un objeto para guardar lo que devuelve, se puede usar directamente la clase (Shape).
                    Shape shapeCollision = Shape.intersect(circleBall,rectStick);//Creamos una variable de la clase Shape para guardar la intersección
                    boolean colisionVacia = shapeCollision.getBoundsInLocal().isEmpty(); //Si is Empty devuelve Falso es que tiene una forma y ha chocado (porque la intersección tine la forma que se sobrepone). Guardo el boolean que nos devuelve (verdadero o falso en una variable)
                    if (colisionVacia == false && ballDirectionX>0){// Si es falso (ha chocado)y la bola va a la derecha devolvemos un mensaje en pantalla
                        System.out.println("Ha colisionado");
                        ballDirectionX = -1;
                        score++;//cuando choque la bola con la pala incrementamos la puntuación
                        textScore.setText(String.valueOf(score));//mostramos la puntuación actualizada. Score es un Entero y hay que convertirlo a texto (String.valueOf).
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