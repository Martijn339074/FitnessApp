package controller;

import java.util.Scanner;
import dao.CoachDAO;
import dao.SporterDAO;
import dao.UserDAO;
import model.CoachModel;
import model.SporterModel;
import model.UserModel;
import view.UserView;

public class UserController {
    private UserView view;
    private Scanner scanner;
    private UserDAO userDAO = new UserDAO();
    private SporterDAO sporterDAO = new SporterDAO();
    private CoachDAO coachDAO = new CoachDAO();
    private UserModel currentUser;

    public UserController(UserView view, Scanner scanner) {
        this.view = view;
        this.scanner = scanner;
    }

    public void signUp() {
        view.showSignUpHeader();

        String username = readRequired("Username");
        if (userDAO.findByUsername(username) != null) {
            view.showMessage("That username is already taken.");
            return;
        }

        String password = readRequiredRaw("Password");
        String email = readRequired("Email");
        String phone = readRequired("Phone");
        String address = readRequired("Address");
        int role = readRole();

        boolean created;
        if (role == 1) {
            created = signUpSporter(username, password, email, phone, address);
        } else {
            created = signUpCoach(username, password, email, phone, address);
        }

        if (created) {
            view.showMessage("Account created. You can log in now.");
        } else {
            view.showMessage("Could not create account. Check that the username is unique.");
        }
    }

    private boolean signUpSporter(String username, String password, String email, String phone, String address) {
        String name = readRequired("Name");
        int age = readPositiveInt("Age");
        String gender = readRequired("Gender");
        double weightKg = readPositiveDouble("Weight (kg)");
        double heightCm = readPositiveDouble("Height (cm)");

        SporterModel sporter = new SporterModel(
            0, username, password, email, phone, address,
            name, age, gender, weightKg, heightCm
        );
        if (!sporterDAO.create(sporter)) {
            return false;
        }
        view.showBmi(sporter);
        return true;
    }

    private boolean signUpCoach(String username, String password, String email, String phone, String address) {
        String name = readRequired("Name");
        String specialty = readRequired("Specialty");
        CoachModel coach = new CoachModel(
            0, username, password, email, phone, address, name, specialty
        );
        return coachDAO.create(coach);
    }

    private int readRole() {
        while (true) {
            view.showRolePrompt();
            String choice = scanner.nextLine().trim();
            if (choice.equals("1") || choice.equals("2")) {
                return Integer.parseInt(choice);
            }
            view.showMessage("Choose 1 for sporter or 2 for coach.");
        }
    }

    private int readPositiveInt(String label) {
        while (true) {
            String value = readRequired(label);
            try {
                int number = Integer.parseInt(value);
                if (number > 0) {
                    return number;
                }
            } catch (NumberFormatException ignored) {
                // Ask again below.
            }
            view.showMessage(label + " must be a whole number greater than 0.");
        }
    }

    private double readPositiveDouble(String label) {
        while (true) {
            String value = readRequired(label).replace(',', '.');
            try {
                double number = Double.parseDouble(value);
                if (number > 0) {
                    return number;
                }
            } catch (NumberFormatException ignored) {
                // Ask again below.
            }
            view.showMessage(label + " must be a number greater than 0.");
        }
    }

    public void login() {
        view.showLoginHeader();
        view.prompt("Username");
        String username = scanner.nextLine().trim();
        view.prompt("Password");
        String password = scanner.nextLine();

        UserModel user = userDAO.findByUsername(username);
        if (user == null || !user.getPassword().equals(password)) {
            view.showMessage("Invalid username or password.");
            return;
        }

        currentUser = user;
        view.showMessage("Welcome, " + user.getUsername() + " (" + user.getRole() + ").");
        runAccountMenu();
    }

    private void runAccountMenu() {
        boolean loggedIn = true;

        while (loggedIn) {
            boolean sporter = currentUser instanceof SporterModel;
            view.showAccountMenu(currentUser);
            int choice = scanner.nextInt();
            scanner.nextLine();

            if (choice == 1) {
                view.showUserInfo(currentUser);
            } else if (choice == 2) {
                updateInformation();
            } else if (sporter && choice == 3) {
                view.showBmi((SporterModel) currentUser);
            } else if (choice == (sporter ? 4 : 3)) {
                view.showMessage("Logged out.");
                currentUser = null;
                loggedIn = false;
            } else {
                view.showMessage("Invalid choice.");
            }
        }
    }

    private void updateInformation() {
        view.showUpdateHeader();

        view.prompt("Email [" + currentUser.getEmail() + "]");
        String email = scanner.nextLine().trim();
        view.prompt("Phone [" + currentUser.getPhone() + "]");
        String phone = scanner.nextLine().trim();
        view.prompt("Address [" + currentUser.getAddress() + "]");
        String address = scanner.nextLine().trim();
        view.prompt("New password");
        String password = scanner.nextLine();

        if (!email.isEmpty()) {
            currentUser.setEmail(email);
        }
        if (!phone.isEmpty()) {
            currentUser.setPhone(phone);
        }
        if (!address.isEmpty()) {
            currentUser.setAddress(address);
        }
        if (!password.isEmpty()) {
            currentUser.setPassword(password);
        }

        if (userDAO.update(currentUser)) {
            view.showMessage("Information updated.");
        } else {
            view.showMessage("Could not update information.");
        }
    }

    private String readRequired(String label) {
        while (true) {
            String value = readRequiredRaw(label).trim();
            if (!value.isEmpty()) {
                return value;
            }
            view.showMessage(label + " cannot be empty.");
        }
    }

    private String readRequiredRaw(String label) {
        while (true) {
            view.prompt(label);
            String value = scanner.nextLine();
            if (!value.isEmpty()) {
                return value;
            }
            view.showMessage(label + " cannot be empty.");
        }
    }
}
