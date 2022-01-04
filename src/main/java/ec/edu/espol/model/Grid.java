  package ec.edu.espol.model;

import custom.controls.GridButton;
import game.Symbol;
import java.util.ArrayList;
import java.util.Iterator;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import model.players.Player;
import model.table.Table;

/**
 *
 * @author Fernando
 */
public class Grid extends Pane implements Comparable<Grid>{
    private ArrayList<ArrayList<Cell>> grid;
    private int filas;
    private int columnas;
    private int width;
    private int height;
    private boolean state;
    private int utility;
   
    
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

    public Grid() {
        this.utility = 0;
    }
    
    
    private void ajustarGrid(){
        this.setPrefSize(width, height);
        this.setMinSize(0, 0);
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
    
    private Symbol getSymbol(int i){
        Cell cell = (Cell) this.getChildren().get(i);
        return cell.getSymbol();
    }

    private boolean rowsAndColumns(Symbol opponentSymbol, int i, int m){
        return getSymbol(i) != opponentSymbol && getSymbol(i+m) != opponentSymbol && getSymbol(i+m*2) != opponentSymbol;
    }

    private int utilityByRows(Symbol opponentSymbol){
        int count = 0;
        for (int i = 0; i < 7; i += 3) {
            if (rowsAndColumns(opponentSymbol, i, 1)) count++;
        }
        return count;
    }

    private int utilityByColumns(Symbol opponentSymbol){
        int count = 0;
        for (int i = 0; i < 3; i++) {
            if (rowsAndColumns(opponentSymbol, i, 3))
                count++;
        }
        return count;
    }

    private int utilityByDiagonals(Symbol opponentSymbol){
        if(getSymbol(4) == opponentSymbol) 
            return 0;
        int count = 0;
        if(getSymbol(0) != opponentSymbol && getSymbol(8) != opponentSymbol) 
            count++;
        if(getSymbol(2) != opponentSymbol && getSymbol(6) != opponentSymbol) 
            count++;
        return count;
    }
    
    @Override
    public int compareTo (Grid o) {
        return this.getUtility() - o.getUtility();
    }

    public int getUtility() {
        return utility;
    }

    public void setUtility(int utility) {
        this.utility = utility;
    }



    public void generateUtility(Symbol p1Symbol, Symbol p2Symbol){
        int computer = utilityByRows(p2Symbol) + utilityByColumns(p2Symbol) + utilityByDiagonals(p2Symbol);
        int human = utilityByRows(p1Symbol) + utilityByColumns(p1Symbol) + utilityByDiagonals(p1Symbol);
        setUtility(computer-human);
    }

    public ArrayList<ArrayList<Cell>> getGrid() {
        return grid;
    }

    public int getFilas() {
        return filas;
    }

    public int getColumnas() {
        return columnas;
    }

    public void setGrid(ArrayList<ArrayList<Cell>> grid) {
        this.grid = grid;
    }

    public void setFilas(int filas) {
        this.filas = filas;
    }

    public void setColumnas(int columnas) {
        this.columnas = columnas;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setState(boolean state) {
        this.state = state;
    }

    public boolean isState() {
        return state;
    }
    
    public Grid copy(int width, int height){
        Grid result = new Grid(3, 3, width, height);
        result.generateGrid();
        for(int f = 0; f < this.filas; f++){
            for(int c = 0; c < this.columnas; c++){
                result.grid.get(f).add(c,this.grid.get(f).get(c));
            }
        }
        return result;
    }
    
    public int availableSpaces(){
        int count = 0;
        for(int f = 0; f < this.filas; f++){
            for(int c = 0; c < this.columnas; c++){
                if(this.grid.get(f).get(c).getSymbol().equals(null)){
                    count++;
                }
            }
        }
        return count;
    }
    
    
    /*Recorre el arreglo para generar los distintos movimientos posibles*/
    public ArrayList<Grid> generateMoves(Symbol s){
        ArrayList<Grid> output = new ArrayList<>();
        for(int f = 0; f < filas; f++){
            for(int c = 0; c < columnas; c++){
                if(this.grid.get(f).get(c).getSymbol() == null){
                    Grid result = this.copy(300, 300);
                    Cell cell = new Cell(f, c);
                    cell.setLayout(result.width, result.height, result.filas, result.columnas);
                    cell.setSymbol(s);
                    cell.setImage();
                    result.grid.get(f).add(c, cell);
                    result.getChildren().add(cell);
                    output.add(result);
                }
            }
        }
        return output;
    }   

    
    
    
    
}
