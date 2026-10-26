package view;

import java.util.List;
import model.CoachModel;

public class CoachView {

    public void displayCoachInfo(CoachModel coach) {
        System.out.println("Coach Info");
        System.out.println("Name: " + coach.getName());
        System.out.println("Specialty: " + coach.getSpecialty());
        System.out.println("Email: " + coach.getEmail());
        System.out.println("Phone: " + coach.getPhone());
        System.out.println("Address: " + coach.getAddress());
    }

    public void showCoachMenu() {
        System.out.println("\n--- Coaches ---");
        System.out.println("1. Create coach");
        System.out.println("2. View all coaches");
        System.out.println("3. Back");
        System.out.print("Choice: ");
    }

    public void showAllCoaches(List<CoachModel> coaches) {
        if (coaches.isEmpty()) {
            showMessage("No coaches found.");
            return;
        }
        System.out.println("\n--- All Coaches ---");
        for (int i = 0; i < coaches.size(); i++) {
            System.out.println("\nCoach #" + (i + 1));
            displayCoachInfo(coaches.get(i));
        }
    }

    public void showCreateCoachHeader() {
        System.out.println("\n--- Create Coach ---");
    }

    public void prompt(String label) {
        System.out.print(label + ": ");
    }

    public void showMessage(String message) {
        System.out.println(message);
    }
}
