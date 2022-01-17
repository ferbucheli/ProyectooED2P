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
public class MinimaxTreeNode<T> {
    private T content;
    private ArrayList<MinimaxTree> children;
    
    public MinimaxTreeNode(T value){
        this.content = value;
        this.children = new ArrayList<>();
    }
    
    public MinimaxTreeNode(){
        
    }
    
    public ArrayList<MinimaxTree> getChildren(){
        return children;
    }

    public T getContent() {
        return content;
    }
    
    public void setChildren(ArrayList<MinimaxTree> children){
        this.children = children;
    }
    
    public void setContent(T content){
        this.content = content;
    }
}
