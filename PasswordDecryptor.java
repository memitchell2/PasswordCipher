public class PasswordDecryptor {

    // Constructor for PasswordDecryptor (if needed)
    public PasswordDecryptor() {
        // Initialize any fields if necessary
    }

    // Method to decrypt the password
    public static String decrypt(String encryptedPassword, String website) {
        String decrpyt = xor(encryptedPassword, website);
        return decrpyt;
    }

    // Method to perfrom the xor operation
    public static String xor(String password, String website) {
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < password.length(); i++) {
            result.append((char) (password.charAt(i) ^ website.charAt(i)));  // XOR each character with the key
            System.out.println("Password: " + password.charAt(i) + " Website: " + website.charAt(i) + " Result: " + result.charAt(i));
        }
        return result.toString();
    }
}
