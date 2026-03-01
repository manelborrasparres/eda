package adt;

import exceptions.EmptyCollectionException; // Necesitarás crear esta excepción

public interface Stack<E> extends Iterable<E> {
    void push(E element);
    E pop() throws EmptyCollectionException;
    E peek() throws EmptyCollectionException;
    int size();
    boolean isEmpty();
}
