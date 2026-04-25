package ds;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Queue;

/**
 * Implementación de un Árbol Binario de Búsqueda (BST) iterativo.
 * * @param <K> tipo de dato a almacenar, debe implementar Comparable.
 */
public class BST<K extends Comparable<K>> implements Iterable<K> {

  protected BSTNode<K> root;
  protected int size;

  /**
   * Constructor por defecto.
   * Complejidad Temporal: O(1)
   * Complejidad Espacial: O(1)
   */
  public BST() {
    this.root = null;
    this.size = 0;
  }

  /**
   * Añade un nodo iterativamente. Ignora elementos duplicados.
   * Complejidad Temporal: O(h) donde h es la altura del árbol. O(log n) si está
   * balanceado, O(n) en el peor caso.
   * Complejidad Espacial: O(1) al ser iterativo.
   * * @param x el elemento a añadir.
   */
  public void add(K x) {
    if (root == null) {
      root = new BSTNode<>(x);
      size++;
      return;
    }

    BSTNode<K> cur = root;
    while (true) {
      int c = x.compareTo(cur.key);
      if (c > 0) {
        if (cur.right != null)
          cur = cur.right;
        else {
          cur.right = new BSTNode<>(x);
          size++;
          break;
        }
      } else if (c < 0) {
        if (cur.left != null)
          cur = cur.left;
        else {
          cur.left = new BSTNode<>(x);
          size++;
          break;
        }
      } else {
        return;
      }
    }
  }

  /**
   * Busca un elemento en el árbol iterativamente.
   * Complejidad Temporal: O(h) proporcional a la altura del árbol.
   * Complejidad Espacial: O(1).
   * * @param x el elemento a buscar.
   * 
   * @return el nodo que contiene el elemento, o null si no se encuentra.
   */
  public BSTNode<K> search(K x) {
    BSTNode<K> cur = root;
    while (cur != null) {
      int c = x.compareTo(cur.key);
      if (c == 0)
        return cur;
      else if (c < 0)
        cur = cur.left;
      else
        cur = cur.right;
    }
    return null;
  }

  /**
   * Encuentra el nodo con el valor mínimo (extremo izquierdo).
   * Complejidad Temporal: O(h).
   * Complejidad Espacial: O(1).
   * * @return el nodo con el valor mínimo, o null si el árbol está vacío.
   */
  public BSTNode<K> min() {
    if (root == null)
      return null;
    BSTNode<K> cur = root;
    while (cur.left != null) {
      cur = cur.left;
    }
    return cur;
  }

  /**
   * Encuentra el nodo con el valor máximo (extremo derecho).
   * Complejidad Temporal: O(h).
   * Complejidad Espacial: O(1).
   * * @return el nodo con el valor máximo, o null si el árbol está vacío.
   */
  public BSTNode<K> max() {
    if (root == null)
      return null;
    BSTNode<K> cur = root;
    while (cur.right != null) {
      cur = cur.right;
    }
    return cur;
  }

  /**
   * Elimina el nodo que contiene el valor recibido, actualizando las alturas.
   * Maneja 3 casos: hoja, 1 hijo, o 2 hijos (usando el sucesor inorden).
   * Complejidad Temporal: O(h).
   * Complejidad Espacial: O(h) debido al uso de la pila para actualizar alturas.
   * * @param x el elemento a eliminar.
   */
  public void delete(K x) {
    if (root == null)
      return;

    Deque<BSTNode<K>> stack = new ArrayDeque<>();
    BSTNode<K> parent = null;
    BSTNode<K> current = root;

    while (current != null) {
      int cmp = x.compareTo(current.key);
      if (cmp == 0)
        break;
      stack.push(current);
      parent = current;
      if (cmp < 0)
        current = current.left;
      else
        current = current.right;
    }

    if (current == null)
      return;
    size--;

    if (current.left != null && current.right != null) {
      stack.push(current);
      BSTNode<K> succParent = current;
      BSTNode<K> succ = current.right;
      while (succ.left != null) {
        stack.push(succ);
        succParent = succ;
        succ = succ.left;
      }
      current.key = succ.key;
      current = succ;
      parent = succParent;
    }

    BSTNode<K> child = (current.left != null) ? current.left : current.right;

    if (parent == null) {
      root = child;
    } else if (parent.left == current) {
      parent.left = child;
    } else {
      parent.right = child;
    }

    while (!stack.isEmpty()) {
      stack.pop().updateHeight();
    }
  }

