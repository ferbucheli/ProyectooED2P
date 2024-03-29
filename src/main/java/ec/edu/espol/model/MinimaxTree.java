package ec.edu.espol.model;

import game.Symbol;
import java.util.ArrayList;
import model.players.Player;
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
    
    public void paintMaxUtil(){
        Grid result = new Grid();
        result.setUtility(-1000000);
        for(MinimaxTree t : this.root.getChildren()){
            if(t.getRoot().getContent().compareTo(result) > 0){
                result = t.getRoot().getContent();
            }
        }
        result.isPainted = true;
    }
    
    public void generateTree(Player p){
        ArrayList<Grid> grids = this.root.getContent().generateMoves(p.getPlayerSymbol());
        insertChilds(grids);
        for(MinimaxTree t : this.root.getChildren()){
            ArrayList<Grid> grids2 = t.root.getContent().generateMoves(p.getOpponentSymbol());
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
                if(this.root.getContent().getWonBy().equals(player.getOpponentSymbol()))
                    return Grid.LOSS;
            }
            this.root.getContent().generateUtility(player.getPlayerSymbol(), player.getOpponentSymbol()); // player.getOpponentSymbol()
            return this.root.getContent().getUtility();
            
            /* NO BORRAR
            this.root.getContent().generateUtility(player.getPlayerSymbol(), player.getOpponentSymbol());
            return this.root.getContent().getUtility();
            */
            
        }
        if(maxPlayer){
            int maxEval = -10000;
            for(MinimaxTree t : this.root.getChildren()){
                int eval = t.minimax(false, player);
                GameValidator.gameValidation(this.root.getContent());
//                if(t.root.getContent().getWonBy().equals(player.getPlayerSymbol()))
//                    t.root.getContent().setUtility(Grid.WON);
//                else
//                    t.root.getContent().setUtility(eval);
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
                GameValidator.gameValidation(this.root.getContent());
                if(minEval > eval || t.getRoot().getContent().getWonBy().equals(player.getPlayerSymbol())){
                    minEval = eval;
                }
            }
            return minEval;
        }
    }
    
    
    public int minimaxEasy(boolean maxPlayer, Player player){
        if(this.isLeaf()){
            this.root.getContent().generateUtility(player.getPlayerSymbol(), player.getOpponentSymbol()); // player.getOpponentSymbol()
            return this.root.getContent().getUtility();
        }
        if(!maxPlayer){
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
