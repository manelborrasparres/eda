package adt;

import exceptions.EmptyCollectionException;

public interface Queue<E> extends Iterable<E> {
    void enqueue(E element);
    E dequeue() throws EmptyCollectionException;
    E first() throws EmptyCollectionException;
    int size();
    boolean isEmpty();
}