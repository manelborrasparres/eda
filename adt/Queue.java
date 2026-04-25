package adt;

/**
 * Interfaz que define el Tipo Abstracto de Datos Cola con Prioridad.
 * Se desencolará siempre primero el elemento con mayor prioridad.
 * 
 * @param <E> el tipo de elementos de la cola
 */
public interface Queue<E> extends Iterable<E> {
  /**
   * Encola un elemento asignándole una prioridad.
   * A mayor valor numérico de prioridad, antes será desencolado.
   * Complejidad Temporal: O(1) amortizado
   * Complejidad Espacial: O(1)
   * 
   * @param element  el elemento a encolar
   * @param priority la prioridad del elemento
   */
  void enqueue(E element, int priority);

  /**
   * Elimina y devuelve el elemento más prioritario de la cola.
   * Complejidad Temporal: Depende de la implementación (O(n) en sec. no ordenada)
   * Complejidad Espacial: O(1)
   * 
   * @return el elemento más prioritario
   */
  E dequeue();

  /**
   * Devuelve el elemento más prioritario sin eliminarlo.
   * Complejidad Temporal: Depende de la implementación (O(n) en sec. no ordenada)
   * Complejidad Espacial: O(1)
   * 
   * @return el elemento más prioritario
   */
  E first();

  /**
   * Comprueba si la cola está vacía.
   * Complejidad Temporal: O(1)
   * 
   * @return true si está vacía, false en caso contrario
   */
  boolean isEmpty();

  /**
   * Devuelve el número de elementos en la cola.
   * Complejidad Temporal: O(1)
   * 
   * @return tamaño de la cola
   */
  int size();
}