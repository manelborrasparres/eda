package ds;

/**
 * Árbol AVL implementado de forma recursiva.
 * * @param <K> tipo de clave.
 */
public class AVLTreeRec<K extends Comparable<K>> extends BST<K> {

  /**
   * Inserción recursiva con balanceo automático.
   * Complejidad Temporal: O(log n)
   * Complejidad Espacial: O(log n) por la pila de recursión.
   * * @param key clave a insertar.
   */
  public void insertar(K key) {
    root = insertRec(root, key);
  }

  private BSTNode<K> insertRec(BSTNode<K> node, K key) {
    if (node == null) {
      size++;
      return new BSTNode<>(key);
    }

    int cmp = key.compareTo(node.key);
    if (cmp < 0)
      node.left = insertRec(node.left, key);
    else if (cmp > 0)
      node.right = insertRec(node.right, key);
    else
      return node;

    node.updateHeight();
    return balance(node);
  }

  private BSTNode<K> balance(BSTNode<K> node) {
    int bf = node.balanceFactor();
    if (bf > 1) {
      if (node.left.balanceFactor() < 0) {
        node.left = rotateLeft(node.left);
      }
      return rotateRight(node);
    }
    if (bf < -1) {
      if (node.right.balanceFactor() > 0) {
        node.right = rotateRight(node.right);
      }
      return rotateLeft(node);
    }
    return node;
  }

  private BSTNode<K> rotateRight(BSTNode<K> y) {
    BSTNode<K> x = y.left;
    BSTNode<K> T2 = x.right;
    x.right = y;
    y.left = T2;
    y.updateHeight();
    x.updateHeight();
    return x;
  }

  private BSTNode<K> rotateLeft(BSTNode<K> x) {
    BSTNode<K> y = x.right;
    BSTNode<K> T2 = y.left;
    y.left = x;
    x.right = T2;
    x.updateHeight();
    y.updateHeight();
    return y;
  }
}