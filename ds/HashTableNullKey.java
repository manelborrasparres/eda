package ds;

import adt.Dictionary;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Implementación de Dictionary que permite almacenar una clave nula.
 * 
 * @param <K> el tipo de las claves
 * @param <V> el tipo de los valores
 */
public class HashTableNullKey<K, V> implements Dictionary<K, V> {

  private static class Entry<K, V> {
    K key;
    V value;
    Entry<K, V> next;

    Entry(K key, V value) {
      this.key = key;
      this.value = value;
    }
  }

  private Entry<K, V>[] table;
  private int size;

  /**
   * Constructor por defecto.
   */
  @SuppressWarnings("unchecked")
  public HashTableNullKey() {
    table = new Entry[16];
    size = 0;
  }

  /**
   * Función hash adaptada para permitir claves nulas.
   * Complejidad Temporal: O(1)
   * 
   * @param key la clave
   * @return el índice. Si es nulo, siempre devuelve 0.
   */
  private int hash(K key) {
    if (key == null)
      return 0;
    return Math.abs(key.hashCode()) % table.length;
  }

  @Override
  public V put(K key, V value) {
    int hash = hash(key);
    Entry<K, V> entry = table[hash];

    while (entry != null) {
      if ((key == null && entry.key == null) || (key != null && key.equals(entry.key))) {
        V old = entry.value;
        entry.value = value;
        return old;
      }
      entry = entry.next;
    }

    Entry<K, V> newEntry = new Entry<>(key, value);
    newEntry.next = table[hash];
    table[hash] = newEntry;
    size++;
    return null;
  }

  @Override
  public V get(K key) {
    int hash = hash(key);
    Entry<K, V> entry = table[hash];

    while (entry != null) {
      if ((key == null && entry.key == null) || (key != null && key.equals(entry.key))) {
        return entry.value;
      }
      entry = entry.next;
    }
    return null;
  }

  @Override
  public V remove(K key) {
    int hash = hash(key);
    Entry<K, V> entry = table[hash];
    Entry<K, V> prev = null;

    while (entry != null) {
      if ((key == null && entry.key == null) || (key != null && key.equals(entry.key))) {
        if (prev == null)
          table[hash] = entry.next;
        else
          prev.next = entry.next;
        size--;
        return entry.value;
      }
      prev = entry;
      entry = entry.next;
    }
    return null;
  }

  @Override
  public boolean contains(K key) {
    return get(key) != null;
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
  @SuppressWarnings("unchecked")
  public void clear() {
    table = new Entry[table.length];
    size = 0;
  }

  @Override
  public Iterator<K> iterator() {
    return new Iterator<K>() {
      private int bucketIndex = 0;
      private Entry<K, V> current = null;

      {
        advance();
      }

      private void advance() {
        if (current != null && current.next != null) {
          current = current.next;
          return;
        }
        if (current != null)
          bucketIndex++;
        while (bucketIndex < table.length && table[bucketIndex] == null) {
          bucketIndex++;
        }
        current = (bucketIndex < table.length) ? table[bucketIndex] : null;
      }

      @Override
      public boolean hasNext() {
        return current != null;
      }

      @Override
      public K next() {
        if (!hasNext())
          throw new NoSuchElementException();
        K key = current.key;
        advance();
        return key;
      }
    };
  }
}