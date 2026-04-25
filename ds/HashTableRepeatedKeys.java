package ds;

import adt.DictionaryRepeatedKeys;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.NoSuchElementException;

/**
 * Implementación de DictionaryRepeatedKeys usando una tabla hash.
 * Los valores repetidos se almacenan en orden LIFO.
 * 
 * @param <K> el tipo de las claves
 * @param <V> el tipo de los valores
 */
public class HashTableRepeatedKeys<K, V> implements DictionaryRepeatedKeys<K, V> {

  private static class Entry<K, V> {
    K key;
    LinkedList<V> values;
    Entry<K, V> next;

    Entry(K key, V value) {
      this.key = key;
      this.values = new LinkedList<>();
      this.values.addFirst(value);
    }
  }

  private Entry<K, V>[] table;
  private int size;

  /**
   * Constructor por defecto.
   */
  @SuppressWarnings("unchecked")
  public HashTableRepeatedKeys() {
    table = new Entry[16];
    size = 0;
  }

  private int hash(K key) {
    return Math.abs(key.hashCode()) % table.length;
  }

  @Override
  public void put(K key, V value) {
    if (key == null)
      return;
    int hash = hash(key);
    Entry<K, V> entry = table[hash];

    while (entry != null) {
      if (entry.key.equals(key)) {
        entry.values.addFirst(value);
        return;
      }
      entry = entry.next;
    }

    Entry<K, V> newEntry = new Entry<>(key, value);
    newEntry.next = table[hash];
    table[hash] = newEntry;
    size++;
  }

  @Override
  public Collection<V> get(K key) {
    if (key == null)
      return null;
    int hash = hash(key);
    Entry<K, V> entry = table[hash];

    while (entry != null) {
      if (entry.key.equals(key)) {
        return entry.values;
      }
      entry = entry.next;
    }
    return null;
  }

  @Override
  public Collection<V> remove(K key) {
    if (key == null)
      return null;
    int hash = hash(key);
    Entry<K, V> entry = table[hash];
    Entry<K, V> prev = null;

    while (entry != null) {
      if (entry.key.equals(key)) {
        if (prev == null)
          table[hash] = entry.next;
        else
          prev.next = entry.next;
        size--;
        return entry.values;
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