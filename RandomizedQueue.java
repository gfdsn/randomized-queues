/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {

    private Item[] randomizedQueue;
    private int N = 0;

    // construct an empty randomized queue
    public RandomizedQueue() {
        randomizedQueue = (Item[]) new Object[2];
    }

    // is the randomized queue empty?
    public boolean isEmpty() {
        return N == 0;
    }

    // return the number of items on the randomized queue
    public int size() {
        return N;
    }

    // add the item
    public void enqueue(Item item) {
        if (item == null) throw new IllegalArgumentException();
        randomizedQueue[N++] = item;
        if (N == randomizedQueue.length) resizeDeque(randomizedQueue.length * 2);
    }

    // remove and return a random item
    public Item dequeue() {
        if (isEmpty()) throw new NoSuchElementException();
        int randomPosition = (int) Math.floor(Math.random() * (N - 1));
        Item item = randomizedQueue[randomPosition];
        randomizedQueue[randomPosition] = null;

        for (int i = 0; i < N; i++) {
            if (randomizedQueue[i] == null) {
                Item nullItem = randomizedQueue[i];
                randomizedQueue[i] = randomizedQueue[N - 1];
                randomizedQueue[N - 1] = nullItem;
                break;
            }
        }
        N--;
        if (N > 0 && N <= randomizedQueue.length / 4) resizeDeque(randomizedQueue.length / 2);
        return item;
    }

    // return a random item (but do not remove it)
    public Item sample() {
        if (isEmpty()) throw new NoSuchElementException();
        int randomPosition = (int) Math.floor(Math.random() * (N - 1));
        return randomizedQueue[randomPosition];
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        return new RandomizedQueueIterator();
    }

    private class RandomizedQueueIterator implements Iterator<Item> {

        private int i = 0;
        private Item[] randomizedQueueCopy = (Item[]) new Object[N];

        public RandomizedQueueIterator() {
            for (int j = 0; j < randomizedQueue.length; j++) {
                if (randomizedQueue[j] != null)
                    randomizedQueueCopy[j] = randomizedQueue[j];
            }
            StdRandom.shuffle(randomizedQueueCopy);
        }

        @Override
        public boolean hasNext() {
            return N > 0 && N != i;
        }

        @Override
        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            return randomizedQueueCopy[i++];
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    private void resizeDeque(int capacity) {
        Item[] queueCopy = (Item[]) new Object[capacity];
        for (int i = 0; i < N; i++) {
            queueCopy[i] = randomizedQueue[i];
        }
        randomizedQueue = queueCopy;
    }

    // unit testing (required)
    public static void main(String[] args) {

        RandomizedQueue<String> queue = new RandomizedQueue<>();

        queue.enqueue("Ines");
        queue.enqueue("Guilherme");
        queue.enqueue("Nero");

        for (String s : queue)
            System.out.println(s);

        // System.out.println();
        // System.out.println(queue.sample());
        //
        // System.out.println();
        // System.out.println("Removed: " + queue.dequeue());
        //
        // System.out.println();
        // for (String s : queue)
        //     System.out.println(s);
        //
        // System.out.println();
        // System.out.println("Removed: " + queue.dequeue());
        //
        // System.out.println();
        // for (String s : queue)
        //     System.out.println(s);


    }
}
