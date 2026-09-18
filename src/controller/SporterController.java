package controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import model.SporterModel;
import view.SporterView;

public class SporterController {
    private SporterView view;
    private Scanner scanner;
    private SporterModel sporter;
    private List<SporterModel> sporters = new ArrayList<>();

    public SporterController(SporterView view, Scanner scanner) {
        this.view = view;
        this.scanner = scanner;
    }   

    public void runSporterMenu() {
        boolean back = false;
    
        while (!back) {
            view.showSporterMenu();
            int choice = scanner.nextInt();
    
            switch (choice) {
                case 1:
                    // create sporter (later)
                    break;
                case 2:
                    view.displayAllSporters(sporters);
                    break;
                case 3:
                    // view one sporter (later)
                    break;
                case 6:
                    back = true;
                    break;
                default:
                    view.showMessage("Invalid choice.");
            }
        }
    }

    public void displaySporterInfo() {
        view.displaySporterInfo(sporter);
    }
}
