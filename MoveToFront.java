import edu.princeton.cs.algs4.BinaryStdIn;
import edu.princeton.cs.algs4.BinaryStdOut;

public class MoveToFront {
    private static final int R = 256; // number of ASCII characters

    // apply move-to-front encoding, reading from stdin and writing to stdout
    public static void encode() {
        char[] arr = new char[R]; // ordered sequence of characters in alphabet
        for (char c = 0; c < R; c++) {
            arr[c] = c;
        }
        while (!BinaryStdIn.isEmpty()) {
            char c = BinaryStdIn.readChar(8);
            char temp = arr[0];

            // find the position of the character and move to front
            for (char i = 0; i < R; i++) {
                char temp2 = arr[i];
                if (temp2 == c) {
                    BinaryStdOut.write(i);
                    arr[i] = temp;
                    break;
                }
                else {
                    arr[i] = temp;
                    temp = temp2;
                }
            }
            arr[0] = c;

        }
        BinaryStdOut.close();
    }

    // apply move-to-front decoding, reading from stdin and writing to stdout
    public static void decode() {
        char[] arr = new char[R]; // ordered sequence of characters in alphabet
        for (char c = 0; c < R; c++) {
            arr[c] = c;
        }
        while (!BinaryStdIn.isEmpty()) {
            char c = BinaryStdIn.readChar(8);
            char temp = arr[c];
            // write cth character and move to front
            BinaryStdOut.write(temp);
            for (char j = c; j > 0; j--) {
                arr[j] = arr[j - 1];
            }
            arr[0] = temp;
        }

        BinaryStdOut.close();
    }

    // if args[0] is "-", apply move-to-front encoding
    // if args[0] is "+", apply move-to-front decoding
    public static void main(String[] args) {
        String arg = args[0];
        if (arg.equals("-")) {
            encode();
        }
        else {
            decode();
        }
    }
}
