package exceptions;

/**
 * Excepción lanzada cuando se intenta acceder o modificar una posición no
 * válida
 * en una estructura de datos.
 */
public class WrongIndexException extends RuntimeException {

  /**
   * Construye una WrongIndexException sin mensaje de detalle.
   */
  public WrongIndexException() {
    super();
  }

  /**
   * Construye una WrongIndexException con el mensaje de detalle especificado.
   * * @param message el mensaje de detalle
   */
  public WrongIndexException(String message) {
    super(message);
  }
}