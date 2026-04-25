package ds;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Representación de un árbol genérico usando el modelo Hijo izquierdo - Hermano
 * derecho.
 * 
 * @param <E> tipo de dato.
 */
public class ArbolGen<E> {
  private static class Nodo<E> {
    E dato;
    Nodo<E> hizq;
    Nodo<E> hder;

    Nodo(E dato) {
      this.dato = dato;
      this.hizq = null;
      this.hder = null;
    }
  }

  private Nodo<E> raiz;

  /**
   * Constructor por defecto.
   * Complejidad Temporal: O(1)
   */
  public ArbolGen(E raizDato) {
    this.raiz = new Nodo<>(raizDato);
  }

  /**
   * Recorrido en Preorden.
   * Complejidad Temporal: O(n)
   * 
   * @return lista de elementos.
   */
  public List<E> preorden() {
    List<E> res = new ArrayList<>();
    preordenRec(raiz, res);
    return res;
  }

  private void preordenRec(Nodo<E> n, List<E> res) {
    if (n == null)
      return;
    res.add(n.dato);
    preordenRec(n.hizq, res);
    preordenRec(n.hder, res);
  }

  /**
   * Recorrido en Inorden.
   * Complejidad Temporal: O(n)
   * 
   * @return lista de elementos.
   */
  public List<E> inorden() {
    List<E> res = new ArrayList<>();
    inordenRec(raiz, res);
    return res;
  }

  private void inordenRec(Nodo<E> n, List<E> res) {
    if (n == null)
      return;
    inordenRec(n.hizq, res);
    res.add(n.dato);
    preordenRec(n.hder, res);
  }

  /**
   * Recorrido en Postorden.
   * Complejidad Temporal: O(n)
   * 
   * @return lista de elementos.
   */
  public List<E> postorden() {
    List<E> res = new ArrayList<>();
    postordenRec(raiz, res);
    return res;
  }

  private void postordenRec(Nodo<E> n, List<E> res) {
    if (n == null)
      return;
    postordenRec(n.hizq, res);
    res.add(n.dato);
    postordenRec(n.hder, res);
  }

  /**
   * Recorrido en Anchura.
   * Complejidad Temporal: O(n)
   * Complejidad Espacial: O(n)
   * 
   * @return lista de elementos.
   */
  public List<E> anchura() {
    List<E> res = new ArrayList<>();
    if (raiz == null)
      return res;
    Queue<Nodo<E>> q = new LinkedList<>();
    q.add(raiz);
    while (!q.isEmpty()) {
      Nodo<E> actual = q.poll();
      res.add(actual.dato);
      Nodo<E> hijo = actual.hizq;
      while (hijo != null) {
        q.add(hijo);
        hijo = hijo.hder;
      }
    }
    return res;
  }
}