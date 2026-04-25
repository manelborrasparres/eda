package ds;

import adt.Queue;
import exceptions.EmptyCollectionException;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Implementación de Cola con Prioridad mediante representación secuencial.
 * 
 * @param <E> el tipo de elementos
 */
public class PriorityQueueSeq<E> implements Queue<E> {

  /**
   * Clase interna para almacenar el elemento y su prioridad.
   */
  private static class PQNode<E> {
    E element;
    int priority;

    PQNode(E element, int priority) {
      this.element = element;
      this.priority = priority;
    }
  }

  private PQNode<E>[] data;
  private int size;

  /**
   * Constructor por defecto.
   */
  @SuppressWarnings("unchecked")
  public PriorityQueueSeq() {
    data = (PQNode<E>[]) new PQNode[10];
    size = 0;
  }

  /**
   * Expande la capacidad del array interno.
   * Complejidad Temporal: O(n)
   * Complejidad Espacial: O(n)
   */
  @SuppressWarnings("unchecked")
  private void expandCapacity() {
    PQNode<E>[] newData = (PQNode<E>[]) new PQNode[data.length * 2];
    System.arraycopy(data, 0, newData, 0, size);
    data = newData;
  }

  /**
   * Busca el índice del elemento con mayor prioridad.
   * Complejidad Temporal: O(n)
   * 
   * @return índice del elemento más prioritario
   */
  private int findHighestPriorityIndex() {
    int highestIndex = 0;
    for (int i = 1; i < size; i++) {
      if (data[i].priority > data[highestIndex].priority) {
        highestIndex = i;
      }
    }
    return highestIndex;
  }

  /** {@inheritDoc} */
  @Override
  public void enqueue(E element, int priority) {
    if (size == data.length) {
      expandCapacity();
    }
    data[size++] = new PQNode<>(element, priority);
  }

  /**
   * {@inheritDoc}
   * Tiempo: O(n) por la búsqueda y el desplazamiento de elementos.
   */
  @Override
  public E dequeue() {
    if (isEmpty())
      throw new EmptyCollectionException();
    int maxIndex = findHighestPriorityIndex();
    E element = data[maxIndex].element;

    // Desplazar elementos a la izquierda para cubrir el hueco
    for (int i = maxIndex; i < size - 1; i++) {
      data[i] = data[i + 1];
    }
    data[size - 1] = null;
    size--;
    return element;
  }

  /**
   * {@inheritDoc}
   * Tiempo: O(n)
   */
  @Override
  public E first() {
    if (isEmpty())
      throw new EmptyCollectionException();
    return data[findHighestPriorityIndex()].element;
  }

  /** {@inheritDoc} */
  @Override
  public boolean isEmpty() {
    return size == 0;
  }

  /** {@inheritDoc} */
  @Override
  public int size() {
    return size;
  }

  /** {@inheritDoc} */
  @Override
  public Iterator<E> iterator() {
    return new Iterator<E>() {
      private int current = 0;

      @Override
      public boolean hasNext() {
        return current < size;
      }

      @Override
      public E next() {
        if (!hasNext())
          throw new NoSuchElementException();
        return data[current++].element;
      }
    };
  }
}