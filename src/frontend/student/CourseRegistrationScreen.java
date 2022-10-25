package frontend.student;

import backend.StudentService;
import database.models.CourseOffer;
import frontend.BackScreen;
import frontend.ProtectedScreen;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CourseRegistrationScreen extends ProtectedScreen {
    public CourseRegistrationScreen() {
        title = "Course Registration";
        option = "Register for a Course";
        backScreen = new BackScreen();
    }

    @Override
    public void process() {
        StudentService service = StudentService.getInstance();

        try {
            List<CourseOffer> offeredCourses = service.getAllOfferedCourses();
            subScreens = new ArrayList<>();
            for(CourseOffer c : offeredCourses)
            {
                subScreens.add(new CourseRegisterConfirmScreen(c.getCourse().getTitle(), c.getCourse().getTitle(), c.getOfferId())); // TODO: format title
            }

        } catch (SQLException e) {
            System.out.println("Something went wrong");
        }

    }
}
