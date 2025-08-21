package DataStructure.Stack;

import DataStructure.LinkedList.LinkedList;

/**
 * Estructura de tipo Pila (LIFO).
 * Permite apilar y desapilar elementos.
 */
public class Stack<E> {
    private LinkedList<E> list;

    public Stack() {
        list = new LinkedList<>(false, false); // lista simple
    }

    /** Inserta en la parte superior */
    public void push(E data) {
        list.insertAtFirstPosition(data);
    }

    /** Extrae el elemento superior */
    public E pop() {
        if (!isEmpty()) {
            return list.removeFirst();
        }
        throw new IllegalStateException(" La pila está vacía.");
    }

    /** Consulta el tope */
    public E peek() {
        if (isEmpty()) throw new IllegalStateException(" La pila está vacía.");
        return list.iterator().next();
    }

    /** Verifica si está vacía */
    public boolean isEmpty() {
        return !list.iterator().hasNext();
    }

    /** Muestra el contenido */
    public void show() {
        list.show();
    }
}
