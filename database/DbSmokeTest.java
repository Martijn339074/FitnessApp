import dao.ExerciseDAO;
import dao.SporterDAO;
import dao.UserDAO;
import model.UserModel;

/** Run from project root: javac -d bin -sourcepath src -cp "lib/*" database/DbSmokeTest.java && java -cp "bin:lib/*" DbSmokeTest */
public class DbSmokeTest {
    public static void main(String[] args) {
        var sporters = new SporterDAO().findAll();
        var exercises = new ExerciseDAO().findAll();
        UserModel user = new UserDAO().findByUsername("john");

        System.out.println("sporters=" + sporters.size());
        System.out.println("exercises=" + exercises.size());
        System.out.println("john=" + (user != null ? user.getEmail() : "null"));

        if (sporters.size() < 2 || exercises.size() < 5 || user == null) {
            System.exit(1);
        }
    }
}
