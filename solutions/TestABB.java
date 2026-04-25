package solutions;

import ds.BST;

/**
 * Clase ejecutable para probar el correcto funcionamiento del BST.
 */
public class TestABB {

  public static void main(String[] args) {
    BST<Integer> tree = new BST<>();

    System.out.println("--- INSERCIÓN ---");
    // Replicando el ejemplo de la documentación: 8, 3, 10, 1, 6, 14 [cite: 156-163]
    tree.add(8);
    tree.add(3);
    tree.add(10);
    tree.add(1);
    tree.add(6);
    tree.add(14);

    System.out.println("Tamaño del árbol: " + tree.size());

    System.out.println("\n--- BÚSQUEDA ---");
    System.out.println("Buscando 6: " + (tree.search(6) != null ? "Encontrado" : "No encontrado"));
    System.out.println("Buscando 99: " + (tree.search(99) != null ? "Encontrado" : "No encontrado"));

    System.out.println("\n--- MÍNIMO Y MÁXIMO ---");
    System.out.println("Valor mínimo: " + tree.min().key);
    System.out.println("Valor máximo: " + tree.max().key);

    System.out.println("\n--- ITERADOR (Recorrido Inorden) ---");
    System.out.print("Elementos ordenados: ");
    for (Integer val : tree) {
      System.out.print(val + " ");
    }
    System.out.println();

    System.out.println("\n--- BORRADO ---");
    System.out.println("Borrando nodo hoja (1)...");
    tree.delete(1);
    System.out.println("Borrando nodo con un hijo (10)...");
    tree.delete(10); // En este momento el 10 solo tiene como hijo al 14
    System.out.println("Borrando raíz con dos hijos (8)...");
    tree.delete(8);

    System.out.print("Elementos ordenados tras borrados: ");
    for (Integer val : tree) {
      System.out.print(val + " ");
    }
    System.out.println("\nTamaño del árbol final: " + tree.size());
  }
}