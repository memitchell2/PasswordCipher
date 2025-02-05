import java.io.FileWriter;
import java.io.IOException;
import java.io.File;

public class PasswordEncryptor {
    private final String key;
    
    public PasswordEncryptor(String key) {
        this.key = key;
    }

    public String encrypt(String password, String website, String destinationFilePath, int counter) throws IOException {
        String combinedInput = password; // Combine password and website for encryption

        String encryptedPassword = cipherEncrypt(combinedInput, key, destinationFilePath);
        if (counter == 0) {
            saveNewToFile(encryptedPassword + ":" + website, destinationFilePath);
        } else {
            saveToExistingFile(encryptedPassword + ":" + website, destinationFilePath);
        }
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

    /* 
     * Save the encrypted password to a new file
     * 
     * @param encryptedPassword The encrypted password to save
     * @param destinationFilePath The file path to save the encrypted password
     * @throws IOException If an error occurs while saving the encrypted password
     */
    public void saveNewToFile(String encryptedPassword, String destinationFilePath) throws IOException {
        File file = new File(destinationFilePath);
        try (FileWriter writer = new FileWriter(destinationFilePath)) {
            writer.write(encryptedPassword);
            System.out.println("Encrypted password saved to: " + file.getAbsolutePath());
        }
    }

    /*
     * Save the encrypted passwoed to the file created on the first iteration loop in the encrypt method
     * 
     * @param encryptedPassword The encrypted password to save
     * @param destinationFilePath The file path to save the encrypted password
     * @throws IOException If an error occurs while saving the encrypted password
     */
    public void saveToExistingFile(String encryptedPassword, String destinationFilePath) throws IOException {
        // Open the file in append mode from the saveNewToFile method
        try (FileWriter writer = new FileWriter(destinationFilePath, true)) {
            // Add a new line before writing the encrypted password
            writer.write("\n");
            // Add the password
            writer.write(encryptedPassword);
            System.out.println("Encrypted password saved to: " + destinationFilePath);
        }
    }

}


