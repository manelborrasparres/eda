package solutions;

import ds.AVLTreeIter;
import ds.AVLTreeRec;

/**
 * Clase ejecutable para comparar experimentalmente el coste entre
 * inserciones recursivas e iterativas en Árboles AVL.
 */
public class TestArboles {
  public static void main(String[] args) {
    AVLTreeRec<Integer> avlRec = new AVLTreeRec<>();
    AVLTreeIter<Integer> avlIter = new AVLTreeIter<>();
    int limit = 100000;

    long startRec = System.nanoTime();
    for (int i = 0; i < limit; i++) {
      avlRec.insertar(i);
    }
    long timeRec = System.nanoTime() - startRec;

    long startIter = System.nanoTime();
    for (int i = 0; i < limit; i++) {
      avlIter.insertar(i);
    }
    long timeIter = System.nanoTime() - startIter;

    System.out.println("--- COMPARATIVA AVL ---");
    System.out.println("Nodos insertados: " + limit);
    System.out.println("Tiempo AVL Recursivo (ns): " + timeRec);
    System.out.println("Tiempo AVL Iterativo (ns): " + timeIter);
    System.out.println("Diferencia teórica: O(log n) ambas, pero la iterativa ahorra overhead de pila de llamadas.");
  }
}