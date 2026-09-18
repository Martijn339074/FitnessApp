package view;
import model.SporterModel;
import controller.SporterController;
import java.util.List;

public class SporterView {
    public void displaySporterInfo(SporterModel sporter) {
        System.out.println("Sporter Info");
        System.out.println("Name: " + sporter.getName());
        System.out.println("Age: " + sporter.getAge());
        System.out.println("Gender: " + sporter.getGender());
        System.out.println("Email: " + sporter.getEmail());
        System.out.println("Phone: " + sporter.getPhone());
        System.out.println("Address: " + sporter.getAddress());
    }

    public void showSporterMenu() {
        System.out.println("\n--- Sporter CRUD ---");
        System.out.println("1. Create sporter");
        System.out.println("2. View all sporters");
        System.out.println("3. View one sporter");
        System.out.println("4. Update sporter");
        System.out.println("5. Delete sporter");
        System.out.println("6. Back");
        System.out.print("Choice: ");
    }

    public void displayAllSporters(List<SporterModel> sporters) {
        if (sporters.isEmpty()) {
            showMessage("No sporters found.");
            return;
        }
        System.out.println("\n--- All Sporters ---");
        for (int i = 0; i < sporters.size(); i++) {
            System.out.println("\nSporter #" + (i + 1));
            displaySporterInfo(sporters.get(i));
        }
    }

    public void showMessage(String message) {
        System.out.println(message);
    }
}
