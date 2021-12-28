/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espol.model;

import game.Symbol;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

/**
 *
 * @author Fernando
 */
public class Cell extends StackPane{
    
    private Symbol symbol;
    private int fila;
    private int columna;
    
    
    public Cell(int fila, int columna){
        this.symbol = null;
        this.fila = fila;
        this.columna = columna;
    }
    
    public void setLayout(int width, int height, int f, int c){
        double w = width / c;
        double h = height / f;
        double x = w * fila;
        double y = h * columna;
        setLayoutX(x);
        setLayoutY(y);
        setPrefWidth(w);
        setPrefHeight(h);
        setStyle("-fx-background-color: white; -fx-border-color: black;");
    }
    
    public void setImage(Symbol s){
        return;
    }
    
}
