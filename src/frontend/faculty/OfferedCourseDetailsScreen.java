package frontend.faculty;

import backend.FacultyService;
import database.models.CourseRegister;
import frontend.BackScreen;
import frontend.ProtectedScreen;
import frontend.MessagePasser;

import java.sql.SQLException;

public class OfferedCourseDetailsScreen extends ProtectedScreen {
    Integer courseId;
    public OfferedCourseDetailsScreen(String title, String option, Integer courseId) {
        this.title = title;
        this.option = option;
        this.courseId = courseId;
        subScreens.add(new GradeEntryScreen());
        backScreen = new BackScreen();
    }

    @Override
    public void process() {
        FacultyService service = FacultyService.getInstance();
        try {
            CourseRegister courseDetails = service.getCourseDetails(courseId);
            // TODO: Show course details
            // TODO: Show registered students and their grades

            MessagePasser.getInstance().getMessages().put("courseCode", courseId);
        } catch (SQLException e) {
            System.out.println("Sorry! Some error occured");
        }
    }
}
