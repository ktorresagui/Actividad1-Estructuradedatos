package DataStructure.LinkedList;

import DataStructure.Node;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Lista enlazada genérica con soporte para:
 *  - Simple
 *  - Doble
 *  - Circular
 *
 * @param <E> tipo de dato a almacenar
 */
public class LinkedList<E> implements Iterable<E> {

    private Node<E> first;
    private Node<E> last;
    private boolean doubly;
    private boolean circular;

    public LinkedList(boolean doubly, boolean circular) {
        this.doubly = doubly;
        this.circular = circular;
        this.first = null;
        this.last = null;
    }

    public void insertAtFirstPosition(E data) {
        Node<E> newNode = new Node<>(data);
        if (first == null) {
            first = last = newNode;
            if (circular) {
                first.setNext(first);
                if (doubly) first.setPrev(first);
            }
            return;
        }

        newNode.setNext(first);
        if (doubly) {
            newNode.setPrev(circular ? last : null);
            first.setPrev(newNode);
        }
        if (circular) {
            last.setNext(newNode);
        }
        first = newNode;
    }

    public void insertAtLastPosition(E data) {
        Node<E> newNode = new Node<>(data);
        if (first == null) {
            first = last = newNode;
            if (circular) {
                first.setNext(first);
                if (doubly) first.setPrev(first);
            }
            return;
        }

        newNode.setPrev(doubly ? last : null);
        if (circular) {
            newNode.setNext(first);
            last.setNext(newNode);
            if (doubly) first.setPrev(newNode);
        } else {
            last.setNext(newNode);
        }
        last = newNode;
    }

    public boolean remove(E data) {
        if (first == null) return false;
        Node<E> current = first;
        Node<E> prevNode = null;

        do {
            if ((current.getData() == null && data == null) ||
                (current.getData() != null && current.getData().equals(data))) {

                if (current == first && current == last) {
                    first = last = null;
                    return true;
                }

                if (current == first) {
                    first = first.getNext();
                    if (doubly && first != null) first.setPrev(circular ? last : null);
                    if (circular && last != null) last.setNext(first);
                    return true;
                }

                if (current == last) {
                    last = prevNode;
                    if (last != null) {
                        last.setNext(circular ? first : null);
                        if (doubly && circular) first.setPrev(last);
                    }
                    return true;
                }

                prevNode.setNext(current.getNext());
                if (doubly && current.getNext() != null) current.getNext().setPrev(prevNode);
                return true;
            }

            prevNode = current;
            current = current.getNext();
        } while ((circular && current != first) || (!circular && current != null));

        return false;
    }

    public E removeFirst() {
        if (first == null) throw new IllegalStateException("La lista está vacía");
        E data = first.getData();
        if (first == last) {
            first = last = null;
        } else {
            first = first.getNext();
            if (doubly) first.setPrev(circular ? last : null);
            if (circular) last.setNext(first);
        }
        return data;
    }

    public E removeLast() {
        if (last == null) throw new IllegalStateException("La lista está vacía");
        E data = last.getData();
        if (first == last) {
            first = last = null;
        } else {
            last = last.getPrev();
            if (last != null) {
                last.setNext(circular ? first : null);
            }
            if (doubly && circular) first.setPrev(last);
        }
        return data;
    }

    public boolean contains(E data) {
        return indexOf(data) != -1;
    }

    public int indexOf(E data) {
        if (first == null) return -1;
        Node<E> current = first;
        int index = 0;
        do {
            if ((current.getData() == null && data == null) ||
                (current.getData() != null && current.getData().equals(data))) return index;
            current = current.getNext();
            index++;
        } while ((circular && current != first) || (!circular && current != null));
        return -1;
    }

    public void show() {
        if (first == null) {
            System.out.println("Lista vacía");
            return;
        }

        System.out.print("De inicio a fin: ");
        Node<E> current = first;
        if (circular) {
            do {
                System.out.print(current.getData() + " -> ");
                current = current.getNext();
            } while (current != first);
            System.out.println("(retorno al inicio)");
        } else {
            while (current != null) {
                System.out.print(current.getData() + " -> ");
                current = current.getNext();
            }
            System.out.println("null");
        }

        if (doubly) {
            System.out.print("De fin a inicio: ");
            current = last;
            if (circular) {
                do {
                    System.out.print(current.getData() + " <- ");
                    current = current.getPrev();
                } while (current != last);
                System.out.println("(retorno al final)");
            } else {
                while (current != null) {
                    System.out.print(current.getData() + " <- ");
                    current = current.getPrev();
                }
                System.out.println("null");
            }
        }
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            private Node<E> current = first;
            private boolean firstTime = true;

            @Override
            public boolean hasNext() {
                if (current == null) return false;
                return circular ? firstTime || current != first : current != null;
            }

            @Override
            public E next() {
                if (!hasNext()) throw new NoSuchElementException();
                E data = current.getData();
                current = current.getNext();
                firstTime = false;
                return data;
            }
        };
    }
}
