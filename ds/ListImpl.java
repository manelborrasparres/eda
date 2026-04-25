package ds;

import adt.List;
import exceptions.WrongIndexException;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Implementación dinámica del TAD Lista utilizando nodos enlazados.
 * * @param <E> el tipo de elementos de la lista
 */
public class ListImpl<E> implements List<E> {

  /**
   * Clase interna que representa un nodo de la lista.
   * * @param <E> el tipo de dato almacenado en el nodo
   */
  private static class Node<E> {
    E data;
    Node<E> next;

    Node(E data) {
      this.data = data;
      this.next = null;
    }
  }

  /**
   * Clase interna que implementa el iterador para la lista.
   */
  private class CIterator implements Iterator<E> {
    private Node<E> current = head;

    @Override
    public boolean hasNext() {
      return current != null;
    }

    @Override
    public E next() {
      if (!hasNext()) {
        throw new NoSuchElementException();
      }
      E data = current.data;
      current = current.next;
      return data;
    }
  }

  private Node<E> head;
  private int size;

  /**
   * Constructor por defecto que inicializa una lista vacía.
   */
  public ListImpl() {
    this.head = null;
    this.size = 0;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void insert(int pos, E data) throws WrongIndexException {
    if (pos < 0 || pos > size) {
      throw new WrongIndexException();
    }
    Node<E> newNode = new Node<>(data);
    if (pos == 0) {
      newNode.next = head;
      head = newNode;
    } else {
      Node<E> current = head;
      for (int i = 0; i < pos - 1; i++) {
        current = current.next;
      }
      newNode.next = current.next;
      current.next = newNode;
    }
    size++;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void delete(int pos) throws WrongIndexException {
    if (pos < 0 || pos >= size) {
      throw new WrongIndexException();
    }
    if (pos == 0) {
      head = head.next;
    } else {
      Node<E> current = head;
      for (int i = 0; i < pos - 1; i++) {
        current = current.next;
      }
      current.next = current.next.next;
    }
    size--;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public E get(int pos) throws WrongIndexException {
    if (pos < 0 || pos >= size) {
      throw new WrongIndexException();
    }
    Node<E> current = head;
    for (int i = 0; i < pos; i++) {
      current = current.next;
    }
    return current.data;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public int search(E data) {
    Node<E> current = head;
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

  /**
   * {@inheritDoc}
   */
  @Override
  public Iterator<E> iterator() {
    return new CIterator();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public int size() {
    return size;
  }
}