import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Receiver receiver = new Receiver("Hamming");
        char[] dat = new char[0];
        BST bst = new BST(dat, 5);
        System.out.println("BSC/Hamming Code:");
        bst.test(dat, receiver, 10000);
        GilbertElliot gilbertElliot = new GilbertElliot(dat, 5, 10, 60);
        System.out.println("Gilbert-Elliot/Hamming Code:");
        gilbertElliot.test(dat, receiver, 10000);
    }
}
