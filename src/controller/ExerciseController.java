package controller;

import java.util.List;
import java.util.Scanner;
import dao.ExerciseDAO;
import model.ExerciseModel;
import view.ExerciseView;

public class ExerciseController {
    private ExerciseView view;
    private Scanner scanner;
    private ExerciseDAO exerciseDAO = new ExerciseDAO();

    public ExerciseController(ExerciseView view, Scanner scanner) {
        this.view = view;
        this.scanner = scanner;
    }

    public void runExerciseMenu() {
        boolean back = false;

        while (!back) {
            List<ExerciseModel> exercises = exerciseDAO.findAll();
            view.showExerciseList(exercises);
            int choice = scanner.nextInt();
            scanner.nextLine();

            if (choice == 0) {
                back = true;
            } else if (choice >= 1 && choice <= exercises.size()) {
                view.showExerciseInfo(exercises.get(choice - 1));
            } else {
                view.showMessage("Invalid choice.");
            }
        }
    }
}
