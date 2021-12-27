
package custom.controls;

import custom.data.ObservableData;
import game.Symbol;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import model.players.Player;

/**
 * This is the button used into the grid of the tik tak toe.
 */
public class GridButton extends Button {
    
    private ObservableData<Symbol> currentSymbol;
    //The associated player to the button. This can be optional.
    private Player asociatedPlayer;
    
    public GridButton(){
        currentSymbol = new ObservableData<>(null);
        configureSymbolListener();
        getStyleClass().add("grid-button");
    }
    
    private void configureSymbolListener() {
        currentSymbol.subscribe((oldSymbol, newSymbol)->{
            setText(newSymbol.name());
        });
    }
    
    public Symbol currentSymbol() {
        return currentSymbol.get();
    }
    
    /**
     * @return
     */
    public boolean isBlank() {
        return currentSymbol() == null;
    }
    
    /**
     * @param symbol the symbol to write into the button
     * @return true if the symbol can be written, only if is blank.
     */
    public boolean setSymbol(Symbol symbol){
        if(symbol == null || !isBlank())
            return false;
        this.getStyleClass().add("grid-button-filled");
        currentSymbol.set(symbol);
        return true;
    }

    public void setAsociatedPlayer (Player associatedPlayer) {
        this.asociatedPlayer = asociatedPlayer;
    }
 
    public Player getAsociatedPlayer () {
        return asociatedPlayer;
    }
    
    public void fillAllGrid(){
        GridPane.setFillHeight(this,true);
        GridPane.setFillWidth(this,true);
        this.setMaxSize(Double.MAX_VALUE,Double.MAX_VALUE);
    }
    
    
     // @return a superficial copy of the Button.
     
    public GridButton copy(){
        GridButton copyButton = new GridButton();
        copyButton.setAsociatedPlayer(this.asociatedPlayer);
        copyButton.setSymbol(this.currentSymbol());
        
        return copyButton;
    }
    
    @Override
    public String toString () {
        StringBuilder str = new StringBuilder("GridButton {");
        str.append("associated player: ")
                .append(this.asociatedPlayer)
                .append("currentSymbol: ")
                .append(this.currentSymbol());
        return str.toString();
    }
    
    @Override
    public boolean equals (Object obj) {
        if (obj == this)
            return true;
        if (obj == null)
            return false;
        if (obj instanceof GridButton){
            GridButton temp = (GridButton) obj;
            boolean hasAssociated = asociatedPlayer != null && temp.asociatedPlayer != null;
            boolean equalsAssoc = !hasAssociated || this.asociatedPlayer.equals(temp.asociatedPlayer);
            return temp.currentSymbol() == this.currentSymbol() && equalsAssoc;
        }
        return false;
    }
}


