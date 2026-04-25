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
 * Implementación de Grafo mediante Matriz de Adyacencia dinámica y genérica.
 * 
 * @param <V> tipo de dato de los vértices.
 */
public class GraphAdjMatrix<V> implements Graph<V> {

  private Map<V, Integer> vertexToId;
  private List<V> idToVertex;
  private int[][] matrix;
  private int numVertices;

  /**
   * Constructor por defecto.
   * Complejidad Temporal: O(1)
   */
  public GraphAdjMatrix() {
    vertexToId = new HashMap<>();
    idToVertex = new ArrayList<>();
    matrix = new int[10][10];
    initMatrix(matrix, 0, 10);
    numVertices = 0;
  }

  private void initMatrix(int[][] mat, int start, int end) {
    for (int i = start; i < end; i++) {
      for (int j = 0; j < end; j++) {
        mat[i][j] = Integer.MAX_VALUE;
      }
    }
  }

  private void expandMatrix() {
    int newSize = matrix.length * 2;
    int[][] newMatrix = new int[newSize][newSize];
    initMatrix(newMatrix, 0, newSize);
    for (int i = 0; i < matrix.length; i++) {
      System.arraycopy(matrix[i], 0, newMatrix[i], 0, matrix.length);
    }
    matrix = newMatrix;
  }

  @Override
  public void addVertex(V v) {
    if (!vertexToId.containsKey(v)) {
      if (numVertices == matrix.length) {
        expandMatrix();
      }
      vertexToId.put(v, numVertices);
      idToVertex.add(v);
      matrix[numVertices][numVertices] = 0;
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
    matrix[idSrc][idDest] = weight;
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

      for (int v = 0; v < numVertices; v++) {
        if (matrix[u][v] != Integer.MAX_VALUE && matrix[u][v] != 0 && !visited[v]) {
          visited[v] = true;
          queue.add(v);
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
        for (int v = numVertices - 1; v >= 0; v--) {
          if (matrix[u][v] != Integer.MAX_VALUE && matrix[u][v] != 0 && !visited[v]) {
            stack.push(v);
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

      for (int v = 0; v < numVertices; v++) {
        if (!visto[v] && matrix[u][v] != Integer.MAX_VALUE && matrix[u][v] != 0 && dist[u] != Integer.MAX_VALUE) {
          int alt = dist[u] + matrix[u][v];
          if (alt < dist[v]) {
            dist[v] = alt;
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

      for (int v = 0; v < numVertices; v++) {
        if (!visto.contains(v) && matrix[u][v] != Integer.MAX_VALUE && matrix[u][v] != 0) {
          int alt = dist[u] + matrix[u][v];
          if (alt < dist[v]) {
            dist[v] = alt;
            pq.add(new int[] { v, alt });
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