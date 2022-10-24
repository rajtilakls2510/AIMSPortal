package frontend.acadoffice;

import backend.AcadOfficeService;
import database.models.Course;
import frontend.BackScreen;
import frontend.ProtectedScreen;

import java.sql.SQLException;

public class CourseAddScreen extends ProtectedScreen {
    public CourseAddScreen() {
        title = "Add a Course";
        option = "Add Course";
        backScreen = new BackScreen();
    }

    @Override
    public void process() {
        AcadOfficeService service = AcadOfficeService.getInstance();
        System.out.println("Enter course details:-");
        try {
            // TODO: Take Course details from user
            service.addCourse(new Course(null, null, null, null, null));
        } catch (SQLException e) {
            System.out.println("Could not add course! Something went wrong");
        }
    }

    @Override
    public void show() throws RuntimeException {
        preScreenProcess();
        clearConsole();
        System.out.println("\n" + title + "\n");
        process();
        postScreenProcess();
    }
}
