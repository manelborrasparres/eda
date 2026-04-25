package adt;

/**
 * Interfaz que define una Cola de Prioridad basándose en el máximo.
 * 
 * @param <E> tipo de elemento, debe ser comparable.
 */
public interface ColaPrioridad<E extends Comparable<E>> {
  /**
   * Inserta un elemento en la cola.
   * Complejidad Temporal: O(log n)
   * Complejidad Espacial: O(1)
   * 
   * @param x elemento a insertar.
   */
  void insertar(E x);

  /**
   * Devuelve el elemento con máxima prioridad sin eliminarlo.
   * Complejidad Temporal: O(1)
   * Complejidad Espacial: O(1)
   * 
   * @return el elemento máximo.
   */
  E maximo();

  /**
   * Elimina y devuelve el elemento con máxima prioridad.
   * Complejidad Temporal: O(log n)
   * Complejidad Espacial: O(1)
   * 
   * @return el elemento eliminado.
   */
  E eliminarMax();
}