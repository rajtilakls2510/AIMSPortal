package frontend.student;

import backend.StudentService;
import frontend.BackScreen;
import frontend.ProtectedScreen;

import java.sql.SQLException;

public class CheckGraduationScreen extends ProtectedScreen {
    public CheckGraduationScreen() {
        title = "Let's see if you are graduated";
        option = "Check Graduation";
        backScreen = new BackScreen();
    }

    @Override
    public void process() {
        StudentService service = StudentService.getInstance();
        try {
            service.checkGraduation();
            // TODO: Display whether the student has graduated or not
        } catch (SQLException e) {
            System.out.println("Something went wrong");
        }
    }
}
