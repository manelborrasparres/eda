package ds;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Implementación de un Árbol Binario de Búsqueda (BST) iterativo[cite: 131,
 * 138].
 * 
 * @param <K> tipo de dato a almacenar, debe implementar Comparable[cite: 189].
 */
public class BST<K extends Comparable<K>> implements Iterable<K> {

  private BSTNode<K> root;
  private int size;

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
   * Añade un nodo iterativamente. Ignora elementos duplicados[cite: 221, 233,
   * 250].
   * Complejidad Temporal: O(h) donde h es la altura del árbol. O(log n) si está
   * balanceado, O(n) en el peor caso[cite: 248, 249].
   * Complejidad Espacial: O(1) al ser iterativo.
   * 
   * @param x el elemento a añadir[cite: 220, 221].
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
        return; // Elemento duplicado, se ignora[cite: 247, 250].
      }
    }
  }

  /**
   * Busca un elemento en el árbol iterativamente[cite: 252].
   * Complejidad Temporal: O(h) proporcional a la altura del árbol[cite: 282].
   * Complejidad Espacial: O(1).
   * 
   * @param x el elemento a buscar[cite: 251].
   * @return el nodo que contiene el elemento, o null si no se encuentra[cite:
   *         252, 280].
   */
  public BSTNode<K> search(K x) {
    BSTNode<K> cur = root;
    while (cur != null) {
      int c = x.compareTo(cur.key);
      if (c == 0)
        return cur; // Encontrado[cite: 261, 262].
      else if (c < 0)
        cur = cur.left; // Buscar en subárbol izquierdo[cite: 265, 266].
      else
        cur = cur.right; // Buscar en subárbol derecho[cite: 268, 269].
    }
    return null; // No encontrado al llegar a null[cite: 277, 280].
  }

  /**
   * Encuentra el nodo con el valor mínimo (extremo izquierdo)[cite: 286, 288].
   * Complejidad Temporal: O(h)[cite: 306].
   * Complejidad Espacial: O(1).
   * 
   * @return el nodo con el valor mínimo, o null si el árbol está vacío.
   */
  public BSTNode<K> min() {
    if (root == null)
      return null;
    BSTNode<K> cur = root;
    while (cur.left != null) {
      cur = cur.left; // Ir siempre a la izquierda[cite: 355].
    }
    return cur;
  }

  /**
   * Encuentra el nodo con el valor máximo (extremo derecho)[cite: 286, 296].
   * Complejidad Temporal: O(h)[cite: 306].
   * Complejidad Espacial: O(1).
   * 
   * @return el nodo con el valor máximo, o null si el árbol está vacío.
   */
  public BSTNode<K> max() {
    if (root == null)
      return null;
    BSTNode<K> cur = root;
    while (cur.right != null) {
      cur = cur.right; // Ir siempre a la derecha[cite: 357].
    }
    return cur;
  }

  /**
   * Elimina el nodo que contiene el valor recibido, actualizando las
   * alturas[cite: 126, 307].
   * Maneja 3 casos: hoja, 1 hijo, o 2 hijos (usando el sucesor inorden) [cite:
   * 310-321, 359].
   * Complejidad Temporal: O(h)[cite: 323].
   * Complejidad Espacial: O(h) debido al uso de la pila para actualizar
   * alturas[cite: 330, 345].
   * 
   * @param x el elemento a eliminar.
   */
  public void delete(K x) {
    if (root == null)
      return; // [cite: 327, 328]

    Deque<BSTNode<K>> stack = new ArrayDeque<>(); // Pila para guardar el camino y actualizar alturas[cite: 330].
    BSTNode<K> parent = null;
    BSTNode<K> current = root;

    // Buscar el nodo a eliminar [cite: 332]
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
      return; // No encontrado[cite: 334].
    size--; // [cite: 333]

    // Caso 3: Dos hijos. Buscar el sucesor inorden (mínimo del subárbol
    // derecho)[cite: 320, 321].
    if (current.left != null && current.right != null) {
      stack.push(current);
      BSTNode<K> succParent = current;
      BSTNode<K> succ = current.right;
      while (succ.left != null) {
        stack.push(succ);
        succParent = succ;
        succ = succ.left;
      }
      current.key = succ.key; // Sustituir valor[cite: 322].
      current = succ; // Ahora procedemos a eliminar el nodo sucesor
      parent = succParent;
    }

    // Casos 1 y 2: Nodo hoja o con un solo hijo[cite: 311, 315].
    BSTNode<K> child = (current.left != null) ? current.left : current.right;

    if (parent == null) {
      root = child;
    } else if (parent.left == current) {
      parent.left = child; // Desconectar o puentear[cite: 314, 318].
    } else {
      parent.right = child; // Desconectar o puentear[cite: 314, 318].
    }

    // Actualizar alturas recorriendo la pila hacia la raíz [cite: 344-348].
    while (!stack.isEmpty()) {
      stack.pop().updateHeight();
    }
  }

  /**
   * Devuelve el número de elementos en el árbol.
   * Complejidad Temporal: O(1)
   * Complejidad Espacial: O(1)
   * 
   * @return tamaño del árbol.
   */
  public int size() {
    return size;
  }

  /**
   * Iterador inorden para recorrer el árbol[cite: 132, 191].
   * 
   * @return un iterador de los elementos.
   */
  @Override
  public Iterator<K> iterator() {
    return new CIterator(); // [cite: 193]
  }

  /**
   * Clase interna que implementa el iterador inorden[cite: 194].
   */
  private class CIterator implements Iterator<K> {
    private Deque<BSTNode<K>> iteratorStack; // [cite: 195]

    /**
     * Constructor del iterador. Inicializa la pila descendiendo por la rama
     * izquierda[cite: 196, 199].
     * Complejidad Temporal: O(h)
     * Complejidad Espacial: O(h)
     */
    public CIterator() {
      iteratorStack = new ArrayDeque<>(); // [cite: 198]
      if (root != null) {
        stackLeftBranch(root); // [cite: 199]
      }
    }

    /**
     * Comprueba si hay más elementos en el recorrido[cite: 201].
     * Complejidad Temporal: O(1)
     */
    @Override
    public boolean hasNext() {
      return !iteratorStack.isEmpty(); // [cite: 203]
    }

    /**
     * Devuelve el siguiente elemento en el recorrido inorden[cite: 205].
     * Complejidad Temporal: O(1) amortizado.
     */
    @Override
    public K next() {
      if (!hasNext())
        throw new NoSuchElementException();
      BSTNode<K> ret = iteratorStack.pop(); // [cite: 206]
      if (ret.right != null) {
        stackLeftBranch(ret.right); // [cite: 210]
      }
      return ret.key; // [cite: 211]
    }

    /**
     * Método auxiliar que apila todos los nodos de la rama izquierda[cite: 212].
     * Complejidad Temporal: O(h)
     * Complejidad Espacial: O(h)
     */
    private void stackLeftBranch(BSTNode<K> node) {
      BSTNode<K> aux = node; // [cite: 213]
      do {
        iteratorStack.push(aux); // [cite: 217]
        aux = aux.left; // [cite: 218]
      } while (aux != null); // [cite: 219]
    }
  }
}