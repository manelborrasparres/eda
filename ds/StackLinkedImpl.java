package ds;

import adt.Stack;
import exceptions.EmptyCollectionException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class StackLinkedImpl<E> implements Stack<E> {
    private Node<E> top;
    private int size;

    private static class Node<E> {
        E data;
        Node<E> next;
        Node(E data, Node<E> next) {
            this.data = data;
            this.next = next;
        }
    }

    public StackLinkedImpl() {
        this.top = null;
        this.size = 0;
    }

    @Override
    public void push(E element) {
        top = new Node<>(element, top);
        size++;
    }

    @Override
    public E pop() throws EmptyCollectionException {
        if (isEmpty()) throw new EmptyCollectionException("Pila vacía");
        E data = top.data;
        top = top.next;
        size--;
        return data;
    }

    @Override
    public E peek() throws EmptyCollectionException {
        if (isEmpty()) throw new EmptyCollectionException("Pila vacía");
        return top.data;
    }

    @Override
    public int size() { return size; }

    @Override
    public boolean isEmpty() { return top == null; }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            private Node<E> current = top;
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