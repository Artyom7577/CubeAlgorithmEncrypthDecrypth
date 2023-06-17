import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class MyCubeEncryption {
    String key;
    String text;
    MyCube[] cubeEncryptionDecryption;
    String[] chunks;

    public MyCubeEncryption(String text, String key) {
        int chunkSize = 8;
        int numChunks = (int) Math.ceil((double) text.length() / chunkSize);
        int size = (numChunks * 8) - text.length();
        text = text + "#".repeat(Math.max(0, size));

        this.chunks = new String[numChunks];

        for (int i = 0; i < numChunks; i++) {
            int startIndex = i * chunkSize;
            int endIndex = Math.min(startIndex + chunkSize, text.length());
            chunks[i] = text.substring(startIndex, endIndex);
        }
        this.text = text;
        this.key = key;

        this.cubeEncryptionDecryption = new MyCube[chunks.length];

        for (int i = 0; i < chunks.length; i++) {
            cubeEncryptionDecryption[i] = new MyCube(chunks[i]);
        }
    }

    public void encrypt() {
        int currCub = 0;
        String[] split = key.split(":");

        for (String value : split) {
            String[] split1 = value.split(",");
            for (String s : split1) {
                char c = s.charAt(0);
                int startIndex = 1;
                while (startIndex < s.length() && !Character.isDigit(s.charAt(startIndex))) {
                    startIndex++;
                }
                String numberSubstring = s.substring(startIndex);
                int number = Integer.parseInt(numberSubstring);
                if (c == 'L') {
                    for (int t = 0; t < number; t++) {
                        cubeEncryptionDecryption[currCub].rotateLeft();
                    }
                } else if (c == 'R') {
                    for (int t = 0; t < number; t++) {
                        cubeEncryptionDecryption[currCub].rotateRight();
                    }
                } else if (c == 'U') {
                    for (int t = 0; t < number; t++) {
                        cubeEncryptionDecryption[currCub].rotateUp();
                    }
                } else if (c == 'D') {
                    for (int t = 0; t < number; t++) {
                        cubeEncryptionDecryption[currCub].rotateDown();
                    }
                } else {
                    throw new IllegalArgumentException("UnKnown symbols:");
                }
            }
            ++currCub;
        }
    }

    public void print() {
        StringBuilder s = new StringBuilder();
        for (MyCube encryptionDecryption : cubeEncryptionDecryption) {
            s.append(encryptionDecryption.getText());
        }
        System.out.println(s);
    }

    public static void main(String[] args) {

        String filePathToEncrypt = "fileToEncrypt.txt";

        try (BufferedReader reader = new BufferedReader(new FileReader(filePathToEncrypt))) {
            String sentence = reader.readLine();
            String instructions = reader.readLine();

            MyCubeEncryption encryption = new MyCubeEncryption(sentence, instructions);

            encryption.encrypt();
            encryption.print();

        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }
}

