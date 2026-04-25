package ds;

import adt.Espacio3D;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Implementación de Espacio3D optimizada para coste espacial mínimo y tiempo
 * constante.
 * 
 * @param <E> el tipo de dato de interés
 */
public class Espacio3DImpl<E> implements Espacio3D<E> {

  /**
   * Clase interna para representar una coordenada 3D y actuar como clave.
   */
  private static class Coordenada {
    int x, y, z;

    Coordenada(int x, int y, int z) {
      this.x = x;
      this.y = y;
      this.z = z;
    }

    @Override
    public boolean equals(Object obj) {
      if (this == obj)
        return true;
      if (!(obj instanceof Coordenada))
        return false;
      Coordenada c = (Coordenada) obj;
      return x == c.x && y == c.y && z == c.z;
    }

    @Override
    public int hashCode() {
      int hash = 7;
      hash = 31 * hash + x;
      hash = 31 * hash + y;
      hash = 31 * hash + z;
      return hash;
    }
  }

  private HashTable<Coordenada, E> tabla;

  /**
   * Constructor por defecto.
   */
  public Espacio3DImpl() {
    this.tabla = new HashTable<>();
  }

  @Override
  public void insertar(int x, int y, int z, E dato) {
    tabla.put(new Coordenada(x, y, z), dato);
  }

  @Override
  public E recuperar(int x, int y, int z) {
    return tabla.get(new Coordenada(x, y, z));
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("Espacio3D:\n");
    for (Coordenada coord : tabla) {
      sb.append(String.format("  (%d, %d, %d) -> %s\n", coord.x, coord.y, coord.z, tabla.get(coord)));
    }
    return sb.toString();
  }

  @Override
  public Iterator<E> iterator() {
    return new Iterator<E>() {
      private Iterator<Coordenada> keyIterator = tabla.iterator();

      @Override
      public boolean hasNext() {
        return keyIterator.hasNext();
      }

      @Override
      public E next() {
        if (!hasNext())
          throw new NoSuchElementException();
        return tabla.get(keyIterator.next());
      }
    };
  }
}