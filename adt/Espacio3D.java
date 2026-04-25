package adt;

/**
 * Interfaz genérica e iterable para operar con información en coordenadas 3D.
 * 
 * @param <E> el tipo de dato de interés
 */
public interface Espacio3D<E> extends Iterable<E> {

  /**
   * Inserta un dato de interés en una coordenada (x, y, z).
   * Complejidad Temporal: O(1)
   * Complejidad Espacial: O(1) por elemento
   * 
   * @param x    coordenada x
   * @param y    coordenada y
   * @param z    coordenada z
   * @param dato el dato a almacenar
   */
  void insertar(int x, int y, int z, E dato);

  /**
   * Recupera el dato de interés guardado en una coordenada.
   * Complejidad Temporal: O(1)
   * 
   * @param x coordenada x
   * @param y coordenada y
   * @param z coordenada z
   * @return el dato, o null si no existe
   */
  E recuperar(int x, int y, int z);
}