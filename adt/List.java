package adt;

import exceptions.WrongIndexException;
import java.util.Iterator;

public interface List<E> extends Iterable<E> {
    // El método insert debe declarar la excepción [cite: 79]
    void insert(int pos, E data) throws WrongIndexException; 
    
    // El método delete también debe declararla [cite: 80]
    void delete(int pos) throws WrongIndexException;
    
    // El método get también debe declararla [cite: 81]
    E get(int pos) throws WrongIndexException;
    
    int search(E data);
    int size();
    Iterator<E> iterator();
}