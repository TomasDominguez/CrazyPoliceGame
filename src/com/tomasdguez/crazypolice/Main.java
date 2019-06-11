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

import java.util.Random;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 *
 * @author Tomas de Aquino Dominguez Gómez
 */
public class Main extends Application {
    // Declaramos las varaibles o constatntes.
    int alturaVentana = 600;
    int anchuraVentana = 600;
    
    int lineaDer = anchuraVentana + 600;
    int lineaIzq = anchuraVentana + 200;
    
    double velObsActual = 3;
    double dificultad = 0.7;
    double velObsX = 3;
    double velObsY = 3;
    
    double posObsX = lineaDer/2;
    double posObsY = alturaVentana/2;
    
    int velCoche = 0;
    int posCocheX = anchuraVentana/2;
    int posCocheY = alturaVentana -100;
    
    int posRCocheX = posCocheX+30;
    int posRCocheY = posCocheY+20;
    
    int posFondoX = 100;
    int posFondoY = 0;
    int posFondoY_2 = -600;
    
    int puntos = 0;
    
    int portionWidth;
    int loadJugador;
    
    Group pistaFondo1 = new Group();
    Group pistaFondo2 = new Group();
    Group player = new Group();
    Group obstA = new Group();
    Group obstB = new Group();
    
    
    
    public void punto(){
        velObsActual = 3;
        velObsX = 3;
        velObsY = 3;
        posObsX = lineaDer/2;
        posObsY = alturaVentana/2;
    }
    
    @Override
    public void start (Stage primaryStage) {
        Pane root = new Pane();
        Scene scene = new Scene(root, alturaVentana, anchuraVentana);
        scene.setFill(Color.GREEN);
        primaryStage.setTitle("Crazy Police");
        primaryStage.setScene(scene);
        primaryStage.show();
        
        // Pintamos el fondo de la carretera.
        // Fondo de la pisa de carretera.
        Image fondoImg = new Image("carretera.jpg");
        ImageView plano = new ImageView();
        plano.setImage(fondoImg);

        pistaFondo1.getChildren().add(plano);
        pistaFondo1.setLayoutX(posFondoX);
        pistaFondo1.setLayoutY(posFondoY);

        // Segunda Imagen de Fondo de la carretera.
        Image fondoImg2 = new Image("carretera.jpg");
        ImageView plano1 = new ImageView();
        plano1.setImage(fondoImg2);

        pistaFondo2.getChildren().add(plano1);
        pistaFondo2.setLayoutX(posFondoX);
        pistaFondo2.setLayoutY(posFondoY_2);
        
        // Primer Obstaculo del juego.
        Image obsA = new Image("a.png", 50, 50, false, false);
        ImageView obstaculoA = new ImageView();
        obstaculoA.setImage(obsA);
        
        Circle polObs = new Circle(25, 20, 25);
        polObs.setFill(javafx.scene.paint.Color.RED);
        polObs.setOpacity(0.0);
        
        // Añadiendo al grupo la imagen y el poligono además de darle las posiciones.
        obstA.getChildren().addAll(obstaculoA, polObs);
        obstA.setLayoutX(posFondoX);
        obstA.setLayoutY(posFondoY);
        
        // Segundo Obstaculo del juego..
        Image obsB = new Image("b.png", 50, 50, false, false);
        ImageView obstaculoB = new ImageView();
        obstaculoB.setImage(obsB);
        
        Circle polObs2 = new Circle(25, 20, 25);
        polObs2.setFill(javafx.scene.paint.Color.RED);
        polObs.setOpacity(0.0);
        
        // Añadiendo al grupo la imagen y el poligono además de darle las posiciones.
        obstB.getChildren().addAll(obstaculoB, polObs2);
        obstB.setLayoutX(posFondoX*2);
        obstB.setLayoutY(posFondoY*2);
        
        // Declaramos las lineas laterales de la carretera.
        Line lineLeft = new Line (anchuraVentana/6, alturaVentana, anchuraVentana/6, alturaVentana - 600);
        lineLeft.setStroke(Color.WHITE);
         
        Line lineRight = new Line ((anchuraVentana/4)+350, alturaVentana, (anchuraVentana/4)+350, alturaVentana - 600);
        lineRight.setStroke(Color.WHITE);
        
        // Marcadores de Puntuación.
        Text texto = new Text();
        texto.setText("Puntuación:  "+"0");
        texto.setTranslateX(alturaVentana/4);
        texto.setTranslateY(anchuraVentana-580);
        texto.setWrappingWidth(200);
        texto.setFill(Color.WHITE);
        
        // Coche del Jugador
        Image cocheJugador = new Image("police-car.gif");
        ImageView jugador = new ImageView();
        jugador.setImage(cocheJugador);
        
        //Rectangulo para la colisión del coche.
        Rectangle rCoche = new Rectangle(30, 20, 35, 65);
        rCoche.setFill(javafx.scene.paint.Color.RED);
        rCoche.setOpacity(0.0);
            
        // Añadiendo al grupo todo el contenido de la imagen y el poligono.    
        player.getChildren().addAll(jugador, rCoche);
        player.setTranslateX(posCocheX);
        player.setTranslateY(posCocheY);
            
        // Muestra de imagenes de los grupos                
        root.getChildren().addAll( pistaFondo1, pistaFondo2, lineLeft, lineRight, texto, obstB, obstA, player);
        
        // Cominezo de la animación.
        AnimationTimer animation = new AnimationTimer(){
            @Override
            public void handle(long now) {
                //Movimiento Obstaculos
                obstA.setLayoutY(posFondoY);
                obstB.setLayoutY(posFondoY_2);
                
                // Pruena Movimiento Fondo.
                pistaFondo1.setLayoutY(posFondoY);
                pistaFondo2.setLayoutY(posFondoY_2);
                posFondoY += 2;
                posFondoY_2 +=2;
                
                if( posFondoY == 600){
                    posFondoY = -600;
                }
                if( posFondoY_2 == 600){
                    posFondoY_2 = -600;
                }
                
                //System.out.println("Pos Fondo 1: " + posFondoY + " Pos Fondo 2: "+ posFondoY_2);
                
                // Movimiento del coche.
                
                posCocheX += velCoche;
                if(posCocheX < -230){
                    posCocheX = -230;
                } else {
                    if (posCocheX > 130) {
                        posCocheX = 130;
                    }
                }
                player.setLayoutX(posCocheX);
                //System.out.println("Pos coche: " + posCocheX);
                
                // Colisión del obtjeto con el coche.
                Shape colisionObj = Shape.intersect(rCoche, polObs);
                boolean colisionObjVacia = colisionObj.getBoundsInLocal().isEmpty();
                
                if (colisionObjVacia == false) {
                    System.out.println("Hay Colision");
                    
                }
                Shape colisionObj2 = Shape.intersect(rCoche, polObs2);
                boolean colisionObjVacia2 = colisionObj2.getBoundsInLocal().isEmpty();
                
                if (colisionObjVacia2 == false) {
                    System.out.println("Hay Colision Segundo Objeto");
                }
            }
        }; // Final Animación.
        
        // Movimiento del coche.
        scene.setOnKeyPressed((KeyEvent event) -> {
            switch(event.getCode()) {
                case RIGHT:
                    // Pulso de tecla izquierda.
                    velCoche = 6;
                    break;
                case LEFT:
                    // Pulso de tecla izquierda.
                    velCoche = -6;
                    break;
            }
        });
        
        scene.setOnKeyReleased((KeyEvent event) -> {
            velCoche = 0;
        });
        
        // Llama a la animación.
        animation.start();
        
    } // Final Metodo Start.

} // Final Programa