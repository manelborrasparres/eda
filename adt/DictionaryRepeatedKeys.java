package adt;

import java.util.Collection;
import java.util.Iterator;

/**
 * Interfaz que define un Diccionario que permite múltiples valores para una
 * misma clave.
 * 
 * @param <K> el tipo de las claves
 * @param <V> el tipo de los valores
 */
public interface DictionaryRepeatedKeys<K, V> extends Iterable<K> {

  /**
   * Inserta un valor asociado a una clave. Si la clave existe, añade el valor a
   * su colección.
   * Complejidad Temporal: O(1) amortizado
   * Complejidad Espacial: O(1)
   * 
   * @param key   la clave asociada
   * @param value el valor a insertar
   */
  void put(K key, V value);

  /**
   * Recupera la colección de valores asociados a una clave.
   * Complejidad Temporal: O(1)
   * Complejidad Espacial: O(1)
   * 
   * @param key la clave a buscar
   * @return colección de valores, o null si no existe
   */
  Collection<V> get(K key);

  /**
   * Elimina una clave y todos sus valores asociados.
   * Complejidad Temporal: O(1)
   * Complejidad Espacial: O(1)
   * 
   * @param key la clave a eliminar
   * @return la colección de valores eliminados, o null si no existía
   */
  Collection<V> remove(K key);

  /**
   * Consulta si existe la clave en el diccionario.
   * Complejidad Temporal: O(1)
   * 
   * @param key la clave a buscar
   * @return true si existe, false en caso contrario
   */
  boolean contains(K key);

  /**
   * Devuelve el número de claves únicas en el diccionario.
   * Complejidad Temporal: O(1)
   * 
   * @return la cantidad de claves
   */
  int size();

  /**
   * Devuelve un iterador sobre las claves del diccionario.
   * 
   * @return un iterador de claves
   */
  Iterator<K> iterator();
}