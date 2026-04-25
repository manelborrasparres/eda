package solutions;

import adt.Stack;
import ds.StackSeq;

/**
 * Solución al problema de los paréntesis equilibrados usando una Pila.
 */
public class ParentesisEquilibrados {

  /**
   * Determina si una cadena de caracteres tiene los paréntesis equilibrados.
   * Complejidad Temporal: O(n) lineal respecto al tamaño de la cadena.
   * Complejidad Espacial: O(n) en el peor caso (todos son símbolos de apertura).
   * * @param expresion la cadena a analizar
   * 
   * @return true si están equilibrados, false en caso contrario
   */
  public static boolean estanEquilibrados(String expresion) {
    Stack<Character> pila = new StackSeq<>();

    for (int i = 0; i < expresion.length(); i++) {
      char c = expresion.charAt(i);

      if (c == '(' || c == '{' || c == '[') {
        pila.push(c);
      } else if (c == ')' || c == '}' || c == ']') {
        if (pila.isEmpty()) {
          return false; // Cierre sin apertura previa
        }
        char apertura = pila.pop();
        if (!coinciden(apertura, c)) {
          return false; // No hay correspondencia
        }
      }
    }

    // Si la pila está vacía al terminar, están equilibrados
    return pila.isEmpty();
  }

  /**
   * Método auxiliar para comprobar si un par de símbolos son correspondientes.
   * 
   * @param apertura símbolo de apertura
   * @param cierre   símbolo de cierre
   * @return true si corresponden, false en caso contrario
   */
  private static boolean coinciden(char apertura, char cierre) {
    return (apertura == '(' && cierre == ')') ||
        (apertura == '{' && cierre == '}') ||
        (apertura == '[' && cierre == ']');
  }

  /**
   * Método principal para ejecutar pruebas rápidas.
   * 
   * @param args argumentos
   */
  public static void main(String[] args) {
    String[] pruebas = {
        "(()()()())",
        "(((())))",
        "(()((())()))",
        "{[()]}",
        "((((((())",
        "()))",
        "(()()(()",
        "[(])"
    };

    for (String prueba : pruebas) {
      System.out.println(prueba + " -> " + (estanEquilibrados(prueba) ? "Equilibrado" : "No Equilibrado"));
    }
  }
}