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
        positionOfRedundantBit = temp - 1;
//        for (int i = 0; i < temp; i++) {
//            int counter = 0;
//            //char[] binaryValues = String.valueOf(Double.doubleToLongBits(Math.pow(2,i))).toCharArray();
//            for (int j = 0; j < data.length + temp - 1; j++) {
//                char[] binaryValues = Integer.toBinaryString(data.length + temp - j).toCharArray();
//                if(binaryValues.length != temp) {
//                    char[] t = new char[temp];
//                    int whichPosition = 0;
//                    for (int k = 0; k < temp; k++) {
//                        if(k < temp - binaryValues.length) t[k] ='0';
//                        else {
//                            t[k] = binaryValues[whichPosition];
//                            whichPosition++;
//                        }
//                    }
//                    binaryValues = t;
//                }
//                if (binaryValues[i] == '1') {
//                    if (temporaryData[j] == '1') counter++;
//                }
//            }
        //nowa wersja proba
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
        //tutaj koniec
//            if (counter%2==0) temporaryData[(int) (data.length + temp - Math.pow(2,positionOfRedundantBit) - 1)] = '0';
//            else temporaryData[(int) (data.length + temp - Math.pow(2,positionOfRedundantBit) - 1)] = '1';
//            System.out.println("Pozycja bitu w kodzie Hamminga: " + (data.length + temp - Math.pow(2,positionOfRedundantBit)));
//            positionOfRedundantBit--;



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
}
