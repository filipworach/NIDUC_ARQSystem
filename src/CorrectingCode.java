import java.util.Arrays;

public enum CorrectingCode {
    crc{
        @Override
        char[] code(char data[]) {

            int divisor = 7;   // x^2 + x^1 + 1
            divisor = divisor << 23;
            int dataInt = Integer.parseInt(String.valueOf(data), 2);//4464711;
            dataInt = dataInt << 8;

            int firstData = dataInt;
            int pow = 1073741824;
            pow = pow << 1;
            for (int i = 0; i < 23; i++) {
                if ((dataInt & (1 << (31 - i))) == pow) {
                    dataInt = dataInt ^ divisor;
                }
                pow = (int) Math.pow(2, 31 - i - 1);
                divisor = divisor >> 1;
                divisor = divisor & 2147483647;
            }
            int crc = dataInt & 255;
            int finalData = firstData | crc;

            char[] temp;

            temp = Integer.toBinaryString(finalData).toCharArray();
            int lenght = temp.length;
            if(lenght<32){
                temp = Arrays.copyOf(temp, 32);
                for(int i =lenght; i<32 ;i++){
                    temp[i] = '0';
                }
            }

            return temp;
        }
    },
    hamming {
        @Override
        char[] code(char data[]) {

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
            return temporaryData;

            //return null;
        }
    },
    parityBit{
        @Override
        char[] code(char data[]) {
            short temp = 0;
            for (char datum : data) if (datum == '1') temp++;
            char[] temporaryData = new char[data.length + 1];
            for (int i = 0; i < data.length; i++) temporaryData[i] = data[i];
            if (temp % 2 == 0) temporaryData[data.length] = '1';
            else temporaryData[data.length] = '0';
            return temporaryData;
        }
    },
    doubledData {
        @Override
        char[] code(char data[]) {
            char[] temp = Calculator.writeOnChosenPositions(data, 16);
            char[] array = new char[32];

            for (int i = 0; i < 32; i++) {
                array[i] = temp[i / 2];
            }
            return array;
        }
    };

    char[] code(char data[]) {
        return null;
    }
    char[] deCode(char data[]) {
        return null;
    }

}
