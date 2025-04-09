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
            System.out.println();
            System.out.println("1 - Generate the Strong Password");
            System.out.println("2 - Check the password Strength");
            System.out.println("3 - Save a New Password (with Security Validation)");
            System.out.println("4 - Retrive Stored Passwords");
            System.out.println("5 - Delete a Stored Password");
            System.out.println("6 - View Password Guidelines & Rules");
            System.out.println("7 - View Password Change History");
            System.out.println("8 - Detect Repeated passwords");
            System.out.println("9 - Password Recovery (Simulated)");
            System.out.println("10 - Multi-Factor Authentication Setup (Simulated)");
            System.out.println("11 -  Simulate Cloud Backup (Export/Import)");
            System.out.println("12 - Monitor Compliance Logs");
            System.out.println("0 - Quit");
            System.out.print("Enter the Your Choice: ");

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
                    passwordManager.getUsefulInformation();
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
