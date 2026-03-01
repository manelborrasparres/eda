package ds;

import adt.Queue;
import exceptions.EmptyCollectionException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class QueueLinkedImpl<E> implements Queue<E> {
    private Node<E> first, last;
    private int size;

    private static class Node<E> {
        E data;
        Node<E> next;
        Node(E data) { this.data = data; }
    }

    @Override
    public void enqueue(E element) {
        Node<E> newNode = new Node<>(element);
        if (isEmpty()) first = newNode;
        else last.next = newNode;
        last = newNode;
        size++;
    }

    @Override
    public E dequeue() throws EmptyCollectionException {
        if (isEmpty()) throw new EmptyCollectionException("Cola vacía");
        E data = first.data;
        first = first.next;
        if (first == null) last = null;
        size--;
        return data;
    }

    @Override
    public E first() throws EmptyCollectionException {
        if (isEmpty()) throw new EmptyCollectionException("Cola vacía");
        return first.data;
    }

    @Override
    public int size() { return size; }

    @Override
    public boolean isEmpty() { return size == 0; }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            private Node<E> current = first;
            public boolean hasNext() { return current != null; }
            public E next() {
                if (!hasNext()) throw new NoSuchElementException();
                E data = current.data;
                current = current.next;
                return data;
            }
        };
    }
}