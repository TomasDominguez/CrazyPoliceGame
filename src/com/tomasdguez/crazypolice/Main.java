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
    
    int posCocheX = (anchuraVentana/2) +50;
    int posCocheY = alturaVentana - 90;
    
    int posFondoX = 100;
    int posFondoY = 0;
    int posFondoY_2 = -600;
    
    int puntos = 0;
    
    Group pistaFondo1 = new Group();
    Group pistaFondo2 = new Group();
    Group player = new Group();
    
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
        
        // Declaramos las lineas laterales de la carretera.
        Line lineLeft = new Line (alturaVentana/6, anchuraVentana, alturaVentana/6, anchuraVentana - 600);
        lineLeft.setStroke(Color.WHITE);
            
        Line lineRight = new Line ((alturaVentana/4)+350, anchuraVentana, (alturaVentana/4)+350, anchuraVentana - 600);
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
        
        // LLamada Grupo PlayerCar Police.
        player.getChildren().add(jugador);
        player.setTranslateX(posCocheX);
        player.setTranslateY(posCocheY);

        // Muestra de imagenes de los grupos                
        root.getChildren().addAll(pistaFondo1, pistaFondo2, lineLeft, lineRight, texto, player);
        
        // Cominezo de la animación.
        AnimationTimer animation = new AnimationTimer(){
            @Override
            public void handle(long now) {
                //Movimiento Obstaculos
                
                // Pruena Movimiento Fondo.
                pistaFondo1.setLayoutY(posFondoY);
                pistaFondo2.setLayoutY(posFondoY_2);
                posFondoY += 2;
                posFondoY_2 +=2;
                
                if( posFondoY == 600){
                    posFondoY -= -600;
                }
                if( posFondoY_2 == 600){
                    posFondoY_2 -= -600;
                }
            }
        }; // Final Animación.
        
        // Llama a la animación.
        animation.start();
                
    } // Final Metodo Start.

} // Final Programa