package view;

import java.util.List;
import model.ExerciseModel;

public class ExerciseView {

    public void showExerciseList(List<ExerciseModel> exercises) {
        System.out.println("\n--- Exercises ---");
        if (exercises.isEmpty()) {
            System.out.println("No exercises found.");
        }
        for (int i = 0; i < exercises.size(); i++) {
            ExerciseModel exercise = exercises.get(i);
            System.out.println((i + 1) + ". " + exercise.getName()
                + " (" + exercise.getMuscleGroup() + ")");
        }
        System.out.println("0. Back");
        System.out.print("Choice: ");
    }

    public void showExerciseInfo(ExerciseModel exercise) {
        System.out.println("\n--- " + exercise.getName() + " ---");
        System.out.println("Muscle group: " + exercise.getMuscleGroup());
        System.out.println(exercise.getDescription());
    }

    public void showMessage(String message) {
        System.out.println(message);
    }
}
