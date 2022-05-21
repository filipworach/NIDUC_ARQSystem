public class Receiver {
    private char[] message = new char[31];
    private String typeOfCode;

    public Receiver(String typeOfCode) {
        this.typeOfCode = typeOfCode;
    }

    public void setMessage(char[] message) {
        this.message = message;
    }

    public void decode() {
        short tableWithPositions[] = {1, 2, 4, 8, 16};
        switch (typeOfCode) {
            case "Hamming":
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
                    if(message[message.length - tableWithPositions[i]] == '1') howMany++;
                    if (howMany % 2 == 0) {
                        position[5 - i - 1] = 1;
                        if(message[message.length - tableWithPositions[i]] == '1') isError = true;
                    }
                    else position[5 - i - 1] = 0;


                }
                int pos = 0;
                if(isError) pos = Calculator.whichPosition(position);
                System.out.println(pos);
                break;
        }
    }
}
