package frontend.student;

import backend.StudentService;
import database.models.CourseRegister;
import frontend.BackScreen;
import frontend.ProtectedScreen;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class WithdrawCourseScreen extends ProtectedScreen {
    public WithdrawCourseScreen() {
        title = "Withdraw from a Course";
        option = "Withdraw Course";
        backScreen = new BackScreen("", "Back");
    }

    @Override
    public void process() {
        StudentService service = StudentService.getInstance();
        try {
            List<CourseRegister> registeredCourses = service.getRegisteredCourses();
            subScreens = new ArrayList<>();
            for(CourseRegister c: registeredCourses)
                subScreens.add(new WithdrawConfirmScreen(c.getOffer().getCourse().getTitle(), c.getOffer().getCourse().getTitle(), c.getId())); // TODO: Format title
        } catch (SQLException e) {
            System.out.println("Something went wrong");
        }
    }
}
