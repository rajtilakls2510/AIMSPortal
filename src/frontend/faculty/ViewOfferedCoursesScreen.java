package frontend.faculty;

import backend.FacultyService;
import database.models.Course;
import frontend.BackScreen;
import frontend.ProtectedScreen;

import java.sql.SQLException;
import java.util.List;

public class ViewOfferedCoursesScreen extends ProtectedScreen {
    public ViewOfferedCoursesScreen() {
        title = "Offered Courses";
        option = "View Offered courses and their details";
        backScreen = new BackScreen("", "Back");
    }

    @Override
    public void process() {
        FacultyService service = FacultyService.getInstance();
        try {
            List<Course> allOfferedCourses = service.getAllOfferedCourses();
            for(Course c: allOfferedCourses)
                System.out.println(c); // TODO: Apply a formatter function to show courses
        } catch (SQLException e) {
            System.out.println("Sorry! Some error occured");
        }
    }
}
