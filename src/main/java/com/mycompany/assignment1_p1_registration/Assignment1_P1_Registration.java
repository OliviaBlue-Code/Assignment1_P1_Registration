 package com.mycompany.assignment1_p1_registration;

import java.util.Scanner;

public class Assignment1_P1_Registration {
//input object
    public static void main(String[] args) {
       String username;
       String password;
       String cell_phone_number;
     //scanner
    Scanner input = new Scanner(System.in);
    Login user = registerUser (input);
        if (user != null){
        System.out.println("Registration successful!");
        loginUser(input,user);
         }
    } 
     private static Login registerUser(Scanner input) {//method
         
        
        String username = null;
        boolean checkUserName = false;
        while (!checkUserName) {
            System.out.print("Enter username: ");
            username = input.nextLine();
            checkUserName = Login.checkUserName(username);
        }
         String password = "";
        boolean checkPasswordComplexity = false;
        while (!checkPasswordComplexity) {
            System.out.print("Enter password: ");
            password = input.nextLine();
            checkPasswordComplexity = Login.checkPasswordComplexity(password);
        }
        String cell_phone_number = "";
        boolean checkCellPhoneNumber = false;
        while (!checkCellPhoneNumber) {
            System.out.print("Enter cell phone number: ");
            cell_phone_number = input.nextLine();
            checkCellPhoneNumber = Login.checkCellPhoneNumber(cell_phone_number);
             }

        return new Login(username, password, cell_phone_number);
    }

    private static void loginUser(Scanner input, Login user) {
        System.out.println("*****Login*****");
        System.out.print("Enter username: ");
        String username = input.nextLine();
        System.out.print("Enter password: ");
        String password = input.nextLine();

        if (!username.equals(user.getUsername()) || !password.equals(user.getPassword())) {
            System.out.println("Invalid username or password");
        } else {
            System.out.println("Login successful!");
            System.out.println("Username: " + user.getUsername());
            System.out.println("Password: " + user.getPassword());
        }
    }
}



       

    
       
    

 
   
    

