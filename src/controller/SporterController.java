package controller;

import java.util.List;
import java.util.Scanner;
import model.SporterModel;
import view.SporterView;
import dao.SporterDAO;

public class SporterController {
    private SporterView view;
    private Scanner scanner;
    private SporterDAO sporterDAO = new SporterDAO();

    public SporterController(SporterView view, Scanner scanner) {
        this.view = view;
        this.scanner = scanner;
    }

    public void runSporterMenu() {
        boolean back = false;

        while (!back) {
            view.showSporterMenu();
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    createSporter();
                    break;
                case 2:
                    showAllSporters();
                    break;
                case 6:
                    back = true;
                    break;
                default:
                    view.showMessage("Invalid choice.");
            }
        }
    }

    public void showAllSporters() {
        List<SporterModel> sporters = sporterDAO.findAll();
        view.showAllSporters(sporters);
    }

    public void createSporter() {
        view.showCreateSporterHeader();

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
        view.prompt("Age");
        int age = scanner.nextInt();
        scanner.nextLine();
        view.prompt("Gender");
        String gender = scanner.nextLine();

        SporterModel sporter = new SporterModel(
            0, username, password, email, phone, address, name, age, gender
        );

        if (sporterDAO.create(sporter)) {
            view.showMessage("Sporter created successfully.");
        } else {
            view.showMessage("Could not create sporter. Check username is unique.");
        }
    }
}
