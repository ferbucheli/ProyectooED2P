package tests.heap;

import model.heap.Heap;
import java.util.Comparator;

/**
 *
 * @author eduar
 */
public class MainTests {
    public static void main(String[] args) {
        Comparator<Integer> cmp  = (Integer o1, Integer o2) -> o1-o2;
        System.out.println("********************AJUSTAR********************");
        System.out.println("TEST_1: ADJUST_MAX");
        Heap<Integer> heap1 = new Heap<>(100, false, cmp);
      
        heap1.insert(10);
        heap1.insert(11);
        heap1.insert(12);
        heap1.show();
        heap1.adjustMax(0);
        heap1.show();
        
        System.out.println("TEST_2: ADJUST_MIN");
        Heap<Integer> heap2 = new Heap<>(100, false, cmp);
        heap2.setComparator((Integer o1, Integer o2) -> o1 - o2);
        heap2.insert(10);
        heap2.insert(11);
        heap2.insert(12);
        System.out.print("Input:");
        heap2.show();
        heap2.adjustMin(0);
        System.out.print("Output:");
        heap2.show();
        
        
        System.out.println("********************BUILD HEAP********************");
        System.out.println("TEST_1: BUILD_MAX_HEAP");
        Heap<Integer> heap3 = new Heap<>(100, true, cmp);
        heap3.setComparator((Integer o1, Integer o2) -> o1 - o2);
        Integer[] elements3 = {15, 1, 28, 35, 10, 5, 8, 21, 50, 42, 32, 45, 54, 22, 144, 56, 201, 221};
        heap3.addAll(elements3);
        System.out.print("Input:");
        heap3.show();
        heap3.buildHeap();
        System.out.print("Output:");
        heap3.show();
        
        System.out.println("TEST_1: BUILD_MIN_HEAP");
        Heap<Integer> heap4 = new Heap<>(100, false, cmp);
        heap4.setComparator((Integer o1, Integer o2) -> o1 - o2);
        Integer[] elements4 = {15, 1, 28, 35, 10, 5, 8, 21};
        heap4.addAll(elements4);
        System.out.print("Input:");
        heap4.show();
        heap4.buildHeap();
        System.out.print("Output:");
        heap4.show();
        
        System.out.println("********************POLL/DESENCOLAR********************");
        System.out.println("\tTEST_1: POLL IN MIN HEAP");
        Heap<Integer> heap5 = new Heap<>(100, false, cmp);
        heap5.setComparator((Integer o1, Integer o2) -> o1 - o2);
        Integer[] elements5 = {15, 1, 28, 35, 10, 5, 8, 21, 50, 42, 32, 45, 54, 22, 144, 56, 201, 221};
        heap5.addAll(elements5);
        System.out.print("Input:");
        heap5.buildHeap();
        heap5.show();
        System.out.println("ELEMENT ON POLL:" + heap5.poll());
        System.out.print("Output:");
        heap5.show();
        
        
        System.out.println("\tTEST_2: POLL IN MAX HEAP");
        Heap<Integer> heap6 = new Heap<>(100, true, cmp);
        Integer[] elements6 = {15, 1, 28, 35, 10};
        heap6.addAll(elements6);
        System.out.print("Input:");
        heap6.buildHeap();
        heap6.show();
        System.out.println("ELEMENT ON POLL:" + heap6.poll());
        System.out.print("Output:");
        heap6.show();
        
        System.out.println("\tTEST_3: WHOLE MIN POLL");
        Heap<Integer> heap7 = new Heap<>(100, false, cmp);
        heap7.setComparator((Integer o1, Integer o2) -> o1 - o2);
        heap7.addAll(elements6);
        System.out.print("Input:");
        heap7.buildHeap();
        heap7.show();
        System.out.println("Output:");
        while(!heap7.isEmpty()){
            System.out.println(heap7.poll());
        }
        
        System.out.println("************************OFFER/ENCOLAR************************");
        System.out.println("TEST_1: OFFER_IN_MAX_HEAP");
        Heap<Integer> heap9 = new Heap<>(100, true, cmp);
        heap9.addAll(elements6);
        heap9.buildHeap(); 
        heap9.show();
        heap9.offer(150);
        heap9.show();
        
        
        System.out.println("TEST_2: OFFER_IN_MIN_HEAP");
        Heap<Integer> heap10 = new Heap<>(100, false, cmp);
        heap10.addAll(elements6);
        heap10.buildHeap();
        heap10.show();
        heap10.offer(9);
        heap10.show();
        
        System.out.println("TEST_3: WHOLE OFFER");
        System.out.print("input:");
        Heap<Integer> heap11 = new Heap<>(100, false, cmp);
        Integer[] elements11 = {15, 1, 28, 35, 10, 5, 8, 21, 50};
        heap11.addAll(elements11);
        heap11.show();
        heap11.buildHeap();
        System.out.print("with buildheap:  ");
        heap11.show();
        System.out.print("with only offer: ");
        Heap<Integer> heap12 = new Heap<>(100, false, cmp);
        heap12.offer(15);
        heap12.offer(1);
        heap12.offer(28);
        heap12.offer(35);     
        heap12.offer(10);
        heap12.offer(5);
        heap12.offer(8);
        heap12.offer(21);
        heap12.offer(50);
        heap12.show();

    }
 
}
