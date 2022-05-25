public class Main {
    public static void main(String[] args) {

        Receiver receiver = new Receiver("Hamming");
        char[] dat = new char[0];

        BST bst = new BST(dat, 10);

        System.out.println("BSC/Hamming Code:");
        bst.test(dat, receiver, 10000);

        receiver.setTypeOfCode("crc");
        bst.test(dat, receiver, 10000);

        GilbertElliot gilbertElliot = new GilbertElliot(dat, 5, 10, 60);

        System.out.println("Gilbert-Elliot/Hamming Code:");
        gilbertElliot.test(dat, receiver, 10000);

        receiver.setTypeOfCode("parityBit");
        bst.test(dat, receiver, 10000);

        receiver.setTypeOfCode("doubledData");
        bst.test(dat, receiver, 10000);
    }
}
