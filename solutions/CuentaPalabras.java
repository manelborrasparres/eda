package solutions;

import adt.Dictionary;
import ds.HashTable;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Clase ejecutable que analiza un fichero y cuenta las ocurrencias de cada
 * palabra,
 * indicando la línea y columna correspondientes.
 */
public class CuentaPalabras {

  /**
   * Método principal que ejecuta el conteo de palabras.
   * * @param args argumentos de la línea de comandos (el primero puede ser el
   * nombre del archivo)
   */
  public static void main(String[] args) {
    String filename = args.length > 0 ? args[0] : "texto.txt";
    Dictionary<String, StringBuilder> wordOccurrences = new HashTable<>();

    try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
      String line;
      int lineNumber = 1;

      while ((line = br.readLine()) != null) {
        int colNumber = 1;
        StringBuilder currentWord = new StringBuilder();

        for (int i = 0; i < line.length(); i++) {
          char c = line.charAt(i);
          if (Character.isLetterOrDigit(c)) {
            currentWord.append(c);
          } else {
            if (currentWord.length() > 0) {
              addOccurrence(wordOccurrences, currentWord.toString().toLowerCase(), lineNumber,
                  colNumber - currentWord.length());
              currentWord.setLength(0);
            }
          }
          colNumber++;
        }

        if (currentWord.length() > 0) {
          addOccurrence(wordOccurrences, currentWord.toString().toLowerCase(), lineNumber,
              colNumber - currentWord.length());
        }
        lineNumber++;
      }
    } catch (IOException e) {
      System.out.println("No se pudo leer el archivo: " + filename);
      return;
    }

    for (String word : wordOccurrences) {
      System.out.println(word + ": [" + wordOccurrences.get(word).toString() + "]");
    }
  }

  /**
   * Añade la ocurrencia de una palabra al diccionario.
   * * @param dict el diccionario donde se almacenan las ocurrencias
   * 
   * @param word la palabra encontrada
   * @param line la línea donde se encontró la palabra
   * @param col  la columna donde comienza la palabra
   */
  private static void addOccurrence(Dictionary<String, StringBuilder> dict, String word, int line, int col) {
    String loc = "(" + line + ":" + col + ")";
    StringBuilder occurrences = dict.get(word);

    if (occurrences != null) {
      occurrences.append(", ").append(loc);
    } else {
      dict.put(word, new StringBuilder(loc));
    }
  }
}