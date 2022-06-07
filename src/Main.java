public class Main {
    public static void main(String[] args) {

        char[] dat = new char[0];

        BST bst = new BST(dat, 20);

        System.out.println("BSC/Hamming Code:");
        Receiver receiver = new Receiver(CorrectingCode.hamming);
        bst.test(dat, receiver, 10000);

        System.out.println("BSC/CRC code:");
        receiver.setTypeOfCode(CorrectingCode.crc);
        bst.test(dat, receiver, 10000);


        System.out.println("BSC/Parity bit:");
        receiver.setTypeOfCode(CorrectingCode.parityBit);
        bst.test(dat, receiver, 10000);

        System.out.println("BSC/Doubled data:");
        receiver.setTypeOfCode(CorrectingCode.doubledData);
        bst.test(dat, receiver, 10000);


        GilbertElliot gilbertElliot = new GilbertElliot(dat, 10, 20, 40);

        System.out.println("Gilbert-Elliot/Hamming Code:");
        receiver.setTypeOfCode(CorrectingCode.hamming);
        gilbertElliot.test(dat, receiver, 10000);

        System.out.println("Gilbert-Elliot/Parity bit:");
        receiver.setTypeOfCode(CorrectingCode.parityBit);
        gilbertElliot.test(dat, receiver, 10000);

        System.out.println("Gilbert-Elliot/Doubled data:");
        receiver.setTypeOfCode(CorrectingCode.doubledData);
        gilbertElliot.test(dat, receiver, 10000);

        System.out.println("Gilbert-Elliot/Crc code:");
        receiver.setTypeOfCode(CorrectingCode.crc);
        gilbertElliot.test(dat, receiver, 10000);


    }
}
