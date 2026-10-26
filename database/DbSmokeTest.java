import dao.CoachDAO;
import dao.ExerciseDAO;
import dao.SporterDAO;
import dao.UserDAO;
import model.CoachModel;
import model.SporterModel;
import model.UserModel;

/** Run from project root: javac -d bin -sourcepath src -cp "lib/*" database/DbSmokeTest.java && java -cp "bin:lib/*" DbSmokeTest */
public class DbSmokeTest {
    public static void main(String[] args) {
        var sporters = new SporterDAO().findAll();
        var coaches = new CoachDAO().findAll();
        var exercises = new ExerciseDAO().findAll();
        UserModel user = new UserDAO().findByUsername("john");
        UserModel coach = new UserDAO().findByUsername("alex");

        System.out.println("sporters=" + sporters.size());
        System.out.println("coaches=" + coaches.size());
        System.out.println("exercises=" + exercises.size());
        System.out.println("john=" + (user != null ? user.getRole() : "null"));
        System.out.println("alex=" + (coach != null ? coach.getRole() : "null"));

        if (sporters.size() < 2 || coaches.size() < 1 || exercises.size() < 5
                || !(user instanceof SporterModel)
                || !(coach instanceof CoachModel)) {
            System.exit(1);
        }
    }
}
