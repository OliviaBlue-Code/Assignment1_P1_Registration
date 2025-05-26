
package com.mycompany.assignment1_p1_registration;

import javax.swing.JOptionPane;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class Login {
    private String username;
    private String password;
    private String cell_phone_number;

    public Login(String username, String password, String cell_phone_number) {
        this.username = username;
        this.password = password;
        this.cell_phone_number = cell_phone_number;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getCell_phone_number() {
        return cell_phone_number;
    }

    public static boolean checkUserName(String username) {
        String regex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*_)[a-zA-Z0-9_]{1,5}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(username);
        if (matcher.matches()) {
            JOptionPane.showMessageDialog(null, "Username successfully captured");
            return true;
        } else {
            JOptionPane.showMessageDialog(null, "Username is not correctly formatted, please ensure that your username contains an underscore, an uppercase letter, a lowercase letter, and is no more than five characters in length.");
            return false;
        }
    }

    public static boolean checkPasswordComplexity(String password) {
        String regex = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[^a-zA-Z0-9]).{8}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(password);
        if (matcher.matches()) {
            JOptionPane.showMessageDialog(null, "Password successfully captured");
            return true;
        } else {
            JOptionPane.showMessageDialog(null, "Password is not correctly formatted; please ensure that the password contains at least eight characters, a capital letter, a number, and a special character");
            return false;
        }
    }

    public static boolean checkCellPhoneNumber(String cell_phone_number) {
        String regex = "^(\\+27)\\d{9}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(cell_phone_number);
        if (matcher.matches()) {
            JOptionPane.showMessageDialog(null, "Cell phone number successfully added.");
            return true;
        } else {
            JOptionPane.showMessageDialog(null, "Cell phone number incorrectly formatted or does not contain international code.");
            return false;
        }
    }
}

