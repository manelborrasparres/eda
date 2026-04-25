package ds;

import adt.Stack;
import exceptions.EmptyCollectionException;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Implementación de una Pila usando representación secuencial (array)
 * con crecimiento dinámico.
 * 
 * @param <E> el tipo de elementos
 */
public class StackSeq<E> implements Stack<E> {
  private E[] data;
  private int topIndex;

  /**
   * Constructor por defecto. Inicializa la pila con capacidad para 10 elementos.
   */
  @SuppressWarnings("unchecked")
  public StackSeq() {
    data = (E[]) new Object[10];
    topIndex = -1;
  }

  /**
   * Dobla la capacidad del array interno cuando se llena.
   * Complejidad Temporal: O(n)
   * Complejidad Espacial: O(n)
   */
  @SuppressWarnings("unchecked")
  private void expandCapacity() {
    E[] newData = (E[]) new Object[data.length * 2];
    System.arraycopy(data, 0, newData, 0, data.length);
    data = newData;
  }

  /** {@inheritDoc} */
  @Override
  public void push(E element) {
    if (topIndex + 1 == data.length) {
      expandCapacity();
    }
    data[++topIndex] = element;
  }

  /** {@inheritDoc} */
  @Override
  public E pop() {
    if (isEmpty())
      throw new EmptyCollectionException();
    E element = data[topIndex];
    data[topIndex] = null; // Ayudar al recolector de basura
    topIndex--;
    return element;
  }

  /** {@inheritDoc} */
  @Override
  public E top() {
    if (isEmpty())
      throw new EmptyCollectionException();
    return data[topIndex];
  }

  /** {@inheritDoc} */
  @Override
  public boolean isEmpty() {
    return topIndex == -1;
  }

  /** {@inheritDoc} */
  @Override
  public int size() {
    return topIndex + 1;
  }

  /** {@inheritDoc} */
  @Override
  public Iterator<E> iterator() {
    return new Iterator<E>() {
      private int current = topIndex;

      @Override
      public boolean hasNext() {
        return current >= 0;
      }

      @Override
      public E next() {
        if (!hasNext())
          throw new NoSuchElementException();
        return data[current--];
      }
    };
  }
}