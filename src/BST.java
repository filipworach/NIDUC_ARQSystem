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
        int rand = random.nextInt(100);
        return errorProb >= rand;
    }

    public BST(char[] data, int errorProb) {
        super(data, errorProb);
    }

}
