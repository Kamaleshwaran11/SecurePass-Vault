package org.example;

import java.sql.Connection;
import java.util.Scanner;

public class Main {
    public static final String FILE_NAME = "Password.txt";
    public static void main(String[] args)throws Exception {
        Scanner sc = new Scanner(System.in);
        PasswordManager passwordManager = new PasswordManager();
        System.out.println("Welcome!!"); //Welcome message
        Connection conn = DatabaseConnection.getConnection();
        if (conn != null) {
            System.out.println("Connected to database successfully!");
        } else {
            System.out.println("Failed to connect to the database.");
        }

        while (true) {
            System.out.println("\n=== Password Manager ===");
            System.out.println("1. Generate Password");
            System.out.println("2. Check Password Strength");
            System.out.println("3. Save Password");
            System.out.println("4. Retrieve Passwords");
            System.out.println("5. Delete Password");
            System.out.println("6. Simulate Recovery");
            System.out.println("7. Simulate MFA");
            System.out.println("8. Simulate Cloud Backup");
            System.out.println("9. Show Compliance Logs");
            System.out.println("10. Password Guidelines");
            System.out.println("11. Show Password History");
            System.out.println("0. Exit");
            System.out.print("Choose an option: ");

            int choice;
            try {
                choice = Integer.parseInt(sc.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Please enter a Valid input!!!");
                continue;
            }

            switch (choice) {
                case 1 -> {
                    System.out.println("Generate the Strong Password....");
                    String password = passwordManager.generatePassword();
                    System.out.println("Generated Strong Password: " + password);
                }
                case 2 -> {
                    System.out.print("Enter the password to check its strength: ");
                    String password = sc.nextLine();
                    String strength = passwordManager.checkPasswordStrength(password);
                    System.out.println("Password Strength: " + strength);
                }
                case 3 -> {
                    passwordManager.SavePassword(sc);
                }
                case 4 -> {
                    passwordManager.retrievePasswords();
                }
                case 5 -> {
                    passwordManager.deletePassword(sc);
                }
                case 6 -> {
                    passwordManager.simulateRecovery(sc);
                }
                case 7 -> {
                    passwordManager.simulateMFA(sc);
                }
                case 8 -> {
                    passwordManager.simulateCloudBackup();
                }
                case 9 -> {
                    passwordManager.showComplianceLogs();
                }
                case 10 -> {
                    passwordManager.getUsefulInformation();
                }
                case 11 -> {
                    passwordManager.showPasswordHistory();
                }
                case 0 -> {
                    System.out.println("Exiting the program. Goodbye See You Soon Buddy!");
                    sc.close();
                    return; // Exit the loop and program
                }
                default -> System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
