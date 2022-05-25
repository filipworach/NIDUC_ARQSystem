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


    private boolean isError() {
        Random random = new Random();
        return errorProb >= random.nextInt(100);
    }

    public BST(char[] data, int errorProb) {
        super(data, errorProb);
    }

}
