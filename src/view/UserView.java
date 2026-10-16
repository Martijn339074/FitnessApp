package view;

import model.UserModel;

public class UserView {

    public void showLoginHeader() {
        System.out.println("\n--- Log In ---");
    }

    public void showSignUpHeader() {
        System.out.println("\n--- Sign Up ---");
    }

    public void showAccountMenu(String username) {
        System.out.println("\n--- Account (" + username + ") ---");
        System.out.println("1. View my information");
        System.out.println("2. Update my information");
        System.out.println("3. Log out");
        System.out.print("Choice: ");
    }

    public void showUserInfo(UserModel user) {
        System.out.println("\n--- My Information ---");
        System.out.println("Username: " + user.getUsername());
        System.out.println("Email: " + user.getEmail());
        System.out.println("Phone: " + user.getPhone());
        System.out.println("Address: " + user.getAddress());
    }

    public void showUpdateHeader() {
        System.out.println("\n--- Update Information ---");
        System.out.println("Leave a field blank to keep the current value.");
    }

    public void prompt(String label) {
        System.out.print(label + ": ");
    }

    public void showMessage(String message) {
        System.out.println(message);
    }
}
