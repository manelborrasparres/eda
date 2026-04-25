package ds;

import adt.Dictionary;
import java.io.Serializable;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Implementación de la interfaz Dictionary usando una tabla hash.
 * Implementa Serializable para permitir su guardado en ficheros.
 * 
 * @param <K> el tipo de las claves
 * @param <V> el tipo de los valores
 */
public class HashTable<K, V> implements Dictionary<K, V>, Serializable {

  private static final long serialVersionUID = 1L;

  /**
   * Clase interna que representa una entrada en la tabla hash.
   * 
   * @param <K> el tipo de la clave
   * @param <V> el tipo del valor
   */
  private static class TableEntry<K, V> implements Serializable {
    private static final long serialVersionUID = 1L;
    K key;
    V value;
    TableEntry<K, V> next;

    TableEntry(K key, V value) {
      this.key = key;
      this.value = value;
      this.next = null;
    }
  }

  private class CIterator implements Iterator<K> {
    private int bucketIndex = 0;
    private TableEntry<K, V> currentEntry = null;

    CIterator() {
      advanceToNextEntry();
    }

    private void advanceToNextEntry() {
      if (currentEntry != null && currentEntry.next != null) {
        currentEntry = currentEntry.next;
        return;
      }
      if (currentEntry != null) {
        bucketIndex++;
      }
      while (bucketIndex < table.length && table[bucketIndex] == null) {
        bucketIndex++;
      }
      if (bucketIndex < table.length) {
        currentEntry = table[bucketIndex];
      } else {
        currentEntry = null;
      }
    }

    @Override
    public boolean hasNext() {
      return currentEntry != null;
    }

    @Override
    public K next() {
      if (!hasNext()) {
        throw new NoSuchElementException();
      }
      K key = currentEntry.key;
      advanceToNextEntry();
      return key;
    }
  }

  private TableEntry<K, V>[] table;
  private int size;
  private static final double LOAD_FACTOR_THRESHOLD = 0.75;

  @SuppressWarnings("unchecked")
  public HashTable() {
    this(16);
  }

  @SuppressWarnings("unchecked")
  public HashTable(int capacity) {
    table = new TableEntry[capacity];
    size = 0;
  }

  private int hash(K key) {
    return Math.abs(key.hashCode()) % table.length;
  }

  @SuppressWarnings("unchecked")
  private void rehash() {
    TableEntry<K, V>[] oldTable = table;
    table = new TableEntry[oldTable.length * 2];
    size = 0;
    for (TableEntry<K, V> entry : oldTable) {
      while (entry != null) {
        put(entry.key, entry.value);
        entry = entry.next;
      }
    }
  }

  @Override
  public V put(K key, V value) {
    if (key == null)
      return null;
    int hash = hash(key);
    TableEntry<K, V> entry = table[hash];
    while (entry != null) {
      if (entry.key.equals(key)) {
        V oldValue = entry.value;
        entry.value = value;
        return oldValue;
      }
      entry = entry.next;
    }

    TableEntry<K, V> newEntry = new TableEntry<>(key, value);
    newEntry.next = table[hash];
    table[hash] = newEntry;
    size++;

    if ((double) size / table.length > LOAD_FACTOR_THRESHOLD) {
      rehash();
    }
    return null;
  }

  @Override
  public V get(K key) {
    if (key == null)
      return null;
    int hash = hash(key);
    TableEntry<K, V> entry = table[hash];
    while (entry != null) {
      if (entry.key.equals(key)) {
        return entry.value;
      }
      entry = entry.next;
    }
    return null;
  }

  @Override
  public V remove(K key) {
    if (key == null)
      return null;
    int hash = hash(key);
    TableEntry<K, V> entry = table[hash];
    TableEntry<K, V> prev = null;

    while (entry != null) {
      if (entry.key.equals(key)) {
        if (prev == null) {
          table[hash] = entry.next;
        } else {
          prev.next = entry.next;
        }
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
    table = new TableEntry[table.length];
    size = 0;
  }

  @Override
  public Iterator<K> iterator() {
    return new CIterator();
  }
}