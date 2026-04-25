package solutions;

import adt.Queue;
import adt.Stack;
import adt.List;
import ds.ListDummyLinked;
import ds.ListSeq;
import ds.PriorityQueueSeq;
import ds.StackSeq;

/**
 * Clase para probar la iterabilidad (for-each) de todas las estructuras
 * implementadas.
 */
public class TestEstructuras {

  /**
   * Método principal de pruebas.
   * 
   * @param args argumentos de línea de comandos
   */
  public static void main(String[] args) {
    System.out.println("--- PRUEBA PILA SECUENCIAL ---");
    Stack<String> pila = new StackSeq<>();
    pila.push("Tercero");
    pila.push("Segundo");
    pila.push("Primero"); // Cima
    for (String s : pila) {
      System.out.println(s);
    }

    System.out.println("\n--- PRUEBA COLA PRIORIDAD SECUENCIAL ---");
    Queue<String> cola = new PriorityQueueSeq<>();
    cola.enqueue("Baja Prioridad", 1);
    cola.enqueue("Alta Prioridad", 10);
    cola.enqueue("Media Prioridad", 5);
    // Iterador mostrará en orden de inserción interna en este diseño,
    // pero al hacer dequeue saldrán ordenados por prioridad.
    for (String s : cola) {
      System.out.println(s);
    }
    System.out.println("Extrayendo prioritario: " + cola.dequeue());

    System.out.println("\n--- PRUEBA LISTA SECUENCIAL ---");
    List<Integer> listaSec = new ListSeq<>();
    listaSec.insert(0, 10);
    listaSec.insert(1, 20);
    listaSec.insert(2, 30);
    for (Integer i : listaSec) {
      System.out.println(i);
    }

    System.out.println("\n--- PRUEBA LISTA DUMMY LINKED ---");
    List<Integer> listaLinked = new ListDummyLinked<>();
    listaLinked.insert(0, 100);
    listaLinked.insert(1, 200);
    for (Integer i : listaLinked) {
      System.out.println(i);
    }
  }
}