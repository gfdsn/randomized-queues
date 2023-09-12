/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.StdIn;

public class Permutation {
    public static void main(String[] args) {

        int k = Integer.parseInt(args[0]);
        RandomizedQueue<String> values = new RandomizedQueue<>();

        while (!StdIn.isEmpty()) {
            String value = StdIn.readString();
            values.enqueue(value);
        }

        for (int i = 0; i < k; i++) {
            System.out.println(values.dequeue());
        }

    }
}
