package DataStructure.LinkedList;

import DataStructure.Node;

public class LinkedList<E> {

    private Node<E> primero;
    private Node<E> ultimo;
    private final boolean esDoble;
    private final boolean esCircular;

    public LinkedList(boolean esDoble, boolean esCircular) {
        this.esDoble = esDoble;
        this.esCircular = esCircular;
        primero = null;
        ultimo = null;
    }

    private boolean datosIguales(E a, E b) {
        if (a == b) return true;
        if (a == null || b == null) return false;
        if (a instanceof Contactos ca && b instanceof Contactos cb) {
            return ca.getNombre().trim().equalsIgnoreCase(cb.getNombre().trim());
        }
        return a.equals(b);
    }

    public void insertAtFirstPosition(E dato) {
        Node<E> nuevo = new Node<>(dato);

        if (primero == null) {
            primero = ultimo = nuevo;
            if (esCircular) {
                primero.setNext(primero);
                if (esDoble) primero.setPrev(primero);
            }
            return;
        }

        if (esCircular) {
            nuevo.setNext(primero);
            if (esDoble) {
                nuevo.setPrev(ultimo);
                primero.setPrev(nuevo);
                ultimo.setNext(nuevo);
            } else {
                ultimo.setNext(nuevo);
            }
            primero = nuevo;
        } else {
            nuevo.setNext(primero);
            if (esDoble) primero.setPrev(nuevo);
            primero = nuevo;
        }
    }

    public void insertAtLastPosition(E dato) {
        Node<E> nuevo = new Node<>(dato);

        if (primero == null) {
            primero = ultimo = nuevo;
            if (esCircular) {
                primero.setNext(primero);
                if (esDoble) primero.setPrev(primero);
            }
            return;
        }

        if (esCircular) {
            nuevo.setNext(primero);
            if (esDoble) {
                nuevo.setPrev(ultimo);
                ultimo.setNext(nuevo);
                primero.setPrev(nuevo);
            } else {
                ultimo.setNext(nuevo);
            }
            ultimo = nuevo;
        } else {
            ultimo.setNext(nuevo);
            if (esDoble) nuevo.setPrev(ultimo);
            ultimo = nuevo;
        }
    }

    public boolean remove(E dato) {
        if (primero == null) return false;

        Node<E> actual = primero;
        Node<E> anterior = null;

        do {
            if (datosIguales(actual.getData(), dato)) {
                if (actual == primero && actual == ultimo) {
                    primero = ultimo = null;
                    return true;
                }

                if (actual == primero) {
                    primero = primero.getNext();
                    if (esDoble && primero != null) primero.setPrev(esCircular ? ultimo : null);
                    if (esCircular && ultimo != null) ultimo.setNext(primero);
                    return true;
                }

                if (actual == ultimo) {
                    ultimo = anterior;
                    if (ultimo != null) ultimo.setNext(esCircular ? primero : null);
                    if (esDoble && primero != null) primero.setPrev(esCircular ? ultimo : null);
                    return true;
                }

                anterior.setNext(actual.getNext());
                if (esDoble && actual.getNext() != null) actual.getNext().setPrev(anterior);
                return true;
            }

            anterior = actual;
            actual = actual.getNext();
        } while ((esCircular && actual != primero) || (!esCircular && actual != null));

        return false;
    }

    public int indexOf(E dato) {
        if (primero == null) return -1;

        Node<E> actual = primero;
        int pos = 0;

        do {
            if (datosIguales(actual.getData(), dato)) return pos;
            actual = actual.getNext();
            pos++;
        } while ((esCircular && actual != primero) || (!esCircular && actual != null));

        return -1;
    }

    public void show() {
        if (primero == null) {
            System.out.println("La lista esta vacia.");
            return;
        }

        System.out.print("Elementos de la lista en orden: ");
        Node<E> actual = primero;

        if (esCircular) {
            do {
                System.out.print(actual.getData() + " -> ");
                actual = actual.getNext();
            } while (actual != primero);
            System.out.println("(regresa al inicio)");
        } else {
            while (actual != null) {
                System.out.print(actual.getData() + " -> ");
                actual = actual.getNext();
            }
            System.out.println("null");
        }

        if (esDoble) {
            System.out.print("Elementos en sentido inverso: ");
            actual = ultimo;

            if (esCircular) {
                do {
                    System.out.print(actual.getData() + " <- ");
                    actual = actual.getPrev();
                } while (actual != ultimo);
                System.out.println("(regresa al inicio)");
            } else {
                while (actual != null) {
                    System.out.print(actual.getData() + " <- ");
                    actual = actual.getPrev();
                }
                System.out.println("null");
            }
        }
    }
}
