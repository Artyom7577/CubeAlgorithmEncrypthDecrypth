import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MyCubeDecryption {
    private final String text;
    private final String key;
    private final MyCube[] cubeEncryptionDecryption;

    public MyCubeDecryption(String text, String key) {
        this.text = text;
        this.key = key;

        List<String> chunks = new ArrayList<>();

        int startIndex = 0;
        int endIndex = Math.min(text.length(), 8);

        while (startIndex < text.length()) {
            String chunk = text.substring(startIndex, endIndex);

            chunk = chunk.replace("#", ".");

            if (!chunk.isEmpty()) {
                chunks.add(chunk);
            }

            startIndex = endIndex;
            endIndex = Math.min(startIndex + 8, text.length());
        }

        this.cubeEncryptionDecryption = new MyCube[chunks.size()];

        for (int i = 0; i < chunks.size(); i++) {
            String chunk = chunks.get(i);

            cubeEncryptionDecryption[i] = new MyCube(chunk);
        }
    }

    public void decrypt() {
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
                        cubeEncryptionDecryption[currCub].rotateRight();
                    }
                } else if (c == 'R') {
                    for (int t = 0; t < number; t++) {
                        cubeEncryptionDecryption[currCub].rotateLeft();
                    }
                } else if (c == 'U') {
                    for (int t = 0; t < number; t++) {
                        cubeEncryptionDecryption[currCub].rotateDown();
                    }
                } else if (c == 'D') {
                    for (int t = 0; t < number; t++) {
                        cubeEncryptionDecryption[currCub].rotateUp();
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
        String filePathToDecrypt = "fileToDecrypt.txt";

        try (BufferedReader reader = new BufferedReader(new FileReader(filePathToDecrypt))) {
            String sentence = reader.readLine();
            String instructions = reader.readLine();

            MyCubeDecryption decryption = new MyCubeDecryption(sentence, instructions);

            decryption.decrypt();
            decryption.print();

        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }

    private String getText() {
        return text;
    }
}

