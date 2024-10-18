import java.util.Scanner;

public class Interactive {
    public static void main(String[] args) {
        // Create a Scanner object to read input
        Scanner scanner = new Scanner(System.in);
        String userInput = "";

        // Initialize the key (you can replace this with a dynamic key if needed)
        String key = "mySecretKey";
        
        // Create instances of PasswordEncryptor and PasswordDecryptor
        PasswordEncryptor encryptor = new PasswordEncryptor(key);
        PasswordDecryptor decryptor = new PasswordDecryptor(key);

        // Display initial message
        System.out.println("\n\nWelcome to the CPass software!\n.\n.\n.");
        System.out.println("This software is a password encryption tool designed to keep your passwords safe.\nTo use this software is quite simple!\nYou will only need two things:\n1. A password you want to encrypt\n2. The name of the website or service you want to use the password for!");
        System.out.println(".\n.\n.\nLet's get started with the menu!");

        // Display menu choices
        System.out.println("Menu:");
        System.out.println("1. Encrypt a password (type 'encrypt')");
        System.out.println("2. Decrypt a password (type 'decrypt')");
        System.out.println("3. Access the password vault (Password and pin required) (type 'vault')");
        System.out.println("4. A brief explanation of the importance of password encryption (type 'info')");
        System.out.println("5. View the menu (type 'menu')");
        System.out.println("6. Exit the program (type 'exit')");
        System.out.println(".\n.\n.\n All you need to do is type the key of the choice you would like!");

        // Start an interactive loop
        while (true) {
            // Prompt the user for input
            System.out.print("Enter your input: ");
            userInput = scanner.nextLine();  // Read input as a string

            if (userInput.equalsIgnoreCase("encrypt")) {
                System.out.println("You have chosen to encrypt a password!");
                System.out.println("Please enter the password you would like to encrypt: ");
                String password = scanner.nextLine();
                System.out.println("Please enter the name of the website or service you would like to use the password for: ");
                String website = scanner.nextLine();
                // Use the instance of PasswordEncryptor to encrypt
                String encryptedPassword = encryptor.encrypt(password, website);
                System.out.println("The encrypted password is: " + encryptedPassword);

            } else if (userInput.equalsIgnoreCase("decrypt")) {
                System.out.println("You have chosen to decrypt a password!");
                System.out.println("Please enter the encrypted password you would like to decrypt: ");
                String encryptedPassword = scanner.nextLine();
                System.out.println("Please enter the name of the website or service you would like to use the password for: ");
                String website = scanner.nextLine();
                // Use the instance of PasswordDecryptor to decrypt
                String decryptedPassword = decryptor.decrypt(encryptedPassword, website);
                System.out.println("The decrypted password is: " + decryptedPassword);

            } else if (userInput.equalsIgnoreCase("vault")) {
                System.out.println("You have chosen to access the password vault!");
                System.out.println("Please enter the password to access the vault: ");
                String password = scanner.nextLine();
                System.out.println("Please enter the pin to access the vault: ");
                String pin = scanner.nextLine();
                // Vault functionality can be added here

            } else if (userInput.equalsIgnoreCase("info")) {
                System.out.println("You have chosen to learn more about password encryption!");
                System.out.println("Password encryption is the process of converting a password into a different format that is more secure and difficult to crack.");
                System.out.println("This is important because passwords are often the only line of defense against unauthorized access to your personal information.");
                System.out.println("By encrypting your passwords, you can help protect your sensitive data from hackers and other malicious actors.");

            } else if (userInput.equalsIgnoreCase("menu")) {
                System.out.println("Menu:");
                System.out.println("1. Encrypt a password (type 'encrypt')");
                System.out.println("2. Decrypt a password (type 'decrypt')");
                System.out.println("3. Access the password vault (Password and pin required) (type 'vault')");
                System.out.println("4. A brief explanation of the importance of password encryption (type 'info')");
                System.out.println("5. View the menu (type 'menu')");
                System.out.println("6. Exit the program (type 'exit')");

            } else if (userInput.equalsIgnoreCase("exit")) {
                System.out.println("Exiting the program. Goodbye!");
                break; // Exit the loop to allow closing the scanner
            } else {
                System.out.println("Invalid input. Please try again.");
            }

            // Ask the user for the next action
            System.out.println("What else would you like to do?");
        }

        // Close the scanner (important to avoid resource leaks)
        scanner.close();
    }
}
