package solutions;

import adt.Stack;
import adt.List;
import ds.StackImpl;
import ds.ListImpl;
import exceptions.EmptyCollectionException;
import exceptions.WrongIndexException;

public class TestEstructuras {
    public static void main(String[] args) {
        testPila();
        System.out.println("\n" + "=".repeat(30) + "\n");
        testLista();
    }

    private static void testPila() {
        System.out.println("--- PRUEBAS DE PILA (Representación Secuencial) ---");
        Stack<Integer> pila = new StackImpl<>();

        try {
            // Probar crecimiento dinámico (Insertar más de 10 elementos)
            System.out.println("Insertando 15 elementos para probar crecimiento dinámico...");
            for (int i = 1; i <= 15; i++) {
                pila.push(i);
            }
            System.out.println("Tamaño tras inserciones: " + pila.size());

            System.out.println("Elemento en la cima (peek): " + pila.peek());

            // Probar iterador (for-each)
            System.out.print("Recorrido for-each: ");
            for (Integer num : pila) {
                System.out.print(num + " ");
            }
            System.out.println();

            System.out.println("Extrayendo (pop): " + pila.pop());
            System.out.println("Nuevo tamaño: " + pila.size());

            // Probar excepción de pila vacía
            System.out.println("Vaciando pila para forzar error...");
            while (!pila.isEmpty()) { pila.pop(); }
            pila.pop(); 

        } catch (EmptyCollectionException e) {
            System.out.println("Capturada excepción correcta: " + e.getMessage());
        }
    }

    private static void testLista() {
        System.out.println("--- PRUEBAS DE LISTA (Representación Enlazada con Dummy) ---");
        List<String> lista = new ListImpl<>();

        try {
            lista.insert(0, "Nodo A");
            lista.insert(1, "Nodo C");
            lista.insert(1, "Nodo B");

            System.out.print("Contenido lista: ");
            for (String s : lista) {
                System.out.print("[" + s + "] ");
            }
            System.out.println();

            System.out.println("Buscando 'Nodo B': Posición " + lista.search("Nodo B"));

            lista.delete(1); 
            System.out.println("Tras borrar pos 1, el elemento en pos 1 es: " + lista.get(1)); 


        } catch (WrongIndexException e) {
            System.out.println("Capturada excepción correcta: " + e.getMessage());
        }
    }
}