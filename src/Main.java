import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int howManyCorrectMessages = 0;
        int howManyCorrectedMessages = 0;
        int howManyWrongMessages = 0;
        Receiver receiver = new Receiver("Hamming");
        //char [] dat = {'1','1','1','0','0','1','0','0','1','1','0','1','0','1','0','1','1','0','1','0','1','0','1','0','1','1'};
//        char [] dat = Integer.toBinaryString((int) Math.floor(Math.random()*67108864)).toCharArray();
//        dat = Calculator.writeOnChosenPositions(dat, 26);
        char[] dat = new char[0];
        BST bst = new BST(dat, 5);
//        bst.generateHammingCode();
//        dat = Arrays.copyOf(bst.getData(), 31);
//        System.out.println(dat);
//        bst.generateError();
//        receiver.setMessage(bst.getData());
//        receiver.decode();
//        System.out.println(receiver.getMessage());
//        if(Arrays.equals(receiver.getMessage(), dat))
//            System.out.println("Same");
//        else System.out.println("rozne");

        System.out.println("BSC/Hamming Code:");
//        for (int i = 0; i < 10000; i++) {
//            dat = Integer.toBinaryString((int) Math.floor(Math.random() * 67108864)).toCharArray();
//            dat = Calculator.writeOnChosenPositions(dat, 26);
//            bst.setData(dat);
//            bst.generateHammingCode();
//            dat = Arrays.copyOf(bst.getData(), 31);
//            bst.generateError();
//            receiver.setMessage(Arrays.copyOf(bst.getData(), 31));
//            boolean isError = receiver.decode();
//            if (bst.isErrorGenerated && Arrays.equals(dat, receiver.getMessage())) howManyCorrectedMessages++;
//            else if (bst.isErrorGenerated) howManyWrongMessages++;
//            else if (!bst.isErrorGenerated) howManyCorrectMessages++;
//        }
//        System.out.println("Corrected: " + howManyCorrectedMessages);
//        System.out.println("Wrong:" + howManyWrongMessages);
//        System.out.println("Correct: " + howManyCorrectMessages);
//
//        howManyCorrectedMessages = 0;
//        howManyCorrectMessages = 0;
//        howManyWrongMessages = 0;

        bst.test(dat, receiver, 10000);
        GilbertElliot gilbertElliot = new GilbertElliot(dat, 5, 10, 60);
        System.out.println("Gilbert-Elliot/Hamming Code:");
//        for (int i = 0; i < 10000; i++) {
//            dat = Integer.toBinaryString((int) Math.floor(Math.random() * 67108864)).toCharArray();
//            dat = Calculator.writeOnChosenPositions(dat, 26);
//            gilbertElliot.setData(dat);
//            gilbertElliot.generateHammingCode();
//            dat = gilbertElliot.getData();
//            gilbertElliot.generateError();
//            receiver.setMessage(Arrays.copyOf(dat, 31));
//            receiver.decode();
//            if (gilbertElliot.isErrorGenerated && Arrays.equals(dat, receiver.getMessage())) howManyCorrectedMessages++;
//            else if (gilbertElliot.isErrorGenerated) howManyWrongMessages++;
//            else if (!gilbertElliot.isErrorGenerated) howManyCorrectMessages++;
//        }
//        System.out.println("Corrected: " + howManyCorrectedMessages);
//        System.out.println("Wrong:" + howManyWrongMessages);
//        System.out.println("Correct: " + howManyCorrectMessages);
        gilbertElliot.test(dat, receiver, 10000);

//        Scanner scanner = new Scanner(System.in);
//        System.out.println("Binary symmetric channel");
//        System.out.println("Input the message:");
//        char[] dat = Integer.toBinaryString(scanner.nextInt()).toCharArray();
//        System.out.println("Input the probability:");
//        int probability = scanner.nextInt();
//        BST bst = new BST(dat, probability);
//        System.out.println(bst.getData());
//
//        System.out.println("Generating parity bit:");
//        bst.generateParityBit();
//        System.out.println("Generating error:");
//        System.out.println("Message before error:");
//        System.out.println(bst.getData());
//        bst.generateError();
//        System.out.println("Message after error:");
//        System.out.println(bst.getData());
//
//        System.out.println("Generating hamming code:");
//        bst.setData(dat);
//        bst.generateHammingCode();
//
//        System.out.println("Generating error:");
//        System.out.println("Message before error:");
//        System.out.println(bst.getData());
//        bst.generateError();
//        System.out.println("Message after error:");
//        System.out.println(bst.getData());
//
//        System.out.println();
//        System.out.println("Gilbert-Elliot channel");
//        System.out.println("Input the message:");
//        dat = Integer.toBinaryString(scanner.nextInt()).toCharArray();
//        System.out.println("Input the probability:");
//        probability = scanner.nextInt();
//        System.out.println("Input the higher probability:");
//        int higherProbability = scanner.nextInt();
//        System.out.println("Input the probability of changing the bad state to good state");
//        int prob = scanner.nextInt();
//        GilbertElliot gilbertElliot = new GilbertElliot(dat, probability, higherProbability, prob);
//        System.out.println(gilbertElliot.getData());
//
//        System.out.println("Generating parity bit:");
//        gilbertElliot.generateParityBit();
//        System.out.println("Generating error:");
//        System.out.println("Message before error:");
//        System.out.println(gilbertElliot.getData());
//        gilbertElliot.generateError();
//        System.out.println("Message after error:");
//        System.out.println(gilbertElliot.getData());
//
//        gilbertElliot.setData(dat);
//        System.out.println("Generating hamming code:");
//        gilbertElliot.setData(dat);
//        gilbertElliot.generateHammingCode();
//
//        System.out.println("Generating error:");
//        System.out.println("Message before error:");
//        System.out.println(gilbertElliot.getData());
//        gilbertElliot.generateError();
//        System.out.println("Message after error:");
//        System.out.println(gilbertElliot.getData());
    }
}
