package ds;

import adt.Queue;
import exceptions.EmptyCollectionException;
import java.util.Iterator;
import java.util.Arrays;
import java.util.NoSuchElementException;

/**
 * Cola con prioridad de representación secuencial dinámica.
 * Los elementos deben ser Comparables.
 */
public class PriorityQueueImpl<E extends Comparable<E>> implements Queue<E> {
    private E[] data;
    private int size;
    private static final int INITIAL_CAPACITY = 10;

    @SuppressWarnings("unchecked")
    public PriorityQueueImpl() {
        this.data = (E[]) new Comparable[INITIAL_CAPACITY];
        this.size = 0;
    }

    @Override
    public void enqueue(E element) {
        // 1. Verificar si hay que crecer el array
        if (size == data.length) {
            data = Arrays.copyOf(data, data.length * 2);
        }

        // 2. Inserción ordenada para gestionar la prioridad
        int i = size - 1;
        while (i >= 0 && element.compareTo(data[i]) > 0) {
            data[i + 1] = data[i];
            i--;
        }
        data[i + 1] = element;
        size++;
    }

    @Override
    public E dequeue() throws EmptyCollectionException {
        if (isEmpty()) throw new EmptyCollectionException("Cola vacía");
        // Extraemos el último (el de mayor prioridad debido al orden del enqueue)
        E element = data[--size];
        data[size] = null; 
        return element;
    }

    @Override
    public E first() throws EmptyCollectionException {
        if (isEmpty()) throw new EmptyCollectionException("Cola vacía");
        return data[size - 1];
    }

    @Override
    public int size() { return size; }

    @Override
    public boolean isEmpty() { return size == 0; }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            private int current = size - 1;
            public boolean hasNext() { return current >= 0; }
            public E next() {
                if (!hasNext()) throw new NoSuchElementException();
                return data[current--];
            }
        };
    }
}