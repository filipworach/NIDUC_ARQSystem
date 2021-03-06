import java.util.Arrays;
import java.util.Objects;

public class Channel {
    protected char[] data;
    protected int errorProb;
    protected boolean isErrorGenerated = false;
    private static final int HOW_MANY_RETRANSMISSIONS = 5;

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
        data = temporaryData;
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
        int divisor = 7;   // x^2 + x^1 + 1
        divisor = divisor << 23;
        int data = Integer.parseInt(String.valueOf(this.data), 2);//4464711;
        data = data << 8;

        int firstData = data;
        int pow = 1073741824;
        pow = pow << 1;
        for (int i = 0; i < 23; i++) {
            if ((data & (1 << (31 - i))) == pow) {
                data = data ^ divisor;
            }
            pow = (int) Math.pow(2, 31 - i - 1);
            divisor = divisor >> 1;
            divisor = divisor & 2147483647;
        }
        int crc = data & 255;
        int finalData = firstData | crc;

        char[] temp = Integer.toBinaryString(finalData).toCharArray();
        int length = temp.length;
        if (length < 32) {
            this.data = new char[32];
            for (int i = 0; i < (32-length); i++) {
                this.data[i] = '0';
            }
            int j=0;
            for(int i = (32 -length); i<32; i++) {
                this.data[i] = temp[j];
                j++;
            }
        }

    }

    public void generateError() {

    }

    public void generateDoubledData() {
        char[] temp = Calculator.writeOnChosenPositions(data, 16);
        char[] array = new char[32];

        for (int i = 0; i < 32; i++) {
            array[i] = temp[i / 2];
        }
        data = array;
    }

    private int retransmit(int howManyTries, Receiver receiver, char[] dat) {
        int howManyTimes = 0;
        for (int i = 0; i < howManyTries; i++) {
            this.setData(Arrays.copyOf(dat, dat.length));
            this.generateError();
            receiver.setMessage(Arrays.copyOf(data, data.length));
            if (!receiver.decode()) {
                howManyTimes++;
                return howManyTimes;
            } else howManyTimes++;
        }
        return 0;
    }

    public void test(char[] dat, Receiver receiver, int howManyIterations) {
        int howManyCorrectedMessages = 0;
        int howManyWrongMessages = 0;
        int howManyCorrectMessages = 0;
        int howManyRetransmissions = 0;
        int howManyRetransmissionsInOneLoop;
        int howManyUndetectedErrors = 0;
        boolean detected;
        boolean error;
        for (int i = 0; i < howManyIterations; i++) {
            isErrorGenerated = false;
            if (Objects.equals(receiver.getTypeOfCode(), "Hamming")) {
                dat = Integer.toBinaryString((int) Math.floor(Math.random() * 67108864)).toCharArray();
                dat = Calculator.writeOnChosenPositions(dat, 26);
                this.setData(Arrays.copyOf(dat, dat.length));
                this.generateHammingCode();
                dat = Arrays.copyOf(this.getData(), 31);
                this.generateError();
                receiver.setMessage(Arrays.copyOf(this.getData(), 31));
                receiver.decode();

                if (this.isErrorGenerated && !Arrays.equals(dat, receiver.getMessage())) howManyWrongMessages++;
                else if (this.isErrorGenerated && Arrays.equals(dat, receiver.getMessage())) howManyCorrectedMessages++;
                else if (!this.isErrorGenerated) howManyCorrectMessages++;
            } else if (Objects.equals(receiver.getTypeOfCode(), "parityBit")) {
                dat = Integer.toBinaryString((int) Math.floor(Math.random() * 2147483647)).toCharArray();
                dat = Calculator.writeOnChosenPositions(dat, 31);
                this.setData(Arrays.copyOf(dat, dat.length));
                this.generateParityBit();
                dat = Arrays.copyOf(this.getData(), 32);
                this.generateError();
                receiver.setMessage(Arrays.copyOf(this.getData(), 32));
                error = this.isErrorGenerated;
                detected = receiver.decode();
                if (detected) {
                    howManyRetransmissionsInOneLoop = retransmit(HOW_MANY_RETRANSMISSIONS, receiver, dat);
                    if (howManyRetransmissionsInOneLoop != 0) detected = false;
                    howManyRetransmissions += howManyRetransmissionsInOneLoop;
                }
                if (error && !detected && !Arrays.equals(dat, receiver.getMessage())) howManyUndetectedErrors++;
                else if (error && Arrays.equals(dat, receiver.getMessage()) && !detected) howManyCorrectedMessages++;
                else if (error && !Arrays.equals(dat, receiver.getMessage())) howManyWrongMessages++;
                else if (!error) howManyCorrectMessages++;

            } else if (Objects.equals(receiver.getTypeOfCode(), "doubledData")) {

                dat = Integer.toBinaryString((int) Math.floor(Math.random() * 65535)).toCharArray();
                this.setData(dat);
                this.generateDoubledData();
                dat = Arrays.copyOf(this.getData(), 32);
                this.generateError();
                receiver.setMessage(Arrays.copyOf(this.getData(), 32));
                error = this.isErrorGenerated;
                detected = receiver.decode();
                if (detected) {
                    howManyRetransmissionsInOneLoop = retransmit(HOW_MANY_RETRANSMISSIONS, receiver, dat);
                    if (howManyRetransmissionsInOneLoop != 0) detected = false;
                    howManyRetransmissions += howManyRetransmissionsInOneLoop;
                }
                if (error && !detected && !Arrays.equals(dat, receiver.getMessage())) howManyUndetectedErrors++;
                else if (error && Arrays.equals(dat, receiver.getMessage()) && !detected) howManyCorrectedMessages++;
                else if (error && !Arrays.equals(dat, receiver.getMessage())) howManyWrongMessages++;
                else if (!error) howManyCorrectMessages++;

            } else if (Objects.equals(receiver.getTypeOfCode(), "crc")) {

                dat = Integer.toBinaryString((int) Math.floor(Math.random() * 65535)).toCharArray();
                this.setData(Arrays.copyOf(dat, dat.length));
                this.generateCRCCode();
                dat = Arrays.copyOf(this.getData(), 32);
                this.generateError();
                receiver.setMessage(Arrays.copyOf(this.getData(), 32));

                error = this.isErrorGenerated;
                detected = receiver.decode();
                if (detected) {
                    howManyRetransmissionsInOneLoop = retransmit(HOW_MANY_RETRANSMISSIONS, receiver, dat);
                    if (howManyRetransmissionsInOneLoop != 0) detected = false;
                    howManyRetransmissions += howManyRetransmissionsInOneLoop;
                }
                if (error && !detected && !Arrays.equals(dat, receiver.getMessage())) howManyUndetectedErrors++;
                else if (error && Arrays.equals(dat, receiver.getMessage()) && !detected) howManyCorrectedMessages++;
                else if (error && !Arrays.equals(dat, receiver.getMessage())) howManyWrongMessages++;
                else if (!error) howManyCorrectMessages++;


            }
        }
        System.out.println("Corrected: " + howManyCorrectedMessages);
        System.out.println("Wrong:" + howManyWrongMessages);
        System.out.println("Correct: " + howManyCorrectMessages);
        if (!Objects.equals(receiver.getTypeOfCode(), "Hamming")) {
            System.out.println("Undetected: " + howManyUndetectedErrors);
            int temp = (int) Math.round(howManyRetransmissions * 1.0 / howManyCorrectedMessages);
            if (temp > 5) temp = 5;
            else if (temp == 0 && howManyCorrectedMessages != 0) temp = 1;
            System.out.println("How many retransmissions needed to get correct message: " + temp);
        }
        System.out.println();
    }

}
