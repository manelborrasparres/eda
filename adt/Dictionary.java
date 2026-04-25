package adt;

import java.util.Iterator;

/**
 * Interfaz que define el Tipo Abstracto de Datos Diccionario.
 * Un diccionario es una estructura de datos no lineal que almacena una
 * colección de
 * elementos a la que es posible acceder mediante una clave.
 * * @param <K> el tipo de las claves
 * 
 * @param <V> el tipo de los valores
 */
public interface Dictionary<K, V> extends Iterable<K> {

  /**
   * Inserta un elemento asignándole una clave.
   * Si la clave ya existe, sustituye su valor.
   * * @param key la clave asociada al valor
   * 
   * @param value el valor a insertar
   * @return el valor anterior asociado a la clave, o null si no existía
   */
  V put(K key, V value);

  /**
   * Recupera un elemento mediante su clave.
   * * @param key la clave del elemento a buscar
   * 
   * @return el valor asociado a la clave, o null si no se encuentra
   */
  V get(K key);

  /**
   * Elimina un elemento mediante su clave.
   * * @param key la clave del elemento a eliminar
   * 
   * @return el valor asociado a la clave eliminada, o null si no existía
   */
  V remove(K key);

  /**
   * Consulta si existe un elemento con cierta clave asociada.
   * * @param key la clave a buscar
   * 
   * @return true si la clave existe en el diccionario, false en caso contrario
   */
  boolean contains(K key);

  /**
   * Devuelve el número de elementos en el diccionario.
   * * @return la cantidad de elementos
   */
  int size();

  /**
   * Consulta si la colección está vacía.
   * * @return true si el diccionario está vacío, false en caso contrario
   */
  boolean isEmpty();

  /**
   * Elimina todos los elementos almacenados en el diccionario.
   */
  void clear();

  /**
   * Devuelve un iterador sobre las claves del diccionario.
   * * @return un iterador de claves
   */
  Iterator<K> iterator();
}