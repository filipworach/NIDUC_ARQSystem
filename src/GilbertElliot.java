import java.util.Random;

public class GilbertElliot extends Channel{
    private int errorProbHigher;
    private int endBurstErrorProb;

    public GilbertElliot(char[] data, int errorProb, int errorProbHigher, int endBurstErrorProb) {
        super(data, errorProb);
        this.errorProbHigher = errorProbHigher;
        this.endBurstErrorProb = endBurstErrorProb;
    }

    public GilbertElliot() {
    }

    public void setErrorProbHigher(int errorProbHigher) {
        this.errorProbHigher = errorProbHigher;
    }

    public void setEndBurstErrorProb(int endBurstErrorProb) {
        this.endBurstErrorProb = endBurstErrorProb;
    }

    public void generateError() {
        boolean burst = false;

        for (int i = 0; i < data.length; i++) {
            if (burst) {
                if (isError(errorProbHigher)) {
                    isErrorGenerated=true;
                    if (data[i] == '1')
                        data[i] = '0';
                    else
                        data[i] = '1';

                }
                if (isError(endBurstErrorProb))
                    burst = false;
            }
            else {
                if (isError(errorProb)) {
                    burst = true;
                    isErrorGenerated=true;
                    if (data[i] == '1') {
                        data[i] = '0';
                    } else {
                        data[i] = '1';
                    }
                }
            }
        }
    }

    public void toArray(String data) {
        int size = data.length();
        char[] array = new char[32];
        char[] temp = data.toCharArray();
        int j = 0;
        for (int i = 0; i < (32 - size); i++) {
            array[i] = '0';
            j++;
        }

        for (int i = 0; i < size; i++) {
            array[j] = temp[i];
            j++;
        }
        this.data = array;
    }

    private boolean isError(int prob) {
        Random random = new Random();
        if (prob >= random.nextInt(100)) {
            return true;
        }
        return false;
    }

}
