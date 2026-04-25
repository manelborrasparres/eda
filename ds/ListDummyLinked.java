package ds;

import adt.List;
import exceptions.WrongIndexException;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Implementación de Lista con representación enlazada haciendo uso de un Dummy
 * Node.
 * 
 * @param <E> el tipo de elementos
 */
public class ListDummyLinked<E> implements List<E> {

  private static class Node<E> {
    E data;
    Node<E> next;

    Node(E data) {
      this.data = data;
      this.next = null;
    }
  }

  private Node<E> dummy;
  private int size;

  /**
   * Constructor por defecto. Inicializa la lista con un nodo fantasma.
   */
  public ListDummyLinked() {
    this.dummy = new Node<>(null);
    this.size = 0;
  }

  /**
   * * {@inheritDoc}
   * Complejidad Temporal: O(n)
   * Complejidad Espacial: O(1)
   */
  @Override
  public void insert(int pos, E data) throws WrongIndexException {
    if (pos < 0 || pos > size)
      throw new WrongIndexException();

    Node<E> current = dummy;
    for (int i = 0; i < pos; i++) {
      current = current.next;
    }

    Node<E> newNode = new Node<>(data);
    newNode.next = current.next;
    current.next = newNode;
    size++;
  }

  /**
   * * {@inheritDoc}
   * Complejidad Temporal: O(n)
   * Complejidad Espacial: O(1)
   */
  @Override
  public void delete(int pos) throws WrongIndexException {
    if (pos < 0 || pos >= size)
      throw new WrongIndexException();

    Node<E> current = dummy;
    for (int i = 0; i < pos; i++) {
      current = current.next;
    }

    current.next = current.next.next;
    size--;
  }

  /**
   * * {@inheritDoc}
   * Complejidad Temporal: O(n)
   * Complejidad Espacial: O(1)
   */
  @Override
  public E get(int pos) throws WrongIndexException {
    if (pos < 0 || pos >= size)
      throw new WrongIndexException();

    Node<E> current = dummy.next;
    for (int i = 0; i < pos; i++) {
      current = current.next;
    }
    return current.data;
  }

  /**
   * * {@inheritDoc}
   * Complejidad Temporal: O(n)
   * Complejidad Espacial: O(1)
   */
  @Override
  public int search(E data) {
    Node<E> current = dummy.next;
    int pos = 0;
    while (current != null) {
      if ((data == null && current.data == null) || (data != null && data.equals(current.data))) {
        return pos;
      }
      current = current.next;
      pos++;
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
      private Node<E> current = dummy.next;

      @Override
      public boolean hasNext() {
        return current != null;
      }

      @Override
      public E next() {
        if (!hasNext())
          throw new NoSuchElementException();
        E data = current.data;
        current = current.next;
        return data;
      }
    };
  }
}