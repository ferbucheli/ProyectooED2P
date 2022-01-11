package ec.edu.espol.model;

import game.Symbol;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;

/**
 *
 * @author Fernando
 */
public class Cell extends StackPane{
    
    private Symbol symbol;
    private int fila;     /*Posicion en la que se encuentra en el Grid*/
    private int columna;  
    private int ancho;
    private int alto;
    
    
    public Cell(int fila, int columna){
        this.symbol = null;
        this.fila = fila;
        this.columna = columna;
    }
    
    public void setLayout(int width, int height, int f, int c){
        ancho = width/ c;
        alto = height / f;
        double x = ancho * fila;
        double y = alto * columna;
        setLayoutX(x);
        setLayoutY(y);
        setPrefWidth(ancho);
        setPrefHeight(alto);
        setStyle("-fx-background-color: white; -fx-border-color: black;  -fx-border: 20px solid; -fx-background-radius: 15.0;");
    }
    
    public void setImage(){
        if(this.symbol.equals(Symbol.X)){
            Image img = new Image("images/x1.png");
            ImageView imv = new ImageView(img);
            imv.setFitWidth(ancho - 50);
            imv.setFitHeight(alto - 50);
            this.getChildren().add(imv);
        } else{
            Image img = new Image("images/o.png");
            ImageView imv = new ImageView(img);
            imv.setFitWidth(ancho - 50);
            imv.setFitHeight(alto - 50);
            this.getChildren().add(imv);
        } 
    }
    
    public void setIcon(){
        if(this.symbol.equals(Symbol.X)){
            Text txt = new Text("X");
            this.getChildren().add(txt);
        } else{
            Text txt = new Text("O");
            this.getChildren().add(txt);
        } 
    }

    public Symbol getSymbol() {
        return symbol;
    }

    public int getFila() {
        return fila;
    }

    public int getColumna() {
        return columna;
    }

    public void setSymbol(Symbol symbol) {
        this.symbol = symbol;
    }

    public void setFila(int fila) {
        this.fila = fila;
    }

    public void setColumna(int columna) {
        this.columna = columna;
    }
    
    
    
}
