/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tda_tree;

/**
 * <h1>Tree</h1>
 * This class is a tda that represent a tree data structure, support more than
 * 2 child.
 *
 * @param <T> the type that this tree store.
 */
public class Tree<T> {
    
    private TreeNode<T> root;
    /**
     * @param rootContent the value of the root.
     */
    public Tree(T rootContent){
        this.root = new TreeNode<>(rootContent);
    }
    
    public Tree(){
        this((T) null);
    }
    
    public void setRoot(TreeNode<T> root){
        this.root = root;
    }
    
    public TreeNode<T> getRoot(){
        return root;
    }
    
    public Tree<T> getChildrenByUtility(){
        return root.getChildren().peek();
    }
}
