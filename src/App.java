import java.util.Scanner;
import view.ExerciseView;
import view.SporterView;
import view.UserView;
import controller.ExerciseController;
import controller.SporterController;
import controller.UserController;

public class App {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;
        SporterView sporterView = new SporterView();
        SporterController sporterController = new SporterController(sporterView, scanner);
        UserView userView = new UserView();
        UserController userController = new UserController(userView, scanner);
        ExerciseView exerciseView = new ExerciseView();
        ExerciseController exerciseController = new ExerciseController(exerciseView, scanner);

        while (running) { // start app
            System.out.println("Welcome to FitnessApp");
            System.out.println("Please select an option: ");
            System.out.println("1. Log in");
            System.out.println("2. Sign up");
            System.out.println("3. See sporter info");
            System.out.println("4. Exercises");
            System.out.println("5. Exit");
            System.out.print("Choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    userController.login();
                    break;
                case 2:
                    userController.signUp();
                    break;
                case 3:
                    sporterController.runSporterMenu();
                    break;
                case 4:
                    exerciseController.runExerciseMenu();
                    break;
                case 5:
                    System.out.println("Exiting");
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice.");
                    break;
            }
        }

        scanner.close();
    }
}