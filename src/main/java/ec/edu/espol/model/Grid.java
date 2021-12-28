/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espol.model;

import java.util.ArrayList;
import javafx.scene.layout.Pane;

/**
 *
 * @author Fernando
 */
public class Grid extends Pane{
    private ArrayList<ArrayList<Cell>> grid;
    private int filas;
    private int columnas;
    private int width;
    private int height;
   
    
    public Grid(int filas, int columnas, int width, int height){       
        this.grid = new ArrayList<>();
        for (int i = 0; i < columnas; i++) {
            grid.add(new ArrayList<>());
        }   
        this.filas = filas;
        this.columnas = columnas;
        this.width = width;
        this.height = height;  
        ajustarGrid();
    }
    
    private void ajustarGrid(){
        this.setPrefSize(width, height);
        this.setMinSize(width, height);
        this.setMaxSize(width, height);
        this.setStyle("-fx-background-color:white");
    }
    
    public boolean isAvailable(){
        return false;
    }
    
    public void generateGrid(){
        for(int f = 0; f < filas; f++){
            for(int c = 0; c < columnas; c++){
                Cell cell = new Cell(f, c);
                cell.setLayout(width, height, filas, columnas);
                grid.get(f).add(c, cell);
                getChildren().add(cell);
            }
        }
    }
    
    
}
