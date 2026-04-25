package ds;

import adt.ColaPrioridad;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Implementación de un Montículo de Máximos mediante un array.
 * 
 * @param <E> tipo de los elementos.
 */
public class MaxHeap<E extends Comparable<E>> implements ColaPrioridad<E> {
  protected E[] elVector;
  protected int talla = 0;

  /**
   * Constructor por defecto.
   * 
   * @param capacidad capacidad inicial.
   */
  @SuppressWarnings("unchecked")
  public MaxHeap(int capacidad) {
    elVector = (E[]) new Comparable[capacidad + 1];
  }

  /**
   * Constructor para construir un montículo a partir de un array.
   * Complejidad Temporal: O(n)
   * Complejidad Espacial: O(n)
   * 
   * @param elementos array inicial.
   */
  @SuppressWarnings("unchecked")
  public MaxHeap(E[] elementos) {
    talla = elementos.length;
    elVector = (E[]) new Comparable[talla + 1];
    for (int i = 0; i < elementos.length; i++) {
      elVector[i + 1] = elementos[i];
    }
    buildheap();
  }

  /**
   * Restaura la propiedad del montículo hundiendo el elemento.
   * Complejidad Temporal: O(log n)
   * Complejidad Espacial: O(1)
   * 
   * @param pos posición a hundir.
   */
  private void heapify(int pos) {
    int hizq, hder, mayor;
    hizq = 2 * pos;
    hder = 2 * pos + 1;
    mayor = pos;

    if (hizq <= talla && elVector[hizq].compareTo(elVector[mayor]) > 0)
      mayor = hizq;
    if (hder <= talla && elVector[hder].compareTo(elVector[mayor]) > 0)
      mayor = hder;

    if (mayor != pos) {
      E aux = elVector[pos];
      elVector[pos] = elVector[mayor];
      elVector[mayor] = aux;
      heapify(mayor);
    }
  }

  /**
   * Construye un montículo a partir de un array desordenado.
   * Complejidad Temporal: O(n)
   */
  private void buildheap() {
    for (int i = talla / 2; i > 0; i--) {
      heapify(i);
    }
  }

  /**
   * Ordena el vector interno de menor a mayor destruyendo la estructura de
   * montículo.
   * Complejidad Temporal: O(n log n)
   */
  public void heapsort() {
    buildheap();
    int originalTalla = talla;
    for (int i = talla; i > 1; i--) {
      E aux = elVector[i];
      elVector[i] = elVector[1];
      elVector[1] = aux;
      talla--;
      heapify(1);
    }
    talla = originalTalla;
  }

  @Override
  public void insertar(E x) {
    if (talla + 1 >= elVector.length)
      return;
    elVector[++talla] = x;
    int i = talla;
    while (i > 1 && elVector[i / 2].compareTo(x) < 0) {
      elVector[i] = elVector[i / 2];
      i = i / 2;
    }
    elVector[i] = x;
  }

  @Override
  public E maximo() {
    if (talla == 0)
      return null;
    return elVector[1];
  }

  @Override
  public E eliminarMax() {
    if (talla == 0)
      return null;
    E max = elVector[1];
    elVector[1] = elVector[talla--];
    heapify(1);
    return max;
  }

  /**
   * Recorrido en Preorden del montículo.
   * Complejidad Temporal: O(n)
   * 
   * @return lista con el recorrido.
   */
  public List<E> preorden() {
    List<E> res = new ArrayList<>();
    preordenRec(1, res);
    return res;
  }

  private void preordenRec(int pos, List<E> res) {
    if (pos > talla)
      return;
    res.add(elVector[pos]);
    preordenRec(2 * pos, res);
    preordenRec(2 * pos + 1, res);
  }

  /**
   * Recorrido en Inorden del montículo.
   * Complejidad Temporal: O(n)
   * 
   * @return lista con el recorrido.
   */
  public List<E> inorden() {
    List<E> res = new ArrayList<>();
    inordenRec(1, res);
    return res;
  }

  private void inordenRec(int pos, List<E> res) {
    if (pos > talla)
      return;
    inordenRec(2 * pos, res);
    res.add(elVector[pos]);
    inordenRec(2 * pos + 1, res);
  }

  /**
   * Recorrido en Postorden del montículo.
   * Complejidad Temporal: O(n)
   * 
   * @return lista con el recorrido.
   */
  public List<E> postorden() {
    List<E> res = new ArrayList<>();
    postordenRec(1, res);
    return res;
  }

  private void postordenRec(int pos, List<E> res) {
    if (pos > talla)
      return;
    postordenRec(2 * pos, res);
    postordenRec(2 * pos + 1, res);
    res.add(elVector[pos]);
  }

  /**
   * Recorrido en Anchura del montículo.
   * Complejidad Temporal: O(n)
   * 
   * @return lista con el recorrido.
   */
  public List<E> anchura() {
    List<E> res = new ArrayList<>();
    for (int i = 1; i <= talla; i++) {
      res.add(elVector[i]);
    }
    return res;
  }
}