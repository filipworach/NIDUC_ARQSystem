public class Calculator {
    public static int whichPosition(int position[]) {
        int pos = 0;
        for (int i = 0; i < 5; i++) {
            pos += position[i] * Math.pow(2, 5 - i - 1);
        }
        return pos;
    }
}
