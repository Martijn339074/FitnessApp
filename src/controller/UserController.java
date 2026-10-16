package controller;

import java.util.Scanner;
import dao.UserDAO;
import model.UserModel;
import view.UserView;

public class UserController {
    private UserView view;
    private Scanner scanner;
    private UserDAO userDAO = new UserDAO();
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

        UserModel user = new UserModel(0, username, password, email, phone, address);
        if (userDAO.create(user)) {
            view.showMessage("Account created. You can log in now.");
        } else {
            view.showMessage("Could not create account. Check that the username is unique.");
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
        view.showMessage("Welcome, " + user.getUsername() + ".");
        runAccountMenu();
    }

    private void runAccountMenu() {
        boolean loggedIn = true;

        while (loggedIn) {
            view.showAccountMenu(currentUser.getUsername());
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    view.showUserInfo(currentUser);
                    break;
                case 2:
                    updateInformation();
                    break;
                case 3:
                    view.showMessage("Logged out.");
                    currentUser = null;
                    loggedIn = false;
                    break;
                default:
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
