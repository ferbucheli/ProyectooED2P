package tda_heap;

import java.util.Comparator;

/**
 *
 * @author eduardosg
 */
public class Heap<E> { //Parametrizacion por tipo
    private Comparator<E> cmp;
    private E[] array;
    private int MAX_SIZE = 100;
    private int EFFECTIVE_SIZE;
    private boolean IS_MAX;
    
    
    public Heap(int maxSize, boolean ismax, Comparator<E> cmp){
        this.MAX_SIZE = maxSize;
        this.IS_MAX = ismax;
        this.array = (E[]) new Object[MAX_SIZE];
        this.EFFECTIVE_SIZE = 0;
        this.cmp = cmp;
    }
    
    public boolean insert(E newElement){
        if(newElement == null){
            return false;
        }
        
        if(this.isEmpty()){
            array[0] = newElement;
            EFFECTIVE_SIZE++;
            return true;
        }
        
        array[EFFECTIVE_SIZE++] = newElement;
        return true;
               
    }
    
    public int size(){
        return this.EFFECTIVE_SIZE;
    }
    
    public void addAll(E[] elements){
        for(E e: elements){
            array[EFFECTIVE_SIZE++] = e;
        }
    }
    
    
    public boolean isEmpty(){
       return EFFECTIVE_SIZE == 0;
    }
    
    public E getRoot(){
        return array[0];
    }
    
    public void adjustMax(int parent){
        int indexGreatest, rightChild, leftChild;
        
        leftChild = parent*2 + 1;
        rightChild = parent*2 + 2;
        
        //se sabe que solo puede existir primero el hijo izquierdo
        if(leftChild < this.EFFECTIVE_SIZE && cmp.compare(array[leftChild], array[parent]) > 0){
            indexGreatest = leftChild;
            
            //se busca ahora en el lado derecho
            //(si existe el lado derecho es obvio que ya hubo un lado izquierdo) Def. HEAP
                                                                    //se lo compara con el nuevo 
            if (rightChild < this.EFFECTIVE_SIZE && cmp.compare(array[rightChild], array[indexGreatest]) > 0){
                indexGreatest = rightChild;
            }
            
            swap(indexGreatest, parent);
            adjustMax(indexGreatest); //se acomoda hacia abajo (PARTE RECURSIVA) 
            
        } else if (rightChild < this.EFFECTIVE_SIZE && cmp.compare(array[rightChild], array[parent]) > 0) {
            indexGreatest = rightChild;
            swap(indexGreatest, parent);
            adjustMax(indexGreatest); //se acomoda hacia abajo (PARTE RECURSIVA) 
        }

    }
    
    public void adjustMin(int parent){
        int indexLeast, rightChild, leftChild;
        
        leftChild = parent*2 + 1;
        rightChild = parent*2 + 2;
        
        //se sabe que solo puede existir primero el hijo izquierdo
        if(leftChild < this.EFFECTIVE_SIZE && cmp.compare(array[leftChild], array[parent]) < 0){
            indexLeast = leftChild;
            
            //se busca ahora en el lado derecho
            //(si existe el lado derecho es obvio que ya hubo un lado izquierdo) Def. HEAP
                                                                    //se lo compara con el nuevo 
            if (rightChild < this.EFFECTIVE_SIZE && cmp.compare(array[rightChild], array[indexLeast]) < 0){
                indexLeast = rightChild;
            }
            
            swap(indexLeast, parent);
            adjustMin(indexLeast); //se acomoda hacia abajo (PARTE RECURSIVA) 
            
        } else if (rightChild < this.EFFECTIVE_SIZE && cmp.compare(array[rightChild], array[parent]) < 0) {
            indexLeast = rightChild;
            swap(indexLeast, parent);
            adjustMin(indexLeast); //se acomoda hacia abajo (PARTE RECURSIVA) 
        }

    }
    
    
    //metodo que cambia el elemento en la posicion 1 por el elemento en la posicion2
    //y viceversa
    private boolean swap(int index1, int index2){
        if(index1 < this.EFFECTIVE_SIZE && index2 < this.EFFECTIVE_SIZE){
            E tmp = array[index1];
            array[index1] = array[index2];
            array[index2] = tmp;
            return true;
        }
         
        return false;
        
    }
    

    public Comparator<E> getComparator() {
        return cmp;
    }
    
    public void setComparator(Comparator<E> cmp) {
        this.cmp = cmp;
    }
    
    public void show(){
        System.out.print("[");
        for(int i = 0; i < this.EFFECTIVE_SIZE ; i++) {
            System.out.print(array[i] + ", ");
        }
        System.out.println("]");
    }
    
    public void buildHeap(){
        int lastRoot = EFFECTIVE_SIZE/2 - 1;  //es el ultimo nodo raíz (Ver definición)
        for(int i = lastRoot ; i>=0 ; i--){
            if(IS_MAX){
                adjustMax(i);
            } else {
                adjustMin(i);
            }
            
            
        }
    }
    
    public E poll(){
        E result = array[0];
        array[0] = array[--EFFECTIVE_SIZE];
        //se debe hacer que se ajuste la raiz
        if(IS_MAX){
            adjustMax(0);
        } else {
            adjustMin(0);
        }
                                  
        return result;
    }
    
    public boolean offer(E element){
        if(element != null){
            array[EFFECTIVE_SIZE++] = element;
            
            if(IS_MAX){
                adjustParentMax(EFFECTIVE_SIZE-1);
            } else {
                adjustParentMin(EFFECTIVE_SIZE-1);
            }
            return true;
        }
        return false;
    }
    
    //metodo que ajusta el heap hacia arriba
    public void adjustParentMax(int indexChild){
        int indexParent;
        
        indexParent = (indexChild - 1)/2;
        if(cmp.compare(array[indexChild], array[indexParent]) > 0){
            swap(indexChild, indexParent);
            adjustParentMax(indexParent);
        }
    }
    
    //metodo que ajusta el heap hacia arriba
    public void adjustParentMin(int indexChild){
        int indexParent;
        
        indexParent = (indexChild - 1)/2;
        if(cmp.compare(array[indexChild], array[indexParent]) < 0){
            swap(indexChild, indexParent);
            adjustParentMin(indexParent);
        }
    }
    
}
