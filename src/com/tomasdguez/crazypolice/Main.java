/*
 *
 * @author Tomás Dominguez Gómez
 * 1º DAW - Programación. 
 * 2º Trimestre | Curso 2018/19.
 * IES Ntra. Sra. Los Remedios. 
 * Ubrique (Cádiz).
 * www.tomasdguez.com
 * 
 */
package com.tomasdguez.crazypolice;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 *
 * @author Tomas de Aquino Dominguez Gómez
 */
public class Main extends Application {
    
    // Declaramos las constantes para la dimension de nuestra ventana del juego.
    final int SCENES_TAM_X = 800;
    final int SCENES_TAM_Y = 600;
    
    // Declaramos las variables de la posición del coche.
    int posCar_X = (SCENES_TAM_X/2) + 50 ;
    int posCar_Y = SCENES_TAM_Y - 90 ;
    
    // Declaramos las variables para la posicon de la pantalla.
    int posFondo_X = 200; 
    int posFondo_Y = 0;
    int posFondo_Y2 = -600;
    
    // Declaramos las variables para las lineas de colision.
    int lineaDer = SCENES_TAM_X + 600;
    int lineaIzq = SCENES_TAM_X + 200;
    
    // Declaramos las variables para el coche.
    int CAR_WIDTH = 50;
    int CAR_HEIGHT = 80;
    
    // Declaramos la variable para el texto.
    int TEXT_SIZE = 20;
    
    // Declaramos la variable score para la suma de puntos.
    int score = 0;
    
    // Declaramos la variable para la máxima puntuación.
    int highScore = 0;
    
    // Declaramos la variable para la velocidad del coche.
    int carSpeed;
    
    // Declaramos la variable textScore para el uso del boton "Start" y "ResetGame".
    Text textScore;
    Text textTitleScore;
    Text textMaxScore;
    Text textTitleMaxScore;
    
    // Declaramos la variable root con Pane.
    Pane root;
    
    // Declaramos los grupos de los metodos para la animación.
    Group pistaFondo1 = new Group();
    Group pistaFondo2 = new Group();
    
    // Declaramos el grupo para el jugador.
    Group player = new Group();
    
    // Declaramos el metodo para el reinicio del juego.
    private void resetGame(){
    score = 0;
    textScore.setText(String.valueOf(score));
    }
    
    // Declaramos el metodo para dibujar las lineas de la pista/carretera.
    private void lineasPista(int portionHeight, int portionWidth, int portionSpacing){
                                   //(inicioStartX, inicioStartY, InicioFinalX, InicioFinalY)
            Line lineLeft = new Line (SCENES_TAM_X/4, SCENES_TAM_Y, SCENES_TAM_X/4, SCENES_TAM_Y - 600);
            lineLeft.setStroke(Color.WHITE);
            lineLeft.setStrokeWidth(portionWidth);
            
            Line lineRight = new Line ((SCENES_TAM_X/4)+400, SCENES_TAM_Y, (SCENES_TAM_X/4)+400, SCENES_TAM_Y - 600);
            lineRight.setStroke(Color.WHITE);
            lineRight.setStrokeWidth(portionWidth);
                        
            root.getChildren().addAll(lineLeft, lineRight);
    }

    // Declaramos el metodo para dibujar el fondo negro de la pista.
    private void fondo1() {
        // Fondo de la pisa de carretera.
        Image fondoImg = new Image("carretera.jpg");
        ImageView plano = new ImageView();
        plano.setImage(fondoImg);
        
        // Grupo para el fondo del juego.
        pistaFondo1.getChildren().add(plano);
        pistaFondo1.setLayoutX(posFondo_X);
        pistaFondo1.setLayoutY(posFondo_Y);
        
        // Muestra de la image del fondo
        root.getChildren().add(pistaFondo1);
    }
    
    // Declaramos el método para la imagen 2 del fondo.
    private void fondo2() {
        // Fondo de la pisa de carretera.
        Image fondoImg = new Image("carretera.jpg");
        ImageView plano1 = new ImageView();
        plano1.setImage(fondoImg);
        
        // Grupo para el fondo 2 del juego.
        pistaFondo2.getChildren().add(plano1);
        pistaFondo2.setLayoutX(posFondo_X);
        pistaFondo2.setLayoutY(posFondo_Y2);
        
        // Muestra de la image del fondo
        root.getChildren().add(pistaFondo2);
    }
    
    // Declaramos el metodo para el resto de coches.
    private void cocheObj () {
        Rectangle rectCar = new Rectangle(posCar_X, posCar_Y, CAR_WIDTH, CAR_HEIGHT);
        rectCar.setFill(Color.LIGHTGREEN);
        
        Rectangle rectCarSom = new Rectangle((posCar_X + 2.5), (posCar_Y + 2.5), (CAR_WIDTH - 5), (CAR_HEIGHT -5));
        rectCarSom.setFill(Color.GREEN);
        
        Rectangle techoCar = new Rectangle((posCar_X + 3), (posCar_Y + 20), (CAR_WIDTH - 6 ), (CAR_HEIGHT / 2));
        techoCar.setFill(Color.YELLOWGREEN);
        
        root.getChildren().addAll(rectCar, rectCarSom, techoCar);
        
    }
    
    // Declaramos el metodo para dibujar el obstaculo.
    private void arbol () {
        
       
    }
    
