package solutions;

import adt.Dictionary;
import ds.HashTable;

/**
 * Clase ejecutable para probar el correcto funcionamiento de la implementación
 * de Dictionary.
 */
public class TestTabla {

  /**
   * Método principal que ejecuta las pruebas sobre HashTable.
   * * @param args argumentos de la línea de comandos
   */
  public static void main(String[] args) {
    Dictionary<String, Integer> dict = new HashTable<>(4);

    dict.put("Hola", 1);
    dict.put("Adios", 2);
    dict.put("Prueba", 3);
    dict.put("Programacion", 4);
    dict.put("EDA", 5);

    System.out.println("Valor de 'Prueba': " + dict.get("Prueba"));
    System.out.println("Tamaño: " + dict.size());

    dict.put("Prueba", 99);
    System.out.println("Nuevo valor de 'Prueba': " + dict.get("Prueba"));

    dict.remove("Adios");
    System.out.println("Contiene 'Adios'?: " + dict.contains("Adios"));

    System.out.println(dict.toString());

    System.out.println("Recorrido con iterador:");
    for (String key : dict) {
      System.out.println("Clave: " + key + " - Valor: " + dict.get(key));
    }

    dict.clear();
    System.out.println("Está vacío?: " + dict.isEmpty());
  }
}