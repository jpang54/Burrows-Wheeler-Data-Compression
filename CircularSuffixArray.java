import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;

public class CircularSuffixArray {
    private int n; // length of string
    private int[] index; // original indices of sorted suffixes

    private class CircularSuffix implements Comparable<CircularSuffix> {
        /* @citation Adapted from: https://edstem.org/us/courses/7744/lessons/
        21598/slides/125314 Accessed 12/3/2021. */

        private final String text; // reference to given string
        private final int offset; // index where suffix starts

        // create circularsuffix from given string and starting index
        public CircularSuffix(String text, int offset) {
            this.text = text;
            this.offset = offset;
        }

        // return length of circularsuffix
        public int length() {
            return text.length();
        }

        // return starting index of circularsuffix in string
        public int getOffset() {
            return offset;
        }

        // return character at given index in circularsuffix
        public char charAt(int i) {
            return text.charAt((offset + i) % length()); // wraparound
        }

        // compares two circularsuffixes lexicographically
        public int compareTo(CircularSuffix that) {
            for (int i = 0; i < length(); i++) {
                int diff = Character.compare(charAt(i), that.charAt(i));
                // continue comparing if chars at i are the same in both
                if (diff != 0) {
                    return diff;
                }
            }
            return 0;
        }
    }

    // circular suffix array of s
    public CircularSuffixArray(String s) {
        if (s == null) {
            throw new IllegalArgumentException("String is null");
        }
        n = s.length();
        CircularSuffix[] arr = new CircularSuffix[n]; // all circular suffixes
        index = new int[n];

        // find all the circularsuffixes, then sort them
        for (int i = 0; i < n; i++) {
            arr[i] = new CircularSuffix(s, i);
        }
        Arrays.sort(arr);

        // find original indices of sorted suffixes
        for (int i = 0; i < n; i++) {
            index[i] = arr[i].getOffset();
        }

    }

    // length of s
    public int length() {
        return n;
    }

    // returns index of ith sorted suffix
    public int index(int i) {
        if (i < 0 || i >= n) {
            throw new IllegalArgumentException("i is outside prescribed range");
        }
        return index[i];
    }

    // unit testing (required)
    public static void main(String[] args) {
        /* @citation Adapted from: https://www.cs.princeton.edu/courses/archive
        /fall21/cos226/assignments/burrows/specification.php.
        Accessed 12/3/2021. */
        String s = "ABRACADABRA!";
        CircularSuffixArray csa = new CircularSuffixArray(s);
        StdOut.println(csa.length()); // 12
        for (int i = 0; i < csa.length(); i++) {
            StdOut.print(csa.index(i) + " "); // 11 10 7 0 3 5 8 1 4 6 9 2
        }
        StdOut.println();
    }
}
