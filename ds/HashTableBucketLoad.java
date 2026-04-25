package ds;

/**
 * Extensión de la HashTable normal que permite consultar la carga
 * de cada cubeta en tiempo constante.
 * 
 * @param <K> el tipo de las claves
 * @param <V> el tipo de los valores
 */
public class HashTableBucketLoad<K, V> extends HashTable<K, V> {

  private int[] bucketLoads;

  /**
   * Constructor por defecto.
   */
  public HashTableBucketLoad() {
    super(16);
    bucketLoads = new int[16];
  }

  /**
   * Constructor con capacidad.
   * 
   * @param capacity capacidad inicial
   */
  public HashTableBucketLoad(int capacity) {
    super(capacity);
    bucketLoads = new int[capacity];
  }

  private int hash(K key) {
    return Math.abs(key.hashCode()) % bucketLoads.length;
  }

  @Override
  public V put(K key, V value) {
    boolean exists = contains(key);
    V result = super.put(key, value);
    if (!exists && key != null) {
      bucketLoads[hash(key)]++;
    }
    return result;
  }

  @Override
  public V remove(K key) {
    V result = super.remove(key);
    if (result != null && key != null) {
      bucketLoads[hash(key)]--;
    }
    return result;
  }

  /**
   * Devuelve la cantidad de elementos en una cubeta específica.
   * Complejidad Temporal: O(1)
   * 
   * @param bucketIndex el índice de la cubeta
   * @return la cantidad de elementos en esa cubeta
   */
  public int getBucketLoad(int bucketIndex) {
    if (bucketIndex < 0 || bucketIndex >= bucketLoads.length) {
      throw new IndexOutOfBoundsException();
    }
    return bucketLoads[bucketIndex];
  }
}