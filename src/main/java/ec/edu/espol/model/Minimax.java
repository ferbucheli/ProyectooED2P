/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espol.model;

import game.Symbol;
import java.util.ArrayList;
import model.players.Player;
import model.tree.Tree;

/**
 *
 * @author Fernando
 */
public class Minimax {
    
    private MinimaxNode<Grid> root;

    public Minimax(Grid content){
        this.root = new MinimaxNode<Grid>(content);
    }
    
    
    public void setRoot(MinimaxNode<Grid> root){
        this.root = root;
    }
    
    public boolean isLeaf(){
        return this.root.getChildren().isEmpty();
    }
    
    public MinimaxNode<Grid> getRoot(){
        return root;
    }
    
    public void insert(Grid element){
        this.root.getChildren().add(new Minimax(element));
    }
    
    public void insertChilds(ArrayList<Grid> grids){
        for(Grid g : grids){
            this.insert(g);
        }
    }
    
    public void generateTree(Player p){
        ArrayList<Grid> grids = this.root.getContent().generateMoves(p.getPlayerSymbol());
        insertChilds(grids);
        for(Minimax t : this.root.getChildren()){
            ArrayList<Grid> grids2 = t.root.getContent().generateMoves(p.getOppoenentSymbol());
            t.insertChilds(grids2);
        }
    }
    

    
    public Grid minimax(boolean maxPlayer, Player player){
        if(this.isLeaf()){
            this.root.getContent().generateUtility(player.getPlayerSymbol(), player.getOppoenentSymbol());
            return this.root.getContent();
        }
        if(maxPlayer){
            Grid maxEval = new Grid();
            maxEval.setUtility(Integer.MIN_VALUE);
            for(Minimax t : this.root.getChildren()){
                Grid eval = t.minimax(false, player);
                if(maxEval.compareTo(eval) < 0)
                    maxEval = eval;
            }
            return maxEval;
        } else {
            Grid minEval = new Grid();
            minEval.setUtility(Integer.MAX_VALUE);
            for(Minimax t : this.root.getChildren()){
                Grid eval = t.minimax(true, player);
                if(minEval.compareTo(eval) > 0)
                    minEval = eval;
            }
            return minEval;
        }
    }   
}
