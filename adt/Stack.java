package adt;

/**
 * Interfaz que define el Tipo Abstracto de Datos Pila (LIFO).
 * 
 * @param <E> el tipo de elementos de la pila
 */
public interface Stack<E> extends Iterable<E> {
  /**
   * Inserta un elemento en la cima de la pila.
   * Complejidad Temporal: O(1) amortizado
   * Complejidad Espacial: O(1)
   * 
   * @param element el elemento a apilar
   */
  void push(E element);

  /**
   * Elimina y devuelve el elemento en la cima de la pila.
   * Complejidad Temporal: O(1)
   * Complejidad Espacial: O(1)
   * 
   * @return el elemento desapilado
   */
  E pop();

  /**
   * Devuelve el elemento en la cima de la pila sin eliminarlo.
   * Complejidad Temporal: O(1)
   * Complejidad Espacial: O(1)
   * 
   * @return el elemento en la cima
   */
  E top();

  /**
   * Comprueba si la pila está vacía.
   * Complejidad Temporal: O(1)
   * 
   * @return true si está vacía, false en caso contrario
   */
  boolean isEmpty();

  /**
   * Devuelve el número de elementos en la pila.
   * Complejidad Temporal: O(1)
   * 
   * @return tamaño de la pila
   */
  int size();
}