package frontend.faculty;

import backend.FacultyService;
import database.models.Course;
import frontend.BackScreen;
import frontend.ProtectedScreen;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OfferCourseScreen extends ProtectedScreen {
    public OfferCourseScreen() {
        title = "Offer a Course";
        option = "Offer Course";
        backScreen = new BackScreen("", "Back");
    }

    @Override
    public void process() {
        FacultyService service = FacultyService.getInstance();
        try {
            List<Course> allCoursesForOffering = service.getAllCoursesForOffering();
            subScreens = new ArrayList<>();
            for (Course c : allCoursesForOffering) {
                subScreens.add(new OfferedCourseDetailsScreen(c.getTitle(), c.getTitle(), c.getCode())); // TODO: Add formatting to next screen title
            }
        } catch (SQLException e) {
            System.out.println("Sorry! Some error occured.");
        }
    }
}
