
package com.mycompany.assignment1_p1_registration;

import javax.swing.JOptionPane;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.json.JsonWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonReader;

public class Assignment1_P1_Registration {

    public static void main(String[] args) {
        Login user = registerUser();
        if (user != null) {
            LoginUser(user);
        }
    }

    private static Login registerUser() {
        String username = null;
        boolean checkUserName = false;
        while (!checkUserName) {
            username = JOptionPane.showInputDialog(null, "Enter username: ");
            checkUserName = Login.checkUserName(username);
        }

        String password = "";
        boolean checkPasswordComplexity = false;
        while (!checkPasswordComplexity) {
            password = JOptionPane.showInputDialog(null, "Enter password: ");
            checkPasswordComplexity = Login.checkPasswordComplexity(password);
        }

        String cell_phone_number = "";
        boolean checkCellPhoneNumber = false;
        while (!checkCellPhoneNumber) {
            cell_phone_number = JOptionPane.showInputDialog(null, "Enter cell phone number: ");
            checkCellPhoneNumber = Login.checkCellPhoneNumber(cell_phone_number);
        }

        return new Login(username, password, cell_phone_number);
    }

    private static void LoginUser(Login user) {
    boolean loggedIn = false;
    while (!loggedIn) {
        String username = JOptionPane.showInputDialog(null, "Enter username: ");
        String password = JOptionPane.showInputDialog(null, "Enter password: ");

        if (!username.equals(user.getUsername()) || !password.equals(user.getPassword())) {
            JOptionPane.showMessageDialog(null, "Invalid username or password. Please try again.");
        } else {
            JOptionPane.showMessageDialog(null, "Welcome to QuickChat");

            boolean running = true;
            while (running) {
                String[] options = {"Send Messages", "Show Recently Sent Messages", "Quit"};
                int choice = JOptionPane.showOptionDialog(null, "Menu", "QuickChat", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
             switch (choice) {
                    case 0:
                        sendMessages();
                        break;
                    case 1:
                        JOptionPane.showMessageDialog(null, "Coming soon");
                        break;
                    case 2:
                        running = false;
                        JOptionPane.showMessageDialog(null, "Goodbye!");
                        loggedIn = true;
                        break;
                    default:
                        JOptionPane.showMessageDialog(null, "Invalid choice");
                }
            }
        }
    }


    }

    private static void sendMessages() {
      int numMessageToSend = Integer.parseInt(JOptionPane.showInputDialog("Enter the number of messages you want to send"));
    Message[] messages = new Message[numMessageToSend];
    StringBuilder messageDetails = new StringBuilder();
    int numMessages = 0;
    for (int i = 0; i < numMessageToSend; i++) {
        numMessages++;
        String recipientCell = JOptionPane.showInputDialog("Enter the recipient's cell number (+27...)");
        String message = JOptionPane.showInputDialog("Enter the message");
        while (message.length() > 250) {
            JOptionPane.showMessageDialog(null, "Please enter a message of less than 250 characters");
            message = JOptionPane.showInputDialog("Enter the message");
        }

        Message msg = new Message(numMessages, recipientCell, message);
        if (msg.checkMessageID().contains("valid") && msg.checkRecipientCell().contains("valid")) {
            msg.messageHash = msg.createMessageHash().toUpperCase();
            JOptionPane.showMessageDialog(null, "Message Hash: " + msg.messageHash);
            JOptionPane.showMessageDialog(null, "Message sent successfully");
    Object[] options = {"Store Message", "Disregard Message"};
            int choice = JOptionPane.showOptionDialog(null, "What would you like to do with the message?", "Message Options", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);

            if (choice == 0) {
                storeMessageInJSON(msg);
                JOptionPane.showMessageDialog(null, "Message successfully stored");
                messageDetails.append("Message ID: ").append(msg.messageID).append("\n");
                messageDetails.append("Message Hash: ").append(msg.messageHash).append("\n");
                messageDetails.append("Recipient Cell: ").append(recipientCell).append("\n");
                messageDetails.append("Message: ").append(message).append("\n\n");
                messages[i] = msg;
            } else {
                numMessages--;
                JOptionPane.showMessageDialog(null, "Message disregarded");
            }
        } else {
            numMessages--;
          JOptionPane.showMessageDialog(null, "Invalid message ID or recipient cell number");
        }
    }
    if (messageDetails.length() > 0) {
        JOptionPane.showMessageDialog(null, "\n" + messageDetails.toString());
    }
    JOptionPane.showMessageDialog(null, "Total messages sent: " + numMessages);
}



    private static void storeMessageInJSON(Message msg) {
        try {
        Path path = Paths.get("messages.json");
        JsonArray jsonArray;
        if (Files.exists(path)) {
            JsonReader jsonReader = Json.createReader(Files.newBufferedReader(path));
            jsonArray = jsonReader.readArray();
            jsonReader.close();
        } else {
            jsonArray = Json.createArrayBuilder().build();
        }

        JsonObjectBuilder jsonObjectBuilder = Json.createObjectBuilder();
        jsonObjectBuilder.add("messageID", msg.messageID);
        jsonObjectBuilder.add("numMessageSent", msg.numMessageSent);
        jsonObjectBuilder.add("recipientCell", msg.recipientCell);
        jsonObjectBuilder.add("message", msg.message);
        jsonObjectBuilder.add("messageHash", msg.messageHash);

        JsonObject jsonObject = jsonObjectBuilder.build();
        JsonArrayBuilder jsonArrayBuilder = Json.createArrayBuilder();
        jsonArray.forEach(jsonArrayBuilder::add);
        jsonArrayBuilder.add(jsonObject); 

     try (JsonWriter jsonWriter = Json.createWriter(new FileWriter("messages.json"))) {
            jsonWriter.writeArray(jsonArrayBuilder.build());
        }
    } catch (IOException e) {
        JOptionPane.showMessageDialog(null, "Error storing message in JSON: " + e.getMessage());
    }

    }


    } 




 
