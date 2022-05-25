import java.util.Objects;

public class Receiver {
    private char[] message;
    private String typeOfCode;

    public Receiver(String typeOfCode) {
        this.typeOfCode = typeOfCode;
    }

    public void setMessage(char[] message) {
        this.message = message;
    }

    public char[] getMessage() {
        return message;
    }

    public void setTypeOfCode(String typeOfCode) {
        this.typeOfCode = typeOfCode;
    }

    public String getTypeOfCode() {
        return typeOfCode;
    }

    private void hamming() {
        message = new char[31];
        short[] tableWithPositions = {1, 2, 4, 8, 16};
        int[] position = new int[5];
        boolean isError = false;
        for (int i = 0; i < 5; i++) {
            int temp = message.length - tableWithPositions[i];
            boolean firstLoop = true;
            int howMany = 0;
            while (temp >= 0) {
                for (int j = 0; j < tableWithPositions[i]; j++) {
                    if (!firstLoop) {
                        if (message[temp] == '1') howMany++;
                        temp--;
                    } else {
                        firstLoop = false;
                        temp--;
                    }
                }
                temp = temp - tableWithPositions[i];
            }
            if (message[message.length - tableWithPositions[i]] == '1') howMany++;
            if (howMany % 2 == 0) {
                position[5 - i - 1] = 0;

            } else {
                position[5 - i - 1] = 1;
                if (message[message.length - tableWithPositions[i]] == '1') isError = true;
            }


        }

        if (isError) {
            int pos = 0;
            pos = message.length - Calculator.whichPosition(position) - 1;
            if (message[pos] == '1') message[pos] = '0';
            else if (message[pos] == '0') message[pos] = '1';
            //return true;
        }
        //return false;
    }

    private boolean parityBit() {
        int howManyOnes = 0;
        for (int i = 0; i < message.length - 1; i++) {
            if (message[i] == '1') howManyOnes++;
        }
        return (message[message.length - 1] == '0' && howManyOnes % 2 == 0) || (message[message.length - 1] == '1' && howManyOnes % 2 == 1);
    }

    public boolean decode() {
        switch (typeOfCode) {
            case "Hamming":
                hamming();
                return false;
            case "parityBit":
                if (parityBit()) return true;
            case "doubledData":
                if(doubledData()) return true;
            default: return false;
        }
    }

    private boolean doubledData() {
        for (int i = 0; i < message.length - 1; i+=2) {
            return !Objects.equals(message[i], message[i + 1]);
        }
        return false;
    }
}

