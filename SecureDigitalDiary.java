import java.io.*;
import java.util.*;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.MessageDigest;
import java.util.Base64;
import java.nio.file.*;

// Class representing a User
class User {
    private String username;
    private String password;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}

// Class representing a Diary Entry
class DiaryEntry {
    private String title;
    private String content;
    private String timestamp;

    public DiaryEntry(String title, String content) {
        this.title = title;
        this.content = content;
        this.timestamp = new Date().toString();
    }

    @Override
    public String toString() {
        return "Title: " + title + "\n" +
               "Timestamp: " + timestamp + "\n" +
               "Content: " + content + "\n";
    }
}

// Utility class for encryption and hashing
class SecurityUtils {
    private static final String AES = "AES";

    public static String encrypt(String data, SecretKey key) throws Exception {
        Cipher cipher = Cipher.getInstance(AES);
        cipher.init(Cipher.ENCRYPT_MODE, key);
        byte[] encrypted = cipher.doFinal(data.getBytes());
        return Base64.getEncoder().encodeToString(encrypted);
    }

    public static String decrypt(String encryptedData, SecretKey key) throws Exception {
        Cipher cipher = Cipher.getInstance(AES);
        cipher.init(Cipher.DECRYPT_MODE, key);
        byte[] decoded = Base64.getDecoder().decode(encryptedData);
        return new String(cipher.doFinal(decoded));
    }

    public static SecretKey generateKey() throws Exception {
        KeyGenerator keyGen = KeyGenerator.getInstance(AES);
        keyGen.init(128);
        return keyGen.generateKey();
    }

    public static String hashPassword(String password) throws Exception {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] hash = digest.digest(password.getBytes());
        return Base64.getEncoder().encodeToString(hash);
    }
}

// Add ANSI escape codes for colors and clear screen
class TerminalUtils {
    public static final String CLEAR_SCREEN = "\033[H\033[2J";
    public static final String RESET = "\033[0m";
    public static final String GREEN = "\033[32m";
    public static final String RED = "\033[31m";
    public static final String YELLOW = "\033[33m";
    public static final String BLUE = "\033[34m";
    public static final String BOLD = "\033[1m";

    public static void clearScreen() {
        System.out.print(CLEAR_SCREEN);
        System.out.flush();
    }

    public static void loadingAnimation(String message) {
        System.out.print(YELLOW + message);
        for (int i = 0; i < 3; i++) {
            try {
                Thread.sleep(500);
                System.out.print(".");
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        System.out.println(RESET);
    }
}

public class SecureDigitalDiary {

    private static final String USERS_FILE = "users.txt";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            TerminalUtils.clearScreen();
            System.out.println("\n========================");
            System.out.println(TerminalUtils.BOLD + "  WELCOME TO SECURE     " + TerminalUtils.RESET);
            System.out.println(TerminalUtils.BOLD + "    DIGITAL DIARY       " + TerminalUtils.RESET);
            System.out.println("========================");
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. Users");
            System.out.println("4. Exit");
            System.out.println("========================");

            System.out.print(TerminalUtils.YELLOW + "Choose an option: " + TerminalUtils.RESET);
            int choice = getIntInput(scanner);
            if (choice == -1) continue;

            switch (choice) {
                case 1:
                    registerUser(scanner);
                    break;
                case 2:
                    if (loginUser(scanner)) {
                        // Login already handles showing the main menu
                    }
                    break;
                case 3:
                    displayRegisteredUsers();
                    break;
                case 4:
                    System.out.println(TerminalUtils.GREEN + "Exiting... Goodbye!" + TerminalUtils.RESET);
                    return;
                default:
                    System.out.println(TerminalUtils.RED + "Invalid choice. Please try again." + TerminalUtils.RESET);
            }
        }
    }

    private static void registerUser(Scanner scanner) {
        System.out.print("Enter username: ");
        String username = scanner.nextLine().toLowerCase();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        try {
            // Hash the password
            String hashedPassword = SecurityUtils.hashPassword(password);

            // Generate and save the encryption key
            SecretKey key = SecurityUtils.generateKey();
            String keyFileName = username + ".key";
            Files.write(Paths.get(keyFileName), key.getEncoded());

            // Save the user credentials
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(USERS_FILE, true))) {
                writer.write(username + "," + hashedPassword);
                writer.newLine();
            }

            System.out.println("Registration successful! Logging you in...");
            showMainMenu(scanner, username, key);
        } catch (Exception e) {
            System.out.println("Error during registration: " + e.getMessage());
        }
    }

    private static boolean loginUser(Scanner scanner) {
        System.out.print("Enter username: ");
        String username = scanner.nextLine().toLowerCase();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        try {
            // Verify credentials
            try (BufferedReader reader = new BufferedReader(new FileReader(USERS_FILE))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] parts = line.split(",");
                    if (parts[0].equals(username) && SecurityUtils.hashPassword(password).equals(parts[1])) {
                        // Load the encryption key
                        String keyFileName = username + ".key";
                        byte[] keyBytes = Files.readAllBytes(Paths.get(keyFileName));
                        SecretKey key = new SecretKeySpec(keyBytes, "AES");

                        System.out.println("Login successful!");
                        showMainMenu(scanner, username, key);
                        return true;
                    }
                }
            }
        } catch (NoSuchFileException e) {
            System.out.println("Encryption key file missing for user: " + username);
        } catch (Exception e) {
            System.out.println("Error during login: " + e.getMessage());
        }

        System.out.println("Invalid username or password.");
        return false;
    }

    // Update showMainMenu to display logged-in user and keep menu persistent
    private static void showMainMenu(Scanner scanner, String username, SecretKey key) {
        while (true) {
            TerminalUtils.clearScreen();
            System.out.println("\n========================");
            System.out.println(TerminalUtils.BOLD + "  MAIN MENU - " + username.toUpperCase() + TerminalUtils.RESET);
            System.out.println("========================");
            System.out.println("1. Write New Diary Entry");
            System.out.println("2. View Diary Entries");
            System.out.println("3. Logout");
            System.out.println("========================");

            System.out.print(TerminalUtils.YELLOW + "Choose an option: " + TerminalUtils.RESET);
            int choice = getIntInput(scanner);
            if (choice == -1) continue;

            switch (choice) {
                case 1:
                    writeDiaryEntry(scanner, username, key);
                    break;
                case 2:
                    viewDiaryEntries(scanner, username, key);
                    break;
                case 3:
                    System.out.println(TerminalUtils.GREEN + "Logging out..." + TerminalUtils.RESET);
                    return;
                default:
                    System.out.println(TerminalUtils.RED + "Invalid choice. Please try again." + TerminalUtils.RESET);
            }
        }
    }

    private static void writeDiaryEntry(Scanner scanner, String username, SecretKey key) {
        System.out.print("Enter title: ");
        String title = scanner.nextLine();
        System.out.print("Enter content: ");
        String content = scanner.nextLine();

        DiaryEntry entry = new DiaryEntry(title, content);
        String filename = username + "_entries.txt";

        TerminalUtils.loadingAnimation("Saving your entry");

        try {
            String encryptedContent = SecurityUtils.encrypt(entry.toString(), key);

            try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename, true))) {
                writer.write(encryptedContent);
                writer.newLine();
                System.out.println(TerminalUtils.GREEN + "\nDiary entry saved successfully!" + TerminalUtils.RESET);
            }
        } catch (Exception e) {
            System.out.println(TerminalUtils.RED + "Error saving diary entry: " + e.getMessage() + TerminalUtils.RESET);
        }

        System.out.println("\nReturning to Main Menu...");
    }

    // Update viewDiaryEntries to format entries in card-like style
    private static void viewDiaryEntries(Scanner scanner, String username, SecretKey key) {
        String filename = username + "_entries.txt";

        TerminalUtils.clearScreen();
        System.out.println("\n========================");
        System.out.println(TerminalUtils.BOLD + "  YOUR DIARY ENTRIES    " + TerminalUtils.RESET);
        System.out.println("========================");

        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                try {
                    String decryptedContent = SecurityUtils.decrypt(line, key);
                    String[] parts = decryptedContent.split("\n", 3);
                    System.out.println("+------------------------+");
                    System.out.println("| " + TerminalUtils.BOLD + parts[0] + TerminalUtils.RESET); // Title
                    System.out.println("| " + TerminalUtils.BLUE + parts[1] + TerminalUtils.RESET); // Timestamp
                    System.out.println("| " + parts[2]); // Content
                    System.out.println("+------------------------+");
                } catch (Exception e) {
                    System.out.println(TerminalUtils.RED + "Error decrypting entry: " + e.getMessage() + TerminalUtils.RESET);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println(TerminalUtils.RED + "No entries found for this user." + TerminalUtils.RESET);
        } catch (IOException e) {
            System.out.println(TerminalUtils.RED + "Error reading diary entries: " + e.getMessage() + TerminalUtils.RESET);
        }

        System.out.println("========================\n");
    }

    private static void displayRegisteredUsers() {
        System.out.println("\n========================");
        System.out.println("   REGISTERED USERS     ");
        System.out.println("========================");

        try (BufferedReader reader = new BufferedReader(new FileReader(USERS_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                System.out.println("- " + parts[0]); // Display only the username with a bullet point
            }
        } catch (FileNotFoundException e) {
            System.out.println("No registered users found.");
        } catch (IOException e) {
            System.out.println("Error reading user data.");
        }

        System.out.println("========================\n");
    }

    private static int getIntInput(Scanner scanner) {
        try {
            if (scanner.hasNextInt()) {
                int value = scanner.nextInt();
                scanner.nextLine(); // Consume newline
                return value;
            } else {
                String input = scanner.nextLine();
                System.out.println(TerminalUtils.RED + "Invalid input. Please enter a number." + TerminalUtils.RESET);
                try {
                    Thread.sleep(1500);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
                return -1;
            }
        } catch (Exception e) {
            scanner.nextLine(); // Clear the buffer
            System.out.println(TerminalUtils.RED + "Invalid input. Please try again." + TerminalUtils.RESET);
            try {
                Thread.sleep(1500);
            } catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
            }
            return -1;
        }
    }
}
