
package model.table;
import custom.controls.GridButton;
import game.Symbol;
import javafx.scene.layout.GridPane;


public class Table implements Comparable<Table> {
  
    private int utility;
    private GridPane gameGrid;

    
    
    public Table(GridPane gameGrid){
        this.gameGrid = gameGrid;
        this.utility = 0;
    }

    public Table(){
        this(null);
    }

    private Symbol getSymbolGridButton(int i){
        GridButton gb = (GridButton) gameGrid.getChildren().get(i);
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
    public int compareTo (Table o) {
        return this.getUtility() - o.getUtility();
    }

    public int getUtility() {
        return utility;
    }

    public GridPane getTable() {
        return gameGrid;
    }

    public void setUtility(int utility) {
        this.utility = utility;
    }

    public void setGameGrid(GridPane gameGrid) {
        this.gameGrid = gameGrid;
    }

    /**
     * @param computerSymbol The symbol the computer uses
     * @param userSymbol The symbol the user uses
     */
    public void generateUtility(Symbol computerSymbol, Symbol userSymbol){
        int computer = utilityByRows(userSymbol) + utilityByColumns(userSymbol) + utilityByDiagonals(userSymbol);
        int human = utilityByRows(computerSymbol) + utilityByColumns(computerSymbol) + utilityByDiagonals(computerSymbol);
        setUtility(computer-human);
    }

    /**
     * @param position Index i in the GridPane
     * @param value The value to be inserted
     */
    public void insertValue(int position, Symbol value){
        GridButton gb = new GridButton();
        gb.setSymbol(value);
        gameGrid.getChildren().set(position, gb);
    }

    public void printTable(){
        for (int i = 0; i < 9; i++) {
            GridButton gb = (GridButton) gameGrid.getChildren().get(i);
            System.out.println(gb.currentSymbol());
        }
    }
}
