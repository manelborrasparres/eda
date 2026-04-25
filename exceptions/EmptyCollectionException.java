package exceptions;

/**
 * Excepción lanzada cuando se intenta acceder o extraer un elemento
 * de una estructura de datos que está vacía.
 */
public class EmptyCollectionException extends RuntimeException {
  /**
   * Construye una EmptyCollectionException con un mensaje por defecto.
   */
  public EmptyCollectionException() {
    super("La colección está vacía.");
  }
}