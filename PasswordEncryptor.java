import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.nio.charset.StandardCharsets;

public class PasswordEncryptor {

    private String key;

    // Constructor for PasswordEncryptor
    public PasswordEncryptor(String key) {
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

    // Method to encrypt the password
    public String encrypt(String password, String website) {
        try {
            // XOR the password and website with the key
            String xorPassword = xorString(password, key);
            String xorWebsite = xorString(website, key);

            // Combine the XORed values and generate a MAC (hash)
            String combinedString = xorPassword + xorWebsite;
            String mac = generateHash(combinedString);

            // Return the XORed password with the MAC appended
            return xorPassword + ":" + mac;

        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Error generating hash: " + e.getMessage());
        }
    }
}
