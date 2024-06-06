package Genericas;
/**
 * Interfaz generica que determina el comportamiento de clases genericas
 * @param <T> tipo de dato
 */
public interface IGenerica<T> {
    /// agregar, contar, listar, eliminar

    /**
     * Agregar un elemento
     * @param elemento variable
     * @param <T> tipo de dato
     */
    <T> void agregarElemento(T elemento);

    /**
     * Eliminar un elemento
     * @param elemento variable
     * @param <T> tipo de dato
     */
    <T> void eliminarElemento(T elemento);

    /**
     * Contar elementos
     * @return cantidad de elementos
     */
    int contarElementos();

    /**
     * Muestra los elementos
     * @return texto con elementos
     */
    void listarElementos();
}