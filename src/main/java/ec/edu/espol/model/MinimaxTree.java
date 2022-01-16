package ec.edu.espol.model;

import game.Symbol;
import java.util.ArrayList;
import model.players.Player;
import model.tree.Tree;
import validation.GameValidator;

/**
 *
 * @author Fernando
 */
public class MinimaxTree {
    
    private MinimaxTreeNode<Grid> root;

    public MinimaxTree(Grid content){
        this.root = new MinimaxTreeNode<Grid>(content);
    }
    
    
    public void setRoot(MinimaxTreeNode<Grid> root){
        this.root = root;
    }
    
    public boolean isLeaf(){
        return this.root.getChildren().isEmpty();
    }
    
    public MinimaxTreeNode<Grid> getRoot(){
        return root;
    }
    
    public void insert(Grid element){
        this.root.getChildren().add(new MinimaxTree(element));
    }
    
    public void insertChilds(ArrayList<Grid> grids){
        for(Grid g : grids){
            this.insert(g);
        }
    }
    
    public void generateTree(Player p){
        ArrayList<Grid> grids = this.root.getContent().generateMoves(p.getPlayerSymbol());
        insertChilds(grids);
        for(MinimaxTree t : this.root.getChildren()){
            ArrayList<Grid> grids2 = t.root.getContent().generateMoves(p.getOppoenentSymbol());
            t.insertChilds(grids2);
        }
    }
    
    public Grid minimax(){
        Grid result = new Grid();
        result.setUtility(-1000000);
        
        for(MinimaxTree m : this.root.getChildren()){         
            if(m.getRoot().getContent().compareTo(result) > 0)
                result = m.getRoot().getContent();
        }
        return result;
    }
    

    
    public int minimax(boolean maxPlayer, Player player){
        if(this.isLeaf()){
            
            if(GameValidator.gameValidation(this.root.getContent()) == 1){
                if(this.root.getContent().getWonBy().equals(player.getOppoenentSymbol()))
                    return -1000;
            }
            this.root.getContent().generateUtility(player.getPlayerSymbol(), player.getOppoenentSymbol()); // player.getOpponentSymbol()
            return this.root.getContent().getUtility();
            
            /* NO BORRAR
            this.root.getContent().generateUtility(player.getPlayerSymbol(), player.getOppoenentSymbol());
            return this.root.getContent().getUtility();
            */
            
        }
        if(maxPlayer){
            int maxEval = -10000;
            for(MinimaxTree t : this.root.getChildren()){
                int eval = t.minimax(false, player);
                t.root.getContent().setUtility(eval);
                if(maxEval < eval){
                    maxEval = eval;
                }
            }
            return maxEval;
        } else {
            int minEval = 10000;
            for(MinimaxTree t : this.root.getChildren()){
                int eval = t.minimax(true, player);
                t.root.getContent().setUtility(eval);
                if(minEval > eval){
                    minEval = eval;
                }
            }
            return minEval;
        }
    }   
}
