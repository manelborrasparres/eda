package solutions;

import adt.List;
import ds.ListImpl;
import exceptions.WrongIndexException;

/**
 * Clase ejecutable para probar el correcto funcionamiento de la implementación
 * de List.
 */
public class TestLista {

  /**
   * Método principal que ejecuta las pruebas sobre ListImpl.
   * * @param args argumentos de la línea de comandos
   */
  public static void main(String[] args) {
    List<Integer> lista = new ListImpl<>();

    lista.insert(0, 10);
    lista.insert(1, 20);
    lista.insert(1, 15);

    System.out.println("Elemento pos 0: " + lista.get(0));
    System.out.println("Elemento pos 1: " + lista.get(1));
    System.out.println("Elemento pos 2: " + lista.get(2));
    System.out.println("Tamaño: " + lista.size());

    lista.delete(1);
    System.out.println("Elemento pos 1 tras borrar: " + lista.get(1));

    System.out.println("Buscar 20: " + lista.search(20));
    System.out.println("Buscar 99: " + lista.search(99));

    System.out.println("Recorrido con iterador:");
    for (Integer num : lista) {
      System.out.println(num);
    }

    try {
      lista.get(10);
    } catch (WrongIndexException e) {
      System.out.println("Excepcion capturada correctamente");
    }
  }
}