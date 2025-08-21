package DataStructure.Queue;

import DataStructure.LinkedList.LinkedList;

/**
 * Cola genérica (FIFO).
 * Inserta al final y extrae al inicio.
 */
public class Queue<E> {
    private LinkedList<E> list;

    public Queue() {
        list = new LinkedList<>(false, false); // lista simple
    }

    /** Agrega un nuevo elemento */
    public void enqueue(E data) {
        list.insertAtLastPosition(data);
    }

    /** Extrae el siguiente elemento */
    public E dequeue() {
        if (!isEmpty()) {
            return list.removeFirst();
        }
        throw new IllegalStateException(" La cola está vacía.");
    }

    /** Consulta el primero */
    public E peek() {
        if (isEmpty()) throw new IllegalStateException(" La cola está vacía.");
        return list.iterator().next();
    }

    /** Verifica si está vacía */
    public boolean isEmpty() {
        return !list.iterator().hasNext();
    }

    /** Muestra la cola */
    public void show() {
        list.show();
    }
}
