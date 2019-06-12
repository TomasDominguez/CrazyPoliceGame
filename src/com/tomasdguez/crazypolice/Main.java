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

import static java.awt.SystemColor.text;
import static java.lang.Math.random;
import static java.lang.StrictMath.random;
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
    
    int lineaDer = anchuraVentana - 600;
    int lineaIzq = anchuraVentana - 200;
    int limitDer = -230;
    int limitIzq = 130;
    
    double velObsActual = 3;
    double dificultad = 0.7;
    double velObsX = 3;
    double velObsY = 3;
    
    double posObsX;
    double posObsY;
    
    int velCoche = 0;
    int posCocheX = anchuraVentana/2;
    int posCocheY = alturaVentana -100;
    
    int posRCocheX = posCocheX+30;
    int posRCocheY = posCocheY+20;
    
    int posFondoX = 100;
    int posFondoY = 0;
    int posFondoY_2 = -600;
    int posFondoX_Ran = posFondoX;
    int posFondoY_Ran = posFondoY;
    
    int puntos;
    int maxPuntos;
    
    Group pistaFondo1 = new Group();
    Group pistaFondo2 = new Group();
    Group player = new Group();
    Group obstA = new Group();
    Group obstB = new Group();
    
    Text texto;
    Text textoMax;
    Random random;
    
    // Metodo de reinicio.
    public void reinicio() {
       puntos = 0;
       texto.setText(String.valueOf(puntos));
       
       random = new Random();
       posObsX = random.nextInt(limitIzq);
       posObsY = random.nextInt(alturaVentana/2);
       
       System.out.println("PosX: "+ posObsX + " PosY: "+ posObsY);

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
        
        // Marcadores de Puntuación.
        texto = new Text();
        texto.setText("Puntos:  " + puntos);
        texto.setTranslateX(alturaVentana-595);
        texto.setTranslateY(anchuraVentana-580);
        texto.setWrappingWidth(200);
        texto.setFill(Color.WHITE);
        
        textoMax = new Text();
        textoMax.setText("Puntos Mx: "+ maxPuntos);
        textoMax.setTranslateX(alturaVentana-595);
        textoMax.setTranslateY(anchuraVentana-550);
        textoMax.setWrappingWidth(200);
        textoMax.setFill(Color.WHITE);
        
        // Llamada al metodo de reinicio.
        reinicio();
        
        // Primer Obstaculo del juego.
        Image obsA = new Image("a.png", 50, 50, false, false);
        ImageView obstaculoA = new ImageView();
        obstaculoA.setImage(obsA);
        
        Circle polObs = new Circle(25, 20, 25);
        polObs.setFill(javafx.scene.paint.Color.RED);
        polObs.setOpacity(0.0);
        
        // Añadiendo al grupo la imagen y el poligono además de darle las posiciones.
        obstA.getChildren().addAll(obstaculoA, polObs);
        obstA.setLayoutY(posObsY*2);

        // Segundo Obstaculo del juego..
        Image obsB = new Image("b.png", 50, 50, false, false);
        ImageView obstaculoB = new ImageView();
        obstaculoB.setImage(obsB);
        
        Circle polObs2 = new Circle(25, 20, 25);
        polObs2.setFill(javafx.scene.paint.Color.RED);
        polObs2.setOpacity(0.0);
        
        // Añadiendo al grupo la imagen y el poligono además de darle las posiciones.
        obstB.getChildren().addAll(obstaculoB, polObs2);
        obstB.setLayoutY(posObsY*4);

        // Declaramos las lineas laterales de la carretera.
        Line lineLeft = new Line (anchuraVentana/6, alturaVentana, anchuraVentana/6, alturaVentana - 600);
        lineLeft.setStroke(Color.WHITE);
         
        Line lineRight = new Line ((anchuraVentana/4)+350, alturaVentana, (anchuraVentana/4)+350, alturaVentana - 600);
        lineRight.setStroke(Color.WHITE);
        
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
        root.getChildren().addAll( pistaFondo1, pistaFondo2, lineLeft, lineRight, texto, textoMax, obstB, obstA, player);
         
        // Cominezo de la animación.
        AnimationTimer animation = new AnimationTimer(){
            @Override
            public void handle(long now) {
                //Movimiento Obstaculos
                obstA.setLayoutX(posObsX*4);
                obstB.setLayoutX(posObsX*2);
                obstA.setLayoutY(posObsY);
                obstB.setLayoutY(posObsY);
                posObsY += 2;
                
                if( posObsY >= alturaVentana){
                    posObsY = random.nextInt(alturaVentana-599);
                    posObsX = random.nextInt(anchuraVentana-400);
                }
                
                System.out.println("Animation X: "+ posObsX+ " Animation Y: "+ posObsY);
                
                
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
                if(posCocheX < limitDer){
                    posCocheX = limitDer;
                } else {
                    if (posCocheX > limitIzq) {
                        posCocheX = limitIzq;
                    }
                }
                player.setLayoutX(posCocheX);
                //System.out.println("Pos coche: " + posCocheX);
                
                // Colisión del obtjeto con el coche.
                Shape colisionObj = Shape.intersect(rCoche, polObs);
                Shape colisionObj2 = Shape.intersect(rCoche, polObs2);
                boolean colisionObjVacia = colisionObj.getBoundsInLocal().isEmpty();
                boolean colisionObjVacia2 = colisionObj2.getBoundsInLocal().isEmpty();
                
                if (colisionObjVacia == false) {
                    System.out.println("Hay Colision");
                    
                    // Almacenamiento de Maxima Puntuación.
                    maxPuntos = puntos;
                    textoMax.setText(String.valueOf(maxPuntos));
                    
                    // Aumentamos velocidad.
                    posObsY += 4;
                    
                    // Reiniciamos el juego al colisionar.
                    reinicio();
                } else {
                    //puntos.
                    if (colisionObjVacia == true) {
                        puntos ++;
                        texto.setText(String.valueOf(puntos));
                    } 
                }
                
                if (colisionObjVacia2 == false) {
                    System.out.println("Hay Colision");
                    
                    // Almacenamiento de Maxima Puntuación.
                    maxPuntos = puntos;
                    textoMax.setText(String.valueOf(puntos));
                    // Aumentamos velocidad.
                    posObsY += 4;
                    
                    // Reiniciamos el juego al colisionar.
                    reinicio();
                    

                } else {
                    //puntos.
                    if (colisionObjVacia2 == true) {
                        puntos ++;
                        texto.setText(String.valueOf(puntos));
                    }
                }

            } // Final Handle
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