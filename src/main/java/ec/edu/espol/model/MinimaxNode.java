/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espol.model;

import java.util.ArrayList;
import model.tree.Tree;

/**
 *
 * @author Fernando
 */
public class MinimaxNode<T> {
    private T content;
    private ArrayList<Minimax> children;
    
    public MinimaxNode(T value){
        this.content = value;
        this.children = new ArrayList<>();
    }
    
    public MinimaxNode(){
        
    }
    
    public ArrayList<Minimax> getChildren(){
        return children;
    }

    public T getContent() {
        return content;
    }
    
    public void setChildren(ArrayList<Minimax> children){
        this.children = children;
    }
    
    public void setContent(T content){
        this.content = content;
    }
}
