package tests.GameValidation;

import ec.edu.espol.model.Cell;
import ec.edu.espol.model.Grid;
import game.Symbol;
import java.util.ArrayList;
import validation.GameValidator;

/**
 *
 * @author eduar
 */
public class MainTest {
    public static void main(String[] args) {
        System.out.println("Validacion Diagonal");
        Grid tablero1 = new Grid(3, 3, 300, 300);
        tablero1.generateGrid();
        ArrayList<ArrayList<Cell>> tableGame1 = tablero1.getGrid();
        tableGame1.get(0).get(0).setSymbol(Symbol.O);
        tableGame1.get(1).get(1).setSymbol(Symbol.O);
        tableGame1.get(2).get(2).setSymbol(Symbol.O);
        System.out.println(GameValidator.diagonalValidation(tablero1));
        
        System.out.println("Validacion Vertical");
        Grid tablero2 = new Grid(3, 3, 300, 300);
        tablero2.generateGrid();
        ArrayList<ArrayList<Cell>> tableGame2 = tablero2.getGrid();
        tableGame2.get(0).get(2).setSymbol(Symbol.X);
        tableGame2.get(1).get(2).setSymbol(Symbol.X);
        tableGame2.get(2).get(2).setSymbol(Symbol.X);
        System.out.println(GameValidator.horizontalValidation(tablero2));
        
        
        System.out.println("Validacion Horizontal");
        Grid tablero3 = new Grid(3, 3, 300, 300);
        tablero3.generateGrid();
        ArrayList<ArrayList<Cell>> tableGame3 = tablero3.getGrid();
        tableGame3.get(1).get(0).setSymbol(Symbol.X);
        tableGame3.get(1).get(1).setSymbol(Symbol.X);
        tableGame3.get(1).get(2).setSymbol(Symbol.X);
        System.out.println(GameValidator.verticalValidation(tablero3));
        
        System.out.println("Validacion general");
        GameValidator.gameValidation(tablero1);
    }
}
