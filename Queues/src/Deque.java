import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;


public class Deque<Item> implements Iterable<Item> {
    private Node<Item> first, last;

    private static class Node<Item> {
        Item item;
        Node<Item> previous;
        Node<Item> next;
    }

    // construct an empty deque
    public Deque() {
        first = new Node<>();
        last = new Node<>();
    }

    // is the deque empty?
    public boolean isEmpty() {
        return first == null;
    }

    // return the number of items on the deque
    public int size() {
        int size = 0;

        while (iterator().hasNext()) {
            iterator().next();
            size++;
        }

        return size;
    }

    // add the item to the front
    public void addFirst(Item item) {
        Node<Item> oldfisrt = first;
        first = new Node<>();
        first.item = item;
        if (isEmpty()) first = last;
        else {
            first.next = oldfisrt;
            oldfisrt.previous = first;
        }
    }

    // add the item to the back
    public void addLast(Item item) {
        Node<Item> oldlast = last;
        last = new Node<>();
        last.item = item;
        if (isEmpty()) last = null;
        else {
            oldlast.next = last;
            last.next = null;
            last.previous = oldlast;
        }
    }

    // remove and return the item from the front
    public Item removeFirst() {
        Item item = first.item;
        first = first.next;
        return item;
    }

    // remove and return the item from the back
    public Item removeLast() {
        Item item = last.item;
        last = last.previous;
        return item;
    }

    // return an iterator over items in order from front to back
    public Iterator<Item> iterator() {
        return new DequeIterator();
    }

    private class DequeIterator implements Iterator<Item> {
        private Node<Item> current = first;

        public boolean hasNext() {
            return current != null;
        }

        public void remove() {
        }

        public Item next() {
            Item item = current.item;
            current = current.next;
            return item;
        }
    }

    // unit testing (required)
    public static void main(String[] args) {
        Deque<Integer> deque = new Deque<Integer>();

        for (int i = 0; i < 3; i++) {
            deque.addFirst(StdIn.readInt());
        }
        for (int i = 0; i < 3; i++) {
            deque.addLast(StdIn.readInt());
        }
        for (int i = 0; i < 3; i++) {
            StdOut.println(deque.removeFirst());
            StdOut.println(deque.removeLast());
        }
    }

}
