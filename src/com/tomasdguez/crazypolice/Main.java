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

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 *
 * @author Tomas
 */
public class Main extends Application {
    // Declaramos las constantes para la dimension de nuestra ventana del juego.
    final int SCENES_TAM_X = 800;
    final int SCENES_TAM_Y = 600;
    
    // Declaramos las contantes del coche del jugador.
    
    // Declaramos las variables de la posición del coche.
    int posCar = (SCENES_TAM_X) / 2 ;
    
    // Declaramos la variable para el texto.
    int TEXT_SIZE = 20;
    
    // Declaramos la variable score para la suma de puntos.
    int score = 0;
    
    // Declaramos la variable para la máxima puntuación.
    int highScore;
    
    // Declaramos la variable textScore para el uso del boton "Start" y "ResetGame".
    Text textScore;
    Text textTitleScore;
    Text textMaxScore;
    Text textTitleMaxScore;
    
    // Declaramos la variable root con Pane.
    Pane root;
    
    // Declaramos el metodo para el reinicio del juego.
    private void resetGame(){
    score = 0;
    textScore.setText(String.valueOf(score));
    }
    
    // Declaramos el metodo para dibujar las lineas de la pista/carretera.
    private void lineasPista(int portionHeight, int portionWidth, int portionSpacing){
        for(int i=0; i<SCENES_TAM_Y; i+=portionSpacing){
            Line line = new Line(SCENES_TAM_X/2, i, SCENES_TAM_X/2, i+portionHeight);
            line.setStroke(Color.WHITE); // Color de la linea.
            line.setStrokeWidth(portionWidth);
            root.getChildren().add(line);
        }
    }
    
    // Declaramos el metodo para dibujar el fondo negro de la pista.
    private void fondoPista() {
        Rectangle pista = new Rectangle(SCENES_TAM_X/2, SCENES_TAM_Y);
        pista.setStroke(Color.BLACK);
        //pista.isPointInPath(SCENES_TAM_X/2, SCENES_TAM_Y);
        root.getChildren().add(pista);
    }    
    
    @Override
    public void start(Stage primaryStage) {
        // Declaramos dimensiones y color de fondo de la pantalla del juego.
        root = new Pane();
        Scene scene = new Scene(root, SCENES_TAM_X, SCENES_TAM_Y, Color.GREEN); //Color de Fondo.
        primaryStage.setTitle("Crazy Police");
        primaryStage.setScene(scene);
        primaryStage.show();
        
        //Llamamos al metodo del fondo de la Pista.
        fondoPista();
        
        // LLamamos al metodo de las lineas.
        lineasPista(20,6,40);
        
    }
    
    // Prueba de commit
    
}
