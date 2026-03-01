package ds;

import adt.Stack;
import exceptions.EmptyCollectionException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Arrays;

/**
 * Implementación secuencial de una Pila (Stack) que crece dinámicamente.
 *
 */
public class StackImpl<E> implements Stack<E> {
    private E[] data;
    private int size;
    private static final int INITIAL_CAPACITY = 10;

    @SuppressWarnings("unchecked")
    public StackImpl() {
        // En Java no se pueden crear arrays de genéricos directamente
        this.data = (E[]) new Object[INITIAL_CAPACITY];
        this.size = 0;
    }

    @Override
    public void push(E element) {
        // Si no quedan posiciones libres, la estructura crece
        if (size == data.length) {
            data = Arrays.copyOf(data, data.length * 2);
        }
        data[size++] = element;
    }

    @Override
    public E pop() throws EmptyCollectionException {
        if (isEmpty()) {
            throw new EmptyCollectionException("No se puede hacer pop: la pila está vacía.");
        }
        E element = data[--size];
        data[size] = null; // Limpieza para el recolector de basura
        return element;
    }

    @Override
    public E peek() throws EmptyCollectionException {
        if (isEmpty()) {
            throw new EmptyCollectionException("No se puede hacer peek: la pila está vacía.");
        }
        // Devuelve el último elemento sin eliminarlo
        return data[size - 1];
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public Iterator<E> iterator() {
        // Implementación de iterador para permitir uso en for-each
        return new Iterator<E>() {
            private int current = size - 1;

            @Override
            public boolean hasNext() {
                return current >= 0;
            }

            @Override
            public E next() {
                if (!hasNext()) throw new NoSuchElementException();
                return data[current--];
            }
        };
    }
}