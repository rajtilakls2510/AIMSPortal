package frontend.acadoffice;

import backend.AcadOfficeService;
import database.models.Course;
import frontend.BackScreen;
import frontend.ProtectedScreen;

import java.sql.SQLException;
import java.util.List;

public class CourseCatalogScreen extends ProtectedScreen {
    public CourseCatalogScreen() {
        title = "Course Catalogue";
        option = "Course catalog";
        subScreens.add(new CourseAddScreen());
        subScreens.add(new CourseEditScreen());
        subScreens.add(new CourseDeleteScreen());
        backScreen = new BackScreen();
    }

    @Override
    public void process() {
        AcadOfficeService service = AcadOfficeService.getInstance();

        List<Course> courses;
        try {
            courses = service.getCourses();
            System.out.println("Courses Available:- ");
            for (Course c : courses)
                System.out.println(c); // TODO: Apply a formatter function to show courses
        } catch (SQLException e) {
            System.out.println("Could not fetch courses from database!");
        }

    }
}