    // Declaramos el metodo para dibujar el obstaculo.
    private void piedra () {
        // Obstaculo de piedra para el juego.
        Image obsA = new Image("a.png");
        ImageView obstaculoA = new ImageView();
        obstaculoA.setImage(obsA);
        
        // Grupo para el fondo 2 del juego.
        Group piedraGroup = new Group();
        piedraGroup.getChildren().add(obstaculoA);
        piedraGroup.setLayoutX(posFondo_X);
        piedraGroup.setLayoutY(posFondo_Y2);
        
        // Muestra de la image del fondo
        root.getChildren().add(piedraGroup);
        
    }
    
    // Declaramos el metodo para el jugador.
    private void jugador () {
        // LLamada al coche player animado.
        Image policeCar = new Image("police-car.gif");
        ImageView carPolice = new ImageView();
        carPolice.setImage(policeCar);
        
        // LLamada Grupo PlayerCar Police.
        player.getChildren().add(carPolice);
        player.setTranslateX(posCar_X);
        player.setTranslateY(posCar_Y);
        root.getChildren().add(player);
    }
    
    
    @Override
    public void start(Stage primaryStage) {
        
        // Declaramos dimensiones y color de fondo de la pantalla del juego.
        root = new Pane();
        Scene scene = new Scene(root, SCENES_TAM_X, SCENES_TAM_Y, Color.GREEN); //Color de Fondo.
        primaryStage.setTitle("Crazy Police");
        //primaryStage.getIcons().add(new Image ("fileName.png")) // Icono Aplicación.
        primaryStage.setScene(scene);
        primaryStage.show();
        
        // Llamada al fondo.
        fondo1();
        fondo2();
        
        // LLamada a la piedra de objeto
        piedra();
        
        // Llamamos al metodo de las lineas.
        lineasPista(20,6,40);
          
        // LLamaos al jugador.
        jugador();
        
        // Creamos los marcadores de máxima puntuación y la puntuación de partida.
        // Creamos el primer LAYOUTS.
        HBox paneScores = new HBox();
        paneScores.setTranslateY(10);
        paneScores.setMinWidth(SCENES_TAM_X);
        paneScores.setAlignment(Pos.CENTER);
        paneScores.setSpacing(0);
        root.getChildren().add(paneScores);
                
        // Creamos el segundo LAYOUTS para la puntuación de partida.
        HBox paneCurrentScores = new HBox();
        paneCurrentScores.setSpacing(80);
        paneScores.getChildren().add(paneCurrentScores);
        
        // Creamos el tercer LAYOUTS para la puntuación máxima de partida.
        HBox paneHighScores = new HBox();
        paneHighScores.setSpacing(10);
        paneScores.getChildren().add(paneHighScores);
        
        // Creamos la Etiqueta texto para la puntuación de partida.
        textTitleScore = new Text("PUNTOS:");
        textTitleScore.setFont(Font.font(TEXT_SIZE));
        textTitleScore.setFill(Color.WHITE);
        
        // Creamos el Resultado de la puntuación de partida.
        textScore = new Text(String.valueOf(score));
        textScore.setFont(Font.font(TEXT_SIZE));
        textScore.setFill(Color.WHITE); 
       
        // Creamos la Etiqueta para Máxima Puntuación.
        textTitleMaxScore = new Text("PUNTOS MAX:");
        textTitleMaxScore.setFont(Font.font(TEXT_SIZE));
        textTitleMaxScore.setFill(Color.WHITE);
        
        // Creamos el Resultado de la puntación de Max Puntuación.
        textMaxScore = new Text(String.valueOf(highScore));
        textMaxScore.setFont(Font.font(TEXT_SIZE));
        textMaxScore.setFill(Color.WHITE);
        
        // Añadimos los textos a los LAYOUTS reservados para ellos.
        paneCurrentScores.getChildren().add(textTitleScore);
        paneCurrentScores.getChildren().add(textScore);
        paneCurrentScores.getChildren().add(textTitleMaxScore);
        paneCurrentScores.getChildren().add(textMaxScore);
       
        // Animación del fondo y movimiento del mismo.
        AnimationTimer animacion;
        animacion = new AnimationTimer() {
            
        @Override
        public void handle(long now) {
                
                // Cominezo Animación del fondo.
                pistaFondo1.setLayoutY(posFondo_Y);
                pistaFondo2.setLayoutY(posFondo_Y2);
                
                posFondo_Y += 2;
                posFondo_Y2 += 2;
                
                if (posFondo_Y == 600) {
                    posFondo_Y = -600;
                }
                if (posFondo_Y2 == 600) {
                    posFondo_Y2 = -600;
                }

                //Animación del player.
                player.setLayoutX(posCar_X);
                player.setLayoutY(posCar_Y);
                posCar_X += carSpeed;
                
                
                
                posCar_X += carSpeed;
                if(posCar_X < 0){
                    posCar_X = 0;
                } else {
                    if (posCar_X > lineaDer - player ){
                        posCar_X = lineaDer - player ;
                    }
                }
                player.setLayoutX(posCar_X);
              
                
            } // Final Handle
        };
        
        scene.setOnKeyPressed((KeyEvent event) -> {
            switch(event.getCode()){
                case LEFT:
                    //Pulsa tecla izquierda.
                    carSpeed = -6;
                    break;
                case RIGHT:
                    //Pulsa tecla derecha.
                    carSpeed = 6;
                    break;
                }
            });
        scene.setOnKeyReleased((KeyEvent event) -> {
            // Velocidad 0 al no pulsar tecla
            carSpeed = 0;
        });
        
        // Cominezo de la animación.
        animacion.start();
                
    }// Final Metodo Start.        
}// Fina Programa