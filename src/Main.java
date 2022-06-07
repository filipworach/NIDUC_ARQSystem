public class Main {
    public static void main(String[] args) {

        char[] dat = new char[0];

        BST bst = new BST(dat, 1);
        Receiver receiver = new Receiver("");

        System.out.println("BSC/CRC code:");
        receiver.setTypeOfCode("crc");
        bst.test(dat, receiver, 10000);

        System.out.println("BSC/Hamming Code:");
        receiver.setTypeOfCode("Hamming");
        bst.test(dat, receiver, 10000);



        System.out.println("BSC/Parity bit:");
        receiver.setTypeOfCode("parityBit");
        bst.test(dat, receiver, 10000);

        System.out.println("BSC/Doubled data:");
        receiver.setTypeOfCode("doubledData");
        bst.test(dat, receiver, 10000);


        GilbertElliot gilbertElliot = new GilbertElliot(dat, 10, 20, 40);

        System.out.println("Gilbert-Elliot/Hamming Code:");
        receiver.setTypeOfCode("Hamming");
        gilbertElliot.test(dat, receiver, 10000);

        System.out.println("Gilbert-Elliot/Parity bit:");
        receiver.setTypeOfCode("parityBit");
        gilbertElliot.test(dat, receiver, 10000);

        System.out.println("Gilbert-Elliot/Doubled data:");
        receiver.setTypeOfCode("doubledData");
        gilbertElliot.test(dat, receiver, 10000);

        System.out.println("Gilbert-Elliot/Crc code:");
        receiver.setTypeOfCode("crc");
        gilbertElliot.test(dat, receiver, 10000);


    }
}
