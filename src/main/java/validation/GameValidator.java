package validation;

import ec.edu.espol.model.Cell;
import ec.edu.espol.model.Grid;
import game.Symbol;
import java.util.ArrayList;

/**
 *
 * @author eduar
 */
public class GameValidator {
    
    private static Symbol winner;  /*Simbolo del ganador*/
                                  /*Va a ser nulo en caso de que nadie haya ganado*/

    
    /** Validacion diagonal
     * 
     * @param tablero tablero del juego 3 en raya
     *              
     * @return Symbol símbolo del ganador
     *                null en caso de que nadie haya ganado
     */

    public static Symbol diagonalValidation(Grid tablero) {
        ArrayList<ArrayList<Cell>> tableGame = tablero.getGrid();

        ArrayList<Cell> firstRow = tableGame.get(0);
        ArrayList<Cell> secondRow = tableGame.get(1);
        ArrayList<Cell> thirdRow = tableGame.get(2);

        /*Verficacion diagonal hacia la derecha-abajo*/
        /*Verifica si los simbolos en su diagonal son iguales pero diferente de null*/
        if ((secondRow.get(1).getSymbol() != null)
                && secondRow.get(1).getSymbol() == thirdRow.get(2).getSymbol()
                && firstRow.get(0).getSymbol() == secondRow.get(1).getSymbol()) {

            return secondRow.get(1).getSymbol();
        }

        /*Verficacion diagonal hacia la derecha-arriba*/
        if ((secondRow.get(1).getSymbol() != null)
                && secondRow.get(1).getSymbol() == thirdRow.get(0).getSymbol()
                && firstRow.get(2).getSymbol() == secondRow.get(1).getSymbol()) {

            return secondRow.get(1).getSymbol();
        }

        return null;
    }
    
    
    /**
     * Validación horizontal
     * 
     * @param tablero, tablero del juego tres en raya
     * @return Symbol, símbolo del ganador del juego
     *                 null en caso de que no haya ganado nadie
     */
    public static Symbol horizontalValidation(Grid tablero) {
        ArrayList<ArrayList<Cell>> tableGame = tablero.getGrid();

        ArrayList<Cell> firstRow = tableGame.get(0);
        ArrayList<Cell> secondRow = tableGame.get(1);
        ArrayList<Cell> thirdRow = tableGame.get(2);

        for (int rowIndex = 0; rowIndex < 3; rowIndex++) {

            if ((secondRow.get(rowIndex).getSymbol() != null)
                    && secondRow.get(rowIndex).getSymbol() == thirdRow.get(rowIndex).getSymbol()
                    && firstRow.get(rowIndex).getSymbol() == secondRow.get(rowIndex).getSymbol()) {
                
                return secondRow.get(rowIndex).getSymbol();
            }
        }
        return null;
    }
    
    /**
     * Validacion vertical
     * @param tablero, tablero del tres en raya
     * @return Symbol, retorna el symbolo de quién ganó
     *                 null en caso de que no haya ganado nadie
     */
    public static Symbol verticalValidation(Grid tablero) {
        ArrayList<ArrayList<Cell>> tableGame = tablero.getGrid();
        for(ArrayList<Cell> currentRow : tableGame){
            if(currentRow.get(0).getSymbol() != null 
                    && currentRow.get(0).getSymbol() == currentRow.get(1).getSymbol()
                    && currentRow.get(1).getSymbol() == currentRow.get(2).getSymbol()){
            
                return currentRow.get(0).getSymbol();
            }
        }
        return null;
    }
    /*metodo gameValidation(), 
    /*                          retorna 0 en caso de que nadie haya ganado
    /*                          retorna 1 en caso de que alguien haya ganado
    /*                          retorna 2 en caso de empate
    */
    public static int gameValidation(Grid tablero){
        
        /*Primero siempre va a hacer las validaciones de si alguien ha ganado*/
        if(diagonalValidation(tablero) != null){
            System.out.print("Ha ganado diagonal: ");
            System.out.println(diagonalValidation(tablero));
            winner = diagonalValidation(tablero);
            return 1;
        }
        
        if(horizontalValidation(tablero) != null){
            System.out.print("Ha ganado horizontal: ");
            System.out.println(horizontalValidation(tablero));
            winner = horizontalValidation(tablero);
            return 1;
        }
        
        if(verticalValidation(tablero) != null){
            System.out.print("Ha ganado vertical: ");
            System.out.println(verticalValidation(tablero));
            winner = verticalValidation(tablero);
            return 1;
        }   
        
        /*Una vez que no haya sacado un ganador se comprueba el caso de empate*/
        if(isTie(tablero)){
            return 2;
        }
        
        return 0;
    }

    /*isTie(),
            verifica que todas las casillas sean distinto de null y SE DEBE USAR
            despues de hacer las validaciones horizontales, verticales, diagonales
    */
    
    private static boolean isTie(Grid tablero) {
        ArrayList<ArrayList<Cell>> tableGame = tablero.getGrid();
        
        for(ArrayList<Cell> currentRow : tableGame){
            
            for(Cell c: currentRow){
                /*Si encuentra un null es porque todavia hay espacio*/
                if(c.getSymbol() == null){
                    return false;
                }
            }
        }
        return true;
    }
    
    
    public static Symbol getWinner(){
        return winner;
    }
    
}
