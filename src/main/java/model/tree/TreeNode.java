/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.tree;

import java.util.PriorityQueue;

/**
 *
 * @author Lenovo
 * @param <T>
 */
public class TreeNode<T> {
    private T content;
    private PriorityQueue<Tree<T>> children;
    
    public TreeNode(T value){
        this.content = value;
    }
    
    public TreeNode(){
        
    }
    
    public PriorityQueue<Tree<T>> getChildren(){
        return children;
    }

    public T getContent() {
        return content;
    }
    
    public void setChildren(PriorityQueue<Tree<T>> children){
        this.children = children;
    }
    
    public void setContent(T content){
        this.content = content;
    }
}
