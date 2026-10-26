package controller;

import java.util.List;
import java.util.Scanner;
import model.CoachModel;
import view.CoachView;
import dao.CoachDAO;

public class CoachController {
    private CoachView view;
    private Scanner scanner;
    private CoachDAO coachDAO = new CoachDAO();

    public CoachController(CoachView view, Scanner scanner) {
        this.view = view;
        this.scanner = scanner;
    }

    public void runCoachMenu() {
        boolean back = false;

        while (!back) {
            view.showCoachMenu();
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    createCoach();
                    break;
                case 2:
                    showAllCoaches();
                    break;
                case 3:
                    back = true;
                    break;
                default:
                    view.showMessage("Invalid choice.");
            }
        }
    }

    public void showAllCoaches() {
        List<CoachModel> coaches = coachDAO.findAll();
        view.showAllCoaches(coaches);
    }

    public void createCoach() {
        view.showCreateCoachHeader();

        view.prompt("Username");
        String username = scanner.nextLine();
        view.prompt("Password");
        String password = scanner.nextLine();
        view.prompt("Email");
        String email = scanner.nextLine();
        view.prompt("Phone");
        String phone = scanner.nextLine();
        view.prompt("Address");
        String address = scanner.nextLine();
        view.prompt("Name");
        String name = scanner.nextLine();
        view.prompt("Specialty");
        String specialty = scanner.nextLine();

        CoachModel coach = new CoachModel(
            0, username, password, email, phone, address, name, specialty
        );

        if (coachDAO.create(coach)) {
            view.showMessage("Coach created successfully.");
        } else {
            view.showMessage("Could not create coach. Check username is unique.");
        }
    }
}
