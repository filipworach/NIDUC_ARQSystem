import java.util.Random;

public class BST extends Channel{
    public void generateError() {
        isErrorGenerated = false;
        for (int i = 0; i < data.length; i++) {
            if (isError()) {
                if (data[i] == '1') {
                    data[i] = '0';
                } else {
                    data[i] = '1';
                }
                isErrorGenerated = true;
            }
        }
    }

    public void generateDoubledData() {
        toArray();
        char[] temp = data;
        char[] array = new char[32];

        for (int i = 0; i < 32; i++) {
            array[i] = temp[i / 2];
        }
        data = array;
    }

    private void toArray() {
        int size = data.length;
        char[] array = new char[16];
        char[] temp = data;
        int j = 0;
        for (int i = 0; i < (16 - size); i++) {
            array[i] = '0';
            j++;
        }

        for (int i = 0; i < size; i++) {
            array[j] = temp[i];
            j++;
        }
        data = array;
    }

    private boolean isError() {
        Random random = new Random();
        if (errorProb >= random.nextInt(100)) {
            return true;
        }
        return false;
    }

    public BST(char[] data, int errorProb) {
        super(data, errorProb);
    }

}
