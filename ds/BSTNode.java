package ds;

/**
 * Clase que representa un nodo de un Árbol Binario de Búsqueda.
 * 
 * @param <K> el tipo genérico de la clave almacenada, debe ser Comparable[cite:
 *            179].
 */
public class BSTNode<K extends Comparable<K>> {
  public K key;
  public BSTNode<K> left;
  public BSTNode<K> right;
  public int height;

  /**
   * Constructor del nodo.
   * Complejidad Temporal: O(1)
   * Complejidad Espacial: O(1)
   * 
   * @param key el valor a almacenar[cite: 171, 173].
   */
  public BSTNode(K key) {
    this.key = key;
    this.left = null;
    this.right = null;
    updateHeight(); // [cite: 175]
  }

  /**
   * Recalcula la altura del nodo basándose en la altura de sus hijos[cite: 184,
   * 185].
   * Complejidad Temporal: O(1)
   * Complejidad Espacial: O(1)
   */
  public void updateHeight() {
    int leftHeight = (left == null) ? 0 : left.height;
    int rightHeight = (right == null) ? 0 : right.height;
    this.height = 1 + Math.max(leftHeight, rightHeight);
  }

  /**
   * Calcula el factor de balanceo del nodo[cite: 186].
   * Complejidad Temporal: O(1)
   * Complejidad Espacial: O(1)
   * 
   * @return Diferencia de alturas: altura(izq) - altura(der)[cite: 187].
   */
  public int balanceFactor() {
    int leftHeight = (left == null) ? 0 : left.height;
    int rightHeight = (right == null) ? 0 : right.height;
    return leftHeight - rightHeight;
  }
}