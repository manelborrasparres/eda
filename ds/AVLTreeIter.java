package ds;

import java.util.Stack;

/**
 * Árbol AVL implementado de forma iterativa.
 * * @param <K> tipo de clave.
 */
public class AVLTreeIter<K extends Comparable<K>> extends AVLTreeRec<K> {

  /**
   * Inserción iterativa usando pila para rastrear el camino y rebalancear.
   * Complejidad Temporal: O(log n)
   * Complejidad Espacial: O(log n) por el uso de la pila.
   * * @param key clave a insertar.
   */
  @Override
  public void insertar(K key) {
    if (root == null) {
      root = new BSTNode<>(key);
      size++;
      return;
    }

    Stack<BSTNode<K>> path = new Stack<>();
    BSTNode<K> curr = root;

    while (curr != null) {
      path.push(curr);
      int cmp = key.compareTo(curr.key);
      if (cmp < 0) {
        if (curr.left == null) {
          curr.left = new BSTNode<>(key);
          size++;
          break;
        }
        curr = curr.left;
      } else if (cmp > 0) {
        if (curr.right == null) {
          curr.right = new BSTNode<>(key);
          size++;
          break;
        }
        curr = curr.right;
      } else {
        return;
      }
    }

    while (!path.isEmpty()) {
      BSTNode<K> node = path.pop();
      node.updateHeight();
      BSTNode<K> balanced = balanceHelper(node);

      if (path.isEmpty()) {
        root = balanced;
      } else {
        BSTNode<K> parent = path.peek();
        if (parent.left == node)
          parent.left = balanced;
        else
          parent.right = balanced;
      }
    }
  }

  private BSTNode<K> balanceHelper(BSTNode<K> node) {
    int bf = node.balanceFactor();
    if (bf > 1) {
      if (node.left.balanceFactor() < 0)
        node.left = rotateLeftIter(node.left);
      return rotateRightIter(node);
    }
    if (bf < -1) {
      if (node.right.balanceFactor() > 0)
        node.right = rotateRightIter(node.right);
      return rotateLeftIter(node);
    }
    return node;
  }

  private BSTNode<K> rotateRightIter(BSTNode<K> y) {
    BSTNode<K> x = y.left;
    y.left = x.right;
    x.right = y;
    y.updateHeight();
    x.updateHeight();
    return x;
  }

  private BSTNode<K> rotateLeftIter(BSTNode<K> x) {
    BSTNode<K> y = x.right;
    x.right = y.left;
    y.left = x;
    x.updateHeight();
    y.updateHeight();
    return y;
  }
}