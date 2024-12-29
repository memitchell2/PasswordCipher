import java.io.FileWriter;
import java.io.IOException;
import java.io.File;

public class PasswordEncryptor {
    private final String key;
    
    public PasswordEncryptor(String key) {
        this.key = key;
    }

    public String encrypt(String password, String website, String destinationFilePath) throws IOException {
        String combinedInput = password + ":" + website; // Combine password and website for encryption
        String encryptedPassword = cipherEncrypt(combinedInput, key, destinationFilePath);
        saveToFile(encryptedPassword, destinationFilePath);
        return encryptedPassword;
    }

    private String cipherEncrypt(String input, String key, String destinationFilePath) {
        int rotation = key.length(); // Determine rotation based on key length
        StringBuilder encrypted = new StringBuilder();

        for (char c : input.toCharArray()) {
            if (Character.isLetter(c)) {
                // Rotate letters (case-preserving)
                char base = Character.isUpperCase(c) ? 'A' : 'a';
                encrypted.append((char) ((c - base + rotation) % 26 + base));
            } else if (Character.isDigit(c)) {
                // Rotate digits
                encrypted.append((char) ((c - '0' + rotation) % 10 + '0'));
            } else {
                // Leave non-alphanumeric characters unchanged
                encrypted.append(c);
            }
        }
        return encrypted.toString();
    }

    public void saveToFile(String encryptedPassword, String destinationFilePath) throws IOException {
        File file = new File(destinationFilePath);
        try (FileWriter writer = new FileWriter(destinationFilePath)) {
            writer.write(encryptedPassword);
            System.out.println("Encrypted password saved to: " + file.getAbsolutePath());
        }
    }

}


