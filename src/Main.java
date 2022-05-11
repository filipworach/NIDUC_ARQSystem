import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Binary symmetric channel");
        System.out.println("Input the message:");
        char[] dat = Integer.toBinaryString(scanner.nextInt()).toCharArray();
        System.out.println("Input the probability:");
        int probability = scanner.nextInt();
        BST bst = new BST(dat, probability);
        System.out.println(bst.getData());

        System.out.println("Generating parity bit:");
        bst.generateParityBit();
        System.out.println("Generating error:");
        System.out.println("Message before error:");
        System.out.println(bst.getData());
        bst.generateError();
        System.out.println("Message after error:");
        System.out.println(bst.getData());

        System.out.println("Generating hamming code:");
        bst.setData(dat);
        bst.generateHammingCode();

        System.out.println("Generating error:");
        System.out.println("Message before error:");
        System.out.println(bst.getData());
        bst.generateError();
        System.out.println("Message after error:");
        System.out.println(bst.getData());

        System.out.println();
        System.out.println("Gilbert-Elliot channel");
        System.out.println("Input the message:");
        dat = Integer.toBinaryString(scanner.nextInt()).toCharArray();
        System.out.println("Input the probability:");
        probability = scanner.nextInt();
        System.out.println("Input the higher probability:");
        int higherProbability = scanner.nextInt();
        System.out.println("Input the probability of changing the bad state to good state");
        int prob = scanner.nextInt();
        GilbertElliot gilbertElliot = new GilbertElliot(dat, probability, higherProbability, prob);
        System.out.println(gilbertElliot.getData());

        System.out.println("Generating parity bit:");
        gilbertElliot.generateParityBit();
        System.out.println("Generating error:");
        System.out.println("Message before error:");
        System.out.println(gilbertElliot.getData());
        gilbertElliot.generateError();
        System.out.println("Message after error:");
        System.out.println(gilbertElliot.getData());

        gilbertElliot.setData(dat);
        System.out.println("Generating hamming code:");
        gilbertElliot.setData(dat);
        gilbertElliot.generateHammingCode();

        System.out.println("Generating error:");
        System.out.println("Message before error:");
        System.out.println(gilbertElliot.getData());
        gilbertElliot.generateError();
        System.out.println("Message after error:");
        System.out.println(gilbertElliot.getData());
    }
}
