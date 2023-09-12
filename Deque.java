/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import java.util.Iterator;

public class Deque<Item> implements Iterable<Item> {

    private Item[] deque; // The Deque array
    private int N = 0; // Number of items in the Deque

    // construct an empty deque
    public Deque() {
        deque = (Item[]) new Object[2];
    }

    // is the deque empty?
    public boolean isEmpty() {
        return N == 0;
    }

    // return the number of items on the deque
    public int size() {
        return N;
    }

    // add the item to the front
    public void addFirst(Item item) {
        if (item == null) throw new IllegalArgumentException();
        if (N == deque.length) resizeDeque(2 * deque.length);
        Item firstItem = deque[0];
        for (int i = 0; i < N; i++) {
            Item nextItem = deque[i + 1];
            deque[i + 1] = firstItem;
            firstItem = nextItem;
        }
        N++;
        deque[0] = item;
    }

    // add the item to the back
    public void addLast(Item item) {
        if (item == null) throw new IllegalArgumentException();
        if (N == deque.length) resizeDeque(2 * deque.length);
        deque[N++] = item;
    }

    // remove and return the item from the front
    public Item removeFirst() {
        if (isEmpty()) throw new java.util.NoSuchElementException();

        Item item = deque[0];
        for (int i = 0; i < N - 1; i++) {
            deque[i] = deque[i + 1];
        }

        N--;
        if (N > 0 && N <= deque.length / 4) resizeDeque(deque.length / 2);
        return item;
    }

    // remove and return the item from the back
    public Item removeLast() {
        if (isEmpty()) throw new java.util.NoSuchElementException();
        if (N > 0 && N <= deque.length / 4) resizeDeque(deque.length / 2);
        Item item = deque[--N];
        deque[N] = null;
        return item;
    }

    // return an iterator over items in order from front to back
    public Iterator<Item> iterator() {
        return new DequeIterator();
    }

    private class DequeIterator implements Iterator<Item> {

        private int i = 0;

        @Override
        public boolean hasNext() {
            return N > 0 && N != i;
        }

        @Override
        public Item next() {
            if (!hasNext()) throw new java.util.NoSuchElementException();
            return deque[i++];
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    private void resizeDeque(int capacity) {
        Item[] dequeCopy = (Item[]) new Object[capacity];
        for (int i = 0; i < N; i++) {
            dequeCopy[i] = deque[i];
        }
        deque = dequeCopy;
    }

    // unit testing (required)
    public static void main(String[] args) {
        Deque<String> deque1 = new Deque<>();

        deque1.addLast("Guilherme");
        deque1.addLast("Ines");
        deque1.addLast("Luna");
        deque1.addLast("Nero");

        for (String s : deque1) {
            System.out.print(s + " ");
        }
        System.out.println();

        deque1.removeLast();

        for (String s : deque1) {
            System.out.print(s + " ");
        }
    }
}