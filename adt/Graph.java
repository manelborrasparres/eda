package adt;

import java.util.List;
import java.util.Map;

/**
 * Interfaz que define el Tipo Abstracto de Datos Grafo para nodos genéricos.
 * 
 * @param <V> tipo de dato de los vértices.
 */
public interface Graph<V> {

  /**
   * Añade un vértice al grafo de forma dinámica.
   * Complejidad Temporal: O(1) amortizado.
   * Complejidad Espacial: O(1) amortizado.
   * 
   * @param v vértice a añadir.
   */
  void addVertex(V v);

  /**
   * Añade una arista dirigida y ponderada entre dos vértices.
   * Complejidad Temporal: O(1).
   * Complejidad Espacial: O(1).
   * 
   * @param src    vértice origen.
   * @param dest   vértice destino.
   * @param weight peso de la arista.
   */
  void addEdge(V src, V dest, int weight);

  /**
   * Realiza un recorrido en anchura (BFS) desde el vértice indicado[cite: 27, 31,
   * 38].
   * Utilidad: Útil para encontrar el camino más corto en grafos no ponderados o
   * rastrear redes peer-to-peer.
   * Complejidad Temporal: O(V + E).
   * Complejidad Espacial: O(V).
   * 
   * @param start vértice de inicio.
   * @return lista de vértices en el orden visitado.
   */
  List<V> bfs(V start);

  /**
   * Realiza un recorrido en profundidad (DFS) desde el vértice indicado.
   * Utilidad: Útil para detectar ciclos, laberintos o análisis de dependencias
   * (ordenación topológica).
   * Complejidad Temporal: O(V + E).
   * Complejidad Espacial: O(V).
   * 
   * @param start vértice de inicio.
   * @return lista de vértices en el orden visitado.
   */
  List<V> dfs(V start);

  /**
   * Algoritmo de Dijkstra utilizando estructuras simples (búsqueda lineal) [cite:
   * 46-59].
   * Complejidad Temporal: O(V^2).
   * Complejidad Espacial: O(V).
   * 
   * @param start vértice de inicio.
   * @return mapa con las distancias mínimas a cada vértice.
   */
  Map<V, Integer> dijkstraSimple(V start);

  /**
   * Algoritmo de Dijkstra optimizado utilizando una Cola de Prioridad.
   * Complejidad Temporal: O(E log V).
   * Complejidad Espacial: O(V).
   * 
   * @param start vértice de inicio.
   * @return mapa con las distancias mínimas a cada vértice.
   */
  Map<V, Integer> dijkstraPQ(V start);
}