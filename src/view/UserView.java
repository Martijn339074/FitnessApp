package view;

import model.SporterModel;
import model.UserModel;

public class UserView {

    public void showLoginHeader() {
        System.out.println("\n--- Log In ---");
    }

    public void showSignUpHeader() {
        System.out.println("\n--- Sign Up ---");
    }

    public void showRolePrompt() {
        System.out.println("Sign up as:");
        System.out.println("1. Sporter");
        System.out.println("2. Coach");
        System.out.print("Choice: ");
    }

    public void showAccountMenu(UserModel user) {
        System.out.println("\n--- Account (" + user.getUsername() + ") ---");
        System.out.println("1. View my information");
        System.out.println("2. Update my information");
        if (user instanceof SporterModel) {
            System.out.println("3. Calculate BMI");
            System.out.println("4. Log out");
        } else {
            System.out.println("3. Log out");
        }
        System.out.print("Choice: ");
    }

    public void showBmi(SporterModel sporter) {
        System.out.println("\n--- BMI ---");
        System.out.println("Weight (kg): " + sporter.getWeightKg());
        System.out.println("Height (cm): " + sporter.getHeightCm());
        if (sporter.getWeightKg() <= 0 || sporter.getHeightCm() <= 0) {
            System.out.println("BMI cannot be calculated. Weight and height are missing.");
            return;
        }
        double bmi = sporter.calculateBmi();
        System.out.printf("BMI: %.1f%n", bmi);
        if (sporter.isHealthy()) {
            System.out.println("Healthy. Your BMI is in the healthy range (18.5 - 24.9).");
        } else if (bmi < 18.5) {
            System.out.println("Not healthy. Your BMI is below the healthy range (18.5 - 24.9).");
        } else {
            System.out.println("Not healthy. Your BMI is above the healthy range (18.5 - 24.9).");
        }
    }

    public void showUserInfo(UserModel user) {
        System.out.println("\n--- My Information ---");
        System.out.println("Role: " + user.getRole());
        System.out.println("Username: " + user.getUsername());
        System.out.println("Email: " + user.getEmail());
        System.out.println("Phone: " + user.getPhone());
        System.out.println("Address: " + user.getAddress());
        String details = user.getProfileDetails();
        if (!details.isEmpty()) {
            System.out.println(details);
        }
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
