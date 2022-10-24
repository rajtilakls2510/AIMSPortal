package frontend.faculty;

import backend.FacultyService;
import database.models.CourseRegister;
import database.models.Faculty;
import frontend.BackScreen;
import frontend.ProtectedScreen;
import frontend.acadoffice.MessagePasser;

import java.sql.SQLException;

public class OfferedCourseDetailsScreen extends ProtectedScreen {
    String courseCode;
    public OfferedCourseDetailsScreen(String title, String option, String courseCode) {
        this.title = title;
        this.option = option;
        this.courseCode = courseCode;
        subScreens.add(new GradeEntryScreen());
        backScreen = new BackScreen("", "Back");
    }

    @Override
    public void process() {
        FacultyService service = FacultyService.getInstance();
        try {
            CourseRegister courseDetails = service.getCourseDetails(courseCode);
            // TODO: Show course details
            // TODO: Show registered students and their grades

            MessagePasser.getInstance().getMessages().put("courseCode", courseCode);
        } catch (SQLException e) {
            System.out.println("Sorry! Some error occured");
        }
    }
}
