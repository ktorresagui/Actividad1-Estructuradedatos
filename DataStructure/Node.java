package DataStructure;

/**
 * Nodo genérico que almacena un dato y referencias al siguiente y anterior nodo.
 * Se usa en listas simples, dobles o circulares.
 */
public class Node<E> {
    private E dato;
    private Node<E> siguiente;
    private Node<E> anterior;

    /**
     * Construye un nodo con el valor indicado.
     * Inicialmente no apunta a ningún otro nodo.
     */
    public Node(E dato) {
        this.dato = dato;
        this.siguiente = null;
        this.anterior = null;
    }

    public E getData() {
        return dato;
    }

    public void setData(E dato) {
        this.dato = dato;
    }

    public Node<E> getNext() {
        return siguiente;
    }

    public void setNext(Node<E> siguiente) {
        this.siguiente = siguiente;
    }

    public Node<E> getPrev() {
        return anterior;
    }

    public void setPrev(Node<E> anterior) {
        this.anterior = anterior;
    }
}
