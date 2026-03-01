package ds;

import java.util.Iterator;
import java.util.NoSuchElementException;
import adt.List;
import exceptions.WrongIndexException;

public class ListImpl<E> implements List<E> {

    private Node<E> dummy;
    private int size;

    public ListImpl() {
        this.dummy = new Node<>(null, null);
        this.size = 0;
    }

    @Override
    public void insert(int pos, E data) throws WrongIndexException {
        if (pos < 0 || pos > size) {
            throw new WrongIndexException("Posición fuera de rango");
        }
        
        Node<E> prev = getNodeAt(pos - 1); 
        Node<E> newNode = new Node<>(data, prev.next);
        prev.next = newNode;
        size++;
    }

    @Override
    public void delete(int pos) throws WrongIndexException {
        if (pos < 0 || pos >= size) {
            throw new WrongIndexException("Posición inválida");
        }

        Node<E> prev = getNodeAt(pos - 1);
        prev.next = prev.next.next;
        size--;
    }

    @Override
    public E get(int pos) throws WrongIndexException {
        if (pos < 0 || pos >= size) {
            throw new WrongIndexException("Índice inválido");
        }
        return getNodeAt(pos).data;
    }

    @Override
    public int search(E data) {
        Node<E> current = dummy.next;
        for (int i = 0; i < size; i++) {
            if (current.data.equals(data)) return i;
            current = current.next;
        }
        return -1;
    }

    @Override
    public int size() {
        return this.size;
    }

    private Node<E> getNodeAt(int pos) {
        Node<E> aux = dummy;
        for (int i = -1; i < pos; i++) {
            aux = aux.next;
        }
        return aux;
    }

    @Override
    public Iterator<E> iterator() {
        return new CIterator();
    }


    private static class Node<E> {
        E data;
        Node<E> next;

        Node(E data, Node<E> next) {
            this.data = data;
            this.next = next;
        }
    }

    private class CIterator implements Iterator<E> {
        private Node<E> current = dummy.next;

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public E next() {
            if (!hasNext()) throw new NoSuchElementException();
            E data = current.data;
            current = current.next;
            return data;
        }
    }
}