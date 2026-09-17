package controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import model.SporterModel;
import view.SporterView;

public class SporterController {
    private SporterView sporterView;
    private SporterModel sporter;
    private List<SporterModel> sporters = new ArrayList<>();

    public SporterController(SporterView sporterView) {
        this.sporterView = sporterView;
    }

    public void readAllSporters() {
        if (sporters.isEmpty()) {
            sporterView.showMessage("No sporters found.");
            return;
        }
        for (int i = 0; i < sporters.size(); i++) {
            sporterView.displaySporterInfo(sporters.get(i), i);
        }
    } 

    public void displaySporterInfo() {
        sporterView.displaySporterInfo(sporter);
    }
}
