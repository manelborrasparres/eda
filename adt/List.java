package adt;

import exceptions.WrongIndexException;
import java.util.Iterator;

/**
 * Interfaz que define el Tipo Abstracto de Datos Lista.
 * Una lista es una estructura de datos lineal que almacena una colección de
 * elementos.
 * * @param <E> el tipo de elementos mantenidos en esta lista
 */
public interface List<E> extends Iterable<E> {

  /**
   * Inserta un elemento en una posición arbitraria.
   * * @param pos la posición donde se insertará el elemento
   * 
   * @param data el elemento a insertar
   * @throws WrongIndexException si la posición está fuera de rango (pos < 0 ||
   *                             pos > size())
   */
  void insert(int pos, E data) throws WrongIndexException;

  /**
   * Elimina el elemento de una posición arbitraria.
   * * @param pos la posición del elemento a eliminar
   * 
   * @throws WrongIndexException si la posición está fuera de rango (pos < 0 ||
   *                             pos >= size())
   */
  void delete(int pos) throws WrongIndexException;

  /**
   * Accede al elemento de una posición arbitraria.
   * * @param pos la posición del elemento a recuperar
   * 
   * @return el elemento en la posición especificada
   * @throws WrongIndexException si la posición está fuera de rango (pos < 0 ||
   *                             pos >= size())
   */
  E get(int pos) throws WrongIndexException;

  /**
   * Busca un elemento en la colección.
   * * @param data el elemento a buscar
   * 
   * @return la posición de la primera ocurrencia del elemento, o -1 si no se
   *         encuentra
   */
  int search(E data);

  /**
   * Devuelve un iterador sobre los elementos de la lista.
   * * @return un iterador
   */
  Iterator<E> iterator();

  /**
   * Devuelve el número de elementos en la lista.
   * * @return el tamaño de la lista
   */
  int size();
}