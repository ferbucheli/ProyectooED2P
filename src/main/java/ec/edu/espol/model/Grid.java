  package ec.edu.espol.model;

import custom.controls.GridButton;
import game.Symbol;
import java.util.ArrayList;
import java.util.Iterator;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
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
    
    private Symbol getSymbolGridButton(int i){
        GridButton gb = (GridButton) this.getChildren().get(i);
        return gb.currentSymbol();
    }

    private boolean rowsAndColumns(Symbol opponentSymbol, int i, int m){
        return getSymbolGridButton(i) != opponentSymbol && getSymbolGridButton(i+m) != opponentSymbol && getSymbolGridButton(i+m*2) != opponentSymbol;
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
        if(getSymbolGridButton(4) == opponentSymbol) 
            return 0;
        int count = 0;
        if(getSymbolGridButton(0) != opponentSymbol && getSymbolGridButton(8) != opponentSymbol) 
            count++;
        if(getSymbolGridButton(2) != opponentSymbol && getSymbolGridButton(6) != opponentSymbol) 
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


    
    
    
    
}
