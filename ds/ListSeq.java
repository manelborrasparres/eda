package ds;

import adt.List;
import exceptions.WrongIndexException;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Implementación de Lista con representación secuencial (array dinámico).
 * 
 * @param <E> el tipo de elementos
 */
public class ListSeq<E> implements List<E> {
  private E[] data;
  private int size;

  /**
   * Constructor por defecto.
   */
  @SuppressWarnings("unchecked")
  public ListSeq() {
    data = (E[]) new Object[10];
    size = 0;
  }

  @SuppressWarnings("unchecked")
  private void expandCapacity() {
    E[] newData = (E[]) new Object[data.length * 2];
    System.arraycopy(data, 0, newData, 0, size);
    data = newData;
  }

  /**
   * * {@inheritDoc}
   * Complejidad Temporal: O(n) debido al desplazamiento
   * Complejidad Espacial: O(1) o O(n) si expande
   */
  @Override
  public void insert(int pos, E element) throws WrongIndexException {
    if (pos < 0 || pos > size)
      throw new WrongIndexException();
    if (size == data.length)
      expandCapacity();

    for (int i = size; i > pos; i--) {
      data[i] = data[i - 1];
    }
    data[pos] = element;
    size++;
  }

  /**
   * * {@inheritDoc}
   * Complejidad Temporal: O(n) debido al desplazamiento
   * Complejidad Espacial: O(1)
   */
  @Override
  public void delete(int pos) throws WrongIndexException {
    if (pos < 0 || pos >= size)
      throw new WrongIndexException();
    for (int i = pos; i < size - 1; i++) {
      data[i] = data[i + 1];
    }
    data[size - 1] = null;
    size--;
  }

  /**
   * * {@inheritDoc}
   * Complejidad Temporal: O(1)
   * Complejidad Espacial: O(1)
   */
  @Override
  public E get(int pos) throws WrongIndexException {
    if (pos < 0 || pos >= size)
      throw new WrongIndexException();
    return data[pos];
  }

  /**
   * * {@inheritDoc}
   * Complejidad Temporal: O(n)
   * Complejidad Espacial: O(1)
   */
  @Override
  public int search(E element) {
    for (int i = 0; i < size; i++) {
      if ((element == null && data[i] == null) || (element != null && element.equals(data[i]))) {
        return i;
      }
    }
    return -1;
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
        return data[current++];
      }
    };
  }
}