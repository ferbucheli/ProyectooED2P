package game;

import custom.controls.GridButton;
import model.players.Player;

/**
 *
 * @author Lenovo
 */

public class GameValidations {
    
      private boolean validateVertical(Player player, int col){
        for (int i = 0; i < 3; i++) {
            if (!hasEqualSymbol(getCell(i,col),player))
                return false;
        }
        
        return true;
    }
      
      private boolean validateHorizontal(Player player, int fila){
        for (int i = 0; i < 3; i++) {
            if (getCell(fila, i).currentSymbol() != player.getPlayerSymbol())
                return false;
        }
        return true;
    }
      
      public boolean validateAll (Player player) {
        boolean hasHorizontal = false;
        boolean hasVertical = false;
        
        for (int i = 0; i < 3; i++) {
            hasHorizontal = validateHorizontal(player, i);
            if (hasHorizontal) break;
        }
        
        if(!hasHorizontal)
            for (int i = 0; i < 3; i++) {
                hasVertical = validateVertical(player, i);
                if (hasVertical) break;
            }
        
        return hasHorizontal || hasVertical; 
        
    }
    
      private GridButton[][] ma;
      
      public GridButton getCell (int fila, int col) {
            return ma[fila][col];
        }
      
        private boolean hasEqualSymbol(GridButton button, Player player){
            return button.currentSymbol() == player.getPlayerSymbol();
    }
        
}
