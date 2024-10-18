import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.nio.charset.StandardCharsets;

public class PasswordDecryptor {

    private String key;

    // Constructor for PasswordDecryptor
    public PasswordDecryptor(String key) {
        this.key = key;
    }

    // Method to XOR a string with a key
    private String xorString(String input, String key) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < input.length(); i++) {
            result.append((char) (input.charAt(i) ^ key.charAt(i % key.length())));
        }
        return result.toString();
    }

    // Method to create a hash (MAC) using SHA-256
    private String generateHash(String input) throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] encodedHash = digest.digest(input.getBytes(StandardCharsets.UTF_8));

        // Convert byte array to hex string
        StringBuilder hexString = new StringBuilder();
        for (byte b : encodedHash) {
            String hex = Integer.toHexString(0xff & b);
            if (hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        return hexString.toString();
    }

    // Method to decrypt the password and verify the MAC
    public String decrypt(String encryptedPassword, String website) {
        try {
            // Split the encrypted password and the MAC
            String[] parts = encryptedPassword.split(":");
            String xorPassword = parts[0];
            String receivedMac = parts[1];

            // XOR the password back to its original form
            String decryptedPassword = xorString(xorPassword, key);

            // Recalculate the MAC for validation
            String combinedString = xorPassword + xorString(website, key);
            String recalculatedMac = generateHash(combinedString);

            // Verify that the recalculated MAC matches the received MAC
            if (recalculatedMac.equals(receivedMac)) {
                return decryptedPassword;
            } else {
                throw new RuntimeException("MAC verification failed. The data may have been tampered with.");
            }

        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Error generating hash: " + e.getMessage());
        }
    }
}
