import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class PasswordDecryptor {
    private final String key;

    public PasswordDecryptor(String key) {
        this.key = key;
    }

    public String decrypt(String encryptedPassword, String key) throws IOException {
        // Read encrypted password from the file
        return cipherDecrypt(encryptedPassword, key);
    }

    private String cipherDecrypt(String input, String key) {
        int rotation = key.length(); // Determine rotation based on key length
        StringBuilder decrypted = new StringBuilder();

        for (char c : input.toCharArray()) {
            if (Character.isLetter(c)) {
                // Reverse rotation for letters (case-preserving)
                char base = Character.isUpperCase(c) ? 'A' : 'a';
                decrypted.append((char) ((c - base - rotation + 26) % 26 + base));
            } else if (Character.isDigit(c)) {
                // Reverse rotation for digits
                decrypted.append((char) ((c - '0' - rotation + 10) % 10 + '0'));
            } else {
                // Leave non-alphanumeric characters unchanged
                decrypted.append(c);
            }
        }
        return decrypted.toString();
    }

    public String loadFromFile(String encryptedFilePath) throws IOException {
        return Files.readString(Path.of(encryptedFilePath)).trim(); // Read encrypted password from file
    }
}