package exceptions;

/**
 * Excepción lanzada cuando se intenta realizar una operación de extracción
 * en una colección que no contiene elementos.
 */
public class EmptyCollectionException extends Exception {
    public EmptyCollectionException(String message) {
        super(message);
    }
}