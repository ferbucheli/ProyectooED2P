/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tda_tree;

import java.util.Comparator;

/**
 * A comparator for tree to compare it's types
 * @author Lenovo
 * @param <T>
 */
public interface TreeComparator<T> extends Comparator<Tree<T>> {
    int compareContent(T c1, T c2);
    
    @Override
    default int compare(Tree<T> t1, Tree<T> t2){
        return compareContent(t1.getRoot().getContent(), t2.getRoot().getContent());
    }
    
}
