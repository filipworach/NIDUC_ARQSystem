import java.util.Arrays;

public class Channel {
    protected char[] data;
    protected int errorProb;
    protected boolean isErrorGenerated = false;

    public int getIntData() {
        return Integer.parseInt(data.toString(), 2);
    }

    public String getStringData() {
        return data.toString();
    }

    protected void generateHammingCode() {
        int temp = 0;
        while (Math.pow(2, temp) < data.length + temp) {
            temp++;
        }
        char[] temporaryData = new char[data.length + temp];
        int index = 0;
        int positionOfRedundantBit = temp - 1;
        for (int i = 0; i < data.length + temp - 1; i++) {
            if (temporaryData.length - i - 1 == Math.pow(2, positionOfRedundantBit) - 1) positionOfRedundantBit--;
            else {
                temporaryData[i] = data[index];
                index++;
            }
        }
        short tableWithPositions[] = {1, 2, 4, 8, 16};
        for (int i = 0; i < 5; i++) {
            int temporary = temporaryData.length - tableWithPositions[i];
            boolean firstLoop = true;
            int howMany = 0;
            while (temporary >= 0) {
                for (int j = 0; j < tableWithPositions[i]; j++) {
                    if (!firstLoop) {
                        if (temporaryData[temporary] == '1') howMany++;
                        temporary--;
                    } else {
                        firstLoop = false;
                        temporary--;
                    }


                }
                temporary = temporary - tableWithPositions[i];
            }
            if (howMany % 2 == 0)
                temporaryData[temporaryData.length - tableWithPositions[i]] = '0';
            else temporaryData[temporaryData.length - tableWithPositions[i]] = '1';


        }
    data =temporaryData;
}

    protected void generateParityBit() {
        short temp = 0;
        for (char datum : data) if (datum == '1') temp++;
        char[] temporaryData = new char[data.length + 1];
        for (int i = 0; i < data.length; i++) temporaryData[i] = data[i];
        if (temp % 2 == 0) temporaryData[data.length] = '1';
        else temporaryData[data.length] = '0';
        data = temporaryData;
    }

    @Override
    public String toString() {
        return Arrays.toString(data);
    }

    public Channel(char[] data, int errorProb) {
        this.data = data;
        this.errorProb = errorProb - 1;
    }

    public Channel() {
    }

    public char[] getData() {
        return data;
    }

    public void setErrorProb(int errorProb) {
        this.errorProb = errorProb;
    }

    public void setData(char[] data) {
        this.data = data;
    }

    public void generateCRCCode() {
        int divisor = 263;
        divisor = divisor << 24;
        int intData = Integer.parseInt(data.toString(),2);
        intData = intData << 8;
        for(int i = 0;i<24;i++){
            if((intData & (1<<(32-i)))==1){
            }
        }
    }

    public void generateError(){};

    public void test(char [] dat, Receiver receiver, int howManyIterations) {
        int howManyCorrectedMessages = 0;
        int howManyWrongMessages = 0;
        int howManyCorrectMessages = 0;
        for (int i = 0; i < howManyIterations; i++) {
            dat = Integer.toBinaryString((int) Math.floor(Math.random() * 67108864)).toCharArray();
            dat = Calculator.writeOnChosenPositions(dat, 26);
            this.setData(dat);
            this.generateHammingCode();
            dat = Arrays.copyOf(this.getData(), 31);
            this.generateError();
            receiver.setMessage(Arrays.copyOf(this.getData(), 31));
            boolean isError = receiver.decode();
            if (this.isErrorGenerated && Arrays.equals(dat, receiver.getMessage())) howManyCorrectedMessages++;
            else if (this.isErrorGenerated) howManyWrongMessages++;
            else if (!this.isErrorGenerated) howManyCorrectMessages++;
        }
        System.out.println("Corrected: " + howManyCorrectedMessages);
        System.out.println("Wrong:" + howManyWrongMessages);
        System.out.println("Correct: " + howManyCorrectMessages);
    }
}