  /**
   * Devuelve el número de elementos en el árbol.
   * Complejidad Temporal: O(1)
   * Complejidad Espacial: O(1)
   * * @return tamaño del árbol.
   */
  public int size() {
    return size;
  }

  /**
   * Iterador inorden para recorrer el árbol.
   * * @return un iterador de los elementos.
   */
  @Override
  public Iterator<K> iterator() {
    return new CIterator();
  }

  /**
   * Clase interna que implementa el iterador inorden.
   */
  private class CIterator implements Iterator<K> {
    private Deque<BSTNode<K>> iteratorStack;

    /**
     * Constructor del iterador. Inicializa la pila descendiendo por la rama
     * izquierda.
     * Complejidad Temporal: O(h)
     * Complejidad Espacial: O(h)
     */
    public CIterator() {
      iteratorStack = new ArrayDeque<>();
      if (root != null) {
        stackLeftBranch(root);
      }
    }

    /**
     * Comprueba si hay más elementos en el recorrido.
     * Complejidad Temporal: O(1)
     */
    @Override
    public boolean hasNext() {
      return !iteratorStack.isEmpty();
    }

    /**
     * Devuelve el siguiente elemento en el recorrido inorden.
     * Complejidad Temporal: O(1) amortizado.
     */
    @Override
    public K next() {
      if (!hasNext())
        throw new NoSuchElementException();
      BSTNode<K> ret = iteratorStack.pop();
      if (ret.right != null) {
        stackLeftBranch(ret.right);
      }
      return ret.key;
    }

    /**
     * Método auxiliar que apila todos los nodos de la rama izquierda.
     * Complejidad Temporal: O(h)
     * Complejidad Espacial: O(h)
     */
    private void stackLeftBranch(BSTNode<K> node) {
      BSTNode<K> aux = node;
      do {
        iteratorStack.push(aux);
        aux = aux.left;
      } while (aux != null);
    }
  }

  /**
   * Devuelve una lista con todos los elementos mayores al recibido.
   * Complejidad Temporal: O(n) en el peor caso, O(log n + k) promedio.
   * Complejidad Espacial: O(h) por la pila implícita.
   * * @param x elemento a comparar.
   * 
   * @return lista de mayores.
   */
  public List<K> mayoresQue(K x) {
    List<K> result = new ArrayList<>();
    mayoresQueRec(root, x, result);
    return result;
  }

  private void mayoresQueRec(BSTNode<K> node, K x, List<K> result) {
    if (node == null)
      return;
    if (node.key.compareTo(x) > 0) {
      mayoresQueRec(node.left, x, result);
      result.add(node.key);
      mayoresQueRec(node.right, x, result);
    } else {
      mayoresQueRec(node.right, x, result);
    }
  }

  /**
   * Recorrido en Anchura.
   * Complejidad Temporal: O(n)
   * * @return lista de elementos.
   */
  public List<K> anchura() {
    List<K> result = new ArrayList<>();
    if (root == null)
      return result;
    Queue<BSTNode<K>> q = new LinkedList<>();
    q.add(root);
    while (!q.isEmpty()) {
      BSTNode<K> curr = q.poll();
      result.add(curr.key);
      if (curr.left != null)
        q.add(curr.left);
      if (curr.right != null)
        q.add(curr.right);
    }
    return result;
  }
}