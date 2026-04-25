package solutions;

import adt.Graph;
import ds.GraphAdjList;
import ds.GraphAdjMatrix;
import java.util.Map;

/**
 * Clase ejecutable para probar las funcionalidades de los grafos y
 * realizar la comparativa de rendimiento en el algoritmo de Dijkstra.
 */
public class TestGrafos {

  public static void main(String[] args) {
    System.out.println("--- PRUEBAS GRAFO MATRIZ DE ADYACENCIA ---");
    testGrafo(new GraphAdjMatrix<>());

    System.out.println("\n--- PRUEBAS GRAFO LISTA DE ADYACENCIA ---");
    testGrafo(new GraphAdjList<>());

    System.out.println("\n--- COMPARATIVA DE RENDIMIENTO DIJKSTRA ---");
    compararDijkstra();
  }

  private static void testGrafo(Graph<String> grafo) {
    // Replicando el ejemplo visual estándar de distancias
    grafo.addEdge("A", "B", 5);
    grafo.addEdge("A", "C", 2);
    grafo.addEdge("B", "D", 1);
    grafo.addEdge("C", "B", 1);
    grafo.addEdge("C", "E", 4);
    grafo.addEdge("D", "E", 1);

    System.out.println("Recorrido BFS desde A: " + grafo.bfs("A"));
    System.out.println("Recorrido DFS desde A: " + grafo.dfs("A"));

    Map<String, Integer> distancias = grafo.dijkstraPQ("A");
    System.out.println("Distancias mínimas desde A (Dijkstra):");
    for (Map.Entry<String, Integer> entry : distancias.entrySet()) {
      System.out.println("  " + entry.getKey() + ": " + entry.getValue());
    }
  }

  private static void compararDijkstra() {
    Graph<Integer> grafoList = new GraphAdjList<>();
    int numNodes = 2000;

    for (int i = 0; i < numNodes; i++) {
      grafoList.addVertex(i);
    }
    for (int i = 0; i < numNodes - 1; i++) {
      for (int j = i + 1; j < Math.min(numNodes, i + 10); j++) {
        grafoList.addEdge(i, j, (i + j) % 10 + 1);
      }
    }

    long startSimple = System.nanoTime();
    grafoList.dijkstraSimple(0);
    long timeSimple = System.nanoTime() - startSimple;

    long startPQ = System.nanoTime();
    grafoList.dijkstraPQ(0);
    long timePQ = System.nanoTime() - startPQ;

    System.out.println("Nodos: " + numNodes);
    System.out.println("Tiempo Dijkstra Simple (búsqueda lineal) [ns]: " + timeSimple);
    System.out.println("Tiempo Dijkstra Optimizado (Cola Prioridad) [ns]: " + timePQ);
    System.out.println("En grafos dispersos con muchos nodos, la Cola de Prioridad mejora notablemente el coste.");
  }
}