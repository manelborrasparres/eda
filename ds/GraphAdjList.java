package ds;

import adt.Graph;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;

/**
 * Implementación de Grafo mediante Lista de Adyacencia dinámica y genérica.
 * 
 * @param <V> tipo de dato de los vértices.
 */
public class GraphAdjList<V> implements Graph<V> {

  private static class Edge {
    int destId;
    int weight;

    Edge(int destId, int weight) {
      this.destId = destId;
      this.weight = weight;
    }
  }

  private Map<V, Integer> vertexToId;
  private List<V> idToVertex;
  private List<List<Edge>> adjList;
  private int numVertices;

  /**
   * Constructor por defecto.
   * Complejidad Temporal: O(1)
   */
  public GraphAdjList() {
    vertexToId = new HashMap<>();
    idToVertex = new ArrayList<>();
    adjList = new ArrayList<>();
    numVertices = 0;
  }

  @Override
  public void addVertex(V v) {
    if (!vertexToId.containsKey(v)) {
      vertexToId.put(v, numVertices);
      idToVertex.add(v);
      adjList.add(new LinkedList<>());
      numVertices++;
    }
  }

  @Override
  public void addEdge(V src, V dest, int weight) {
    if (!vertexToId.containsKey(src))
      addVertex(src);
    if (!vertexToId.containsKey(dest))
      addVertex(dest);
    int idSrc = vertexToId.get(src);
    int idDest = vertexToId.get(dest);
    adjList.get(idSrc).add(new Edge(idDest, weight));
  }

  @Override
  public List<V> bfs(V start) {
    List<V> result = new ArrayList<>();
    if (!vertexToId.containsKey(start))
      return result;

    boolean[] visited = new boolean[numVertices];
    Queue<Integer> queue = new LinkedList<>();

    int startId = vertexToId.get(start);
    visited[startId] = true;
    queue.add(startId);

    while (!queue.isEmpty()) {
      int u = queue.poll();
      result.add(idToVertex.get(u));

      for (Edge e : adjList.get(u)) {
        if (!visited[e.destId]) {
          visited[e.destId] = true;
          queue.add(e.destId);
        }
      }
    }
    return result;
  }

  @Override
  public List<V> dfs(V start) {
    List<V> result = new ArrayList<>();
    if (!vertexToId.containsKey(start))
      return result;

    boolean[] visited = new boolean[numVertices];
    Stack<Integer> stack = new Stack<>();

    stack.push(vertexToId.get(start));

    while (!stack.isEmpty()) {
      int u = stack.pop();
      if (!visited[u]) {
        visited[u] = true;
        result.add(idToVertex.get(u));
        for (Edge e : adjList.get(u)) {
          if (!visited[e.destId]) {
            stack.push(e.destId);
          }
        }
      }
    }
    return result;
  }

  @Override
  public Map<V, Integer> dijkstraSimple(V start) {
    Map<V, Integer> distances = new HashMap<>();
    if (!vertexToId.containsKey(start))
      return distances;

    int[] dist = new int[numVertices];
    boolean[] visto = new boolean[numVertices];

    for (int i = 0; i < numVertices; i++) {
      dist[i] = Integer.MAX_VALUE;
    }

    int startId = vertexToId.get(start);
    dist[startId] = 0;

    for (int i = 0; i < numVertices; i++) {
      int u = -1;
      int minDist = Integer.MAX_VALUE;
      for (int j = 0; j < numVertices; j++) {
        if (!visto[j] && dist[j] < minDist) {
          minDist = dist[j];
          u = j;
        }
      }

      if (u == -1)
        break;
      visto[u] = true;

      for (Edge e : adjList.get(u)) {
        if (!visto[e.destId] && dist[u] != Integer.MAX_VALUE) {
          int alt = dist[u] + e.weight;
          if (alt < dist[e.destId]) {
            dist[e.destId] = alt;
          }
        }
      }
    }

    for (int i = 0; i < numVertices; i++) {
      distances.put(idToVertex.get(i), dist[i]);
    }
    return distances;
  }

  @Override
  public Map<V, Integer> dijkstraPQ(V start) {
    Map<V, Integer> distances = new HashMap<>();
    if (!vertexToId.containsKey(start))
      return distances;

    int[] dist = new int[numVertices];
    for (int i = 0; i < numVertices; i++)
      dist[i] = Integer.MAX_VALUE;

    int startId = vertexToId.get(start);
    dist[startId] = 0;

    PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));
    pq.add(new int[] { startId, 0 });
    Set<Integer> visto = new HashSet<>();

    while (!pq.isEmpty()) {
      int[] current = pq.poll();
      int u = current[0];

      if (visto.contains(u))
        continue;
      visto.add(u);

      for (Edge e : adjList.get(u)) {
        if (!visto.contains(e.destId)) {
          int alt = dist[u] + e.weight;
          if (alt < dist[e.destId]) {
            dist[e.destId] = alt;
            pq.add(new int[] { e.destId, alt });
          }
        }
      }
    }

    for (int i = 0; i < numVertices; i++) {
      distances.put(idToVertex.get(i), dist[i]);
    }
    return distances;
  }
}