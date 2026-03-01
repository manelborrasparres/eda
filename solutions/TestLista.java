package solutions;

import adt.List;
import ds.ListImpl;
import exceptions.WrongIndexException;

/**
 * Clase ejecutable para probar la implementación del TAD Lista.
 */
public class TestLista {
    public static void main(String[] args) {
        // 1. Instanciar la lista (usando Genericidad para Strings)
        List<String> miLista = new ListImpl<>();

        try {
            System.out.println("--- Probando Inserciones ---");
            miLista.insert(0, "Primero");
            miLista.insert(1, "Tercero");
            miLista.insert(1, "Segundo"); // Insertar en medio
            
            System.out.println("Tamaño de la lista: " + miLista.size()); // Debería ser 3

            // 2. Probar el bucle for-each (requiere que sea Iterable)
            System.out.println("\n--- Contenido de la lista (Iterador) ---");
            for (String s : miLista) {
                System.out.println("- " + s);
            }

            // 3. Probar búsqueda
            System.out.println("\n--- Probando Búsqueda ---");
            int pos = miLista.search("Segundo");
            System.out.println("Posición de 'Segundo': " + pos); // Debería ser 1

            // 4. Probar borrado
            System.out.println("\n--- Probando Borrado ---");
            miLista.delete(0); // Borra "Primero"
            System.out.println("Tras borrar pos 0, el nuevo primero es: " + miLista.get(0));

            // 5. Probar manejo de excepciones (Forzar error)
            System.out.println("\n--- Probando Excepción (Fuera de rango) ---");
            miLista.get(10); 

        } catch (WrongIndexException e) {
            System.err.println("Capturada excepción esperada: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Error inesperado: " + e.getMessage());
        }
    }
}