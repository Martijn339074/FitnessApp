import java.util.Scanner;
import view.SporterView;
import controller.SporterController;

public class App {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        boolean running = true; 
        SporterView sporterView = new SporterView();
        SporterController sporterController = new SporterController(sporterView, scanner);

        while (running) { // start app
            System.out.println("Welcome to FitnessApp");
            System.out.println("Please select an option: ");
            System.out.println("1. Log in");
            System.out.println("2. Sign up");
            System.out.println("3. See sporter info");
            System.out.println("4. Exit");
            System.out.print("Choice: ");

            int choice = scanner.nextInt();
            
            switch (choice) {
                case 1:
                    System.err.println("Log In");
                    break;
                case 2:
                    System.err.println("Sign Up");
                    break;
                case 3:
                    sporterController.runSporterMenu();
                    break;
                case 4:
                    System.err.println("Exiting");
                    running = false;
                    break;
                default:
                    break;
            }
        }

        scanner.close();
    }
}