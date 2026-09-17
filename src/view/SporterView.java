package view;
import model.SporterModel;
import controller.SporterController;

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
}
