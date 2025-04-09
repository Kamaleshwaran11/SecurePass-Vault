package org.example;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.io.*;
import java.security.SecureRandom;
import java.util.Scanner;
import java.sql.ResultSet;


import static org.example.Main.FILE_NAME;

public class PasswordManager {
    private static final String ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%^&*()-_+=<>?";
    private static final int PASSWORD_LENGTH = 12; // Length of the generated password

    // Generate a Strong Password
    public String generatePassword() {
        StringBuilder password = new StringBuilder(PASSWORD_LENGTH);
        SecureRandom random = new SecureRandom();

        for (int i = 0; i < PASSWORD_LENGTH; i++) {
            int index = random.nextInt(ALPHABET.length());
            password.append(ALPHABET.charAt(index));
        }

        return password.toString();
    }
    //
    public static boolean isPhishingLabel(String label){
        String[] suspiciousKeywords= {"bank","login","verify","reset","otp","secure"};
        for (String word:suspiciousKeywords){
            if(label.toLowerCase().contains(word)){
                return true;
            }
        }
        return false;
    }
    // Check the strength of a password
    public String checkPasswordStrength(String password) {
        String strength = "Weak";
        int score = 0;

        if (password.length() >= 8) score++;
        if (password.matches(".*[a-z].*")) score++;
        if (password.matches(".*[A-Z].*")) score++;
        if (password.matches(".*\\d.*")) score++;
        if (password.matches(".*[!@#$%^&*(),.?\":{}|<>].*")) score++;

        switch (score) {
            case 5:
                strength = "Very Strong";
                break;
            case 4:
                strength = "Strong";
                break;
            case 3:
                strength = "Moderate";
                break;
            case 2:
                strength = "Weak";
                break;
            default:
                strength = "Very Weak";
        }

        return strength;
    }

        public void SavePassword(Scanner sc) {
            System.out.print("Enter a label for your password: ");
            String label = sc.nextLine();
            if(isPhishingLabel(label)){
                System.out.println("Warning: The Label contains potentially suspicious keywords.");
            }
            System.out.print("Enter the password to save: ");
            String password = sc.nextLine();

             // Hashing for security

            String sql = "INSERT INTO passwords (label, password_save) VALUES (?, ?)";

            try (Connection conn = DatabaseConnection.getConnection();
                 PreparedStatement pstmt = conn.prepareStatement(sql)) {

                pstmt.setString(1, label);
                pstmt.setString(2, password);
                pstmt.executeUpdate();

                System.out.println("Password saved successfully!");
            } catch (SQLException e) {
                System.out.println("Error saving password: " + e.getMessage());
            }
        }


    public void retrievePasswords() {
        String sql = "SELECT label, password_save FROM passwords";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            if (!rs.isBeforeFirst()) {
                System.out.println("No passwords stored yet.");
                return;
            }

            while (rs.next()) {
                System.out.println("Label: " + rs.getString("label"));
                System.out.println("Password: " + rs.getString("password_save"));
                System.out.println("---------------------------------");
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving passwords: " + e.getMessage());
        }
    }

    public void deletePassword(Scanner sc) {
        System.out.print("Enter the label of the password to delete: ");
        String label = sc.nextLine();

        String sql = "DELETE FROM passwords WHERE label = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, label);
            int rowsAffected = pstmt.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Password deleted successfully.");
            } else {
                System.out.println("Label not found!");
            }
        } catch (SQLException e) {
            System.out.println("Error deleting password: " + e.getMessage());
        }
    }



    // Provide useful information
    public void getUsefulInformation() {
        System.out.println();
        System.out.println("Use a minimum password length of 8 or more characters if permitted.");
        System.out.println("Include lowercase and uppercase alphabetic characters, numbers, and symbols if permitted.");
        System.out.println("Generate passwords randomly where feasible.");
        System.out.println("Avoid using the same password twice (e.g., across multiple user accounts and/or software systems).");
        System.out.println("Avoid character repetition, keyboard patterns, dictionary words, letter or number sequences," +
                "\nusernames, relative or pet names, romantic links (current or past), " +
                "and biographical information (e.g., ID numbers, ancestors' names, or dates).");
        System.out.println("Avoid using information that the user's colleagues and/or " +
                "acquaintances might know to be associated with the user.");
        System.out.println("Do not use passwords that consist wholly of any simple combination of the aforementioned weak components.");
    }
}
