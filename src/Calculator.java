public class Calculator {
    public static int whichPosition(int position[]) {
        int pos = 0;
        for (int i = 0; i < 5; i++) {
            pos += position[i] * Math.pow(2, 5 - i - 1);
        }
        return pos - 1;
    }

    public static char[] writeOnChosenPositions(char[] tab, int position) {
        int tabLength = tab.length;
        int howManyZeros = position - tabLength;
        char[] newData = new char[tabLength + howManyZeros];

        if (howManyZeros > 0) {
            for (int i = 0; i < howManyZeros; i++) {
                newData[i] = '0';
            }
            for (int i = howManyZeros; i < newData.length; i++) {
                newData[i] = tab[i - howManyZeros];
            }
            return newData;
        } else return tab;
    }
}
