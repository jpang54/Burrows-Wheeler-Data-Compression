import edu.princeton.cs.algs4.BinaryStdIn;
import edu.princeton.cs.algs4.BinaryStdOut;

public class BurrowsWheeler {
    private static final int R = 256; // number of ASCII characters

    // apply Burrows-Wheeler transform,
    // reading from standard input and writing to standard output
    public static void transform() {
        String s = BinaryStdIn.readString();
        CircularSuffixArray csa = new CircularSuffixArray(s);
        int n = s.length();

        // print out index of original circularsuffix in sorted array
        for (int i = 0; i < n; i++) {
            if (0 == csa.index(i)) {
                BinaryStdOut.write(i);
                break;
            }
        }

        // print out last column of sorted circularsuffix array
        for (int i = 0; i < n; i++) {
            int index = csa.index(i) - 1;
            if (index < 0) index = n - 1; // wraparound
            BinaryStdOut.write(s.charAt(index));
        }
        BinaryStdOut.close();
    }

    // apply Burrows-Wheeler inverse transform,
    // reading from standard input and writing to standard output
    public static void inverseTransform() {
        // index of original circularsuffix in sorted array
        int first = BinaryStdIn.readInt();
        String s = BinaryStdIn.readString(); // string to decode
        int n = s.length();
        int[] next = new int[n];
        char[] sorted = new char[n];

        /* @citation Adapted from: https://www.cs.princeton.edu/courses/
        archive/fall21/cos226/lectures/51StringSorts.pdf. Accessed 12/3/2021.*/
        int[] count = new int[R + 1]; // key indexed counting
        for (int i = 0; i < n; i++)
            count[s.charAt(i) + 1]++; // count frequencies
        for (int r = 0; r < R; r++)
            count[r + 1] += count[r]; // compute cumulative frequencies
        // update sorted array and next[]
        for (int i = 0; i < n; i++) {
            sorted[count[s.charAt(i)]] = s.charAt(i);
            next[count[s.charAt(i)]++] = i;
        }

        // print decoded string
        for (int i = 0; i < n; i++) {
            BinaryStdOut.write(sorted[first]);
            first = next[first];
        }
        BinaryStdOut.close();
    }

    // if args[0] is "-", apply Burrows-Wheeler transform
    // if args[0] is "+", apply Burrows-Wheeler inverse transform
    public static void main(String[] args) {
        String arg = args[0];
        if (arg.equals("-")) {
            transform();
        }
        else {
            inverseTransform();
        }
    }
}
