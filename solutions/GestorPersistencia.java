package solutions;

import ds.HashTable;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * Clase de utilidad para guardar y cargar instancias de HashTable en ficheros.
 */
public class GestorPersistencia {

  /**
   * Guarda una HashTable en un fichero.
   * Complejidad Temporal: O(n) donde n es el tamaño de la tabla
   * 
   * @param table    la tabla a guardar
   * @param filename el nombre o ruta del fichero
   * @param <K>      tipo de clave
   * @param <V>      tipo de valor
   * @throws IOException si hay un error de escritura
   */
  public static <K, V> void guardarHashTable(HashTable<K, V> table, String filename) throws IOException {
    try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
      oos.writeObject(table);
    }
  }

  /**
   * Carga una HashTable desde un fichero.
   * Complejidad Temporal: O(n) donde n es el tamaño de la tabla almacenada
   * 
   * @param filename el nombre o ruta del fichero
   * @param <K>      tipo de clave
   * @param <V>      tipo de valor
   * @return la HashTable reconstruida y totalmente funcional
   * @throws IOException            si hay un error de lectura
   * @throws ClassNotFoundException si no se reconoce la estructura del objeto
   */
  @SuppressWarnings("unchecked")
  public static <K, V> HashTable<K, V> cargarHashTable(String filename) throws IOException, ClassNotFoundException {
    try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
      return (HashTable<K, V>) ois.readObject();
    }
  }
}