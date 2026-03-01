package solutions;

import adt.List;
import ds.ListImpl;
import exceptions.WrongIndexException;

public class TestLista {
    public static void main(String[] args) {
        List<String> miLista = new ListImpl<>();

        try {
            System.out.println("--- Probando Inserciones ---");
            miLista.insert(0, "Primero");
            miLista.insert(1, "Tercero");
            miLista.insert(1, "Segundo"); 
            
            System.out.println("Tamaño de la lista: " + miLista.size());

            System.out.println("\n--- Contenido de la lista (Iterador) ---");
            for (String s : miLista) {
                System.out.println("- " + s);
            }

            System.out.println("\n--- Probando Búsqueda ---");
            int pos = miLista.search("Segundo");
            System.out.println("Posición de 'Segundo': " + pos); 

            System.out.println("\n--- Probando Borrado ---");
            miLista.delete(0); 
            System.out.println("Tras borrar pos 0, el nuevo primero es: " + miLista.get(0));

            System.out.println("\n--- Probando Excepción (Fuera de rango) ---");
            miLista.get(10); 

        } catch (WrongIndexException e) {
            System.err.println("Capturada excepción esperada: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Error inesperado: " + e.getMessage());
        }
    }
}