package frontend.acadoffice;

import backend.AcadOfficeService;
import database.models.Course;
import frontend.BackScreen;
import frontend.ProtectedScreen;

import java.sql.SQLException;
import java.util.Scanner;

public class CourseEditScreen extends ProtectedScreen {
    public CourseEditScreen() {
        title = "Edit a Course";
        option = "Edit Course";
        backScreen = new BackScreen();
    }

    @Override
    public void process() {
        AcadOfficeService service = AcadOfficeService.getInstance();
        System.out.println("Enter course details:-");

        try {
            Scanner sc = new Scanner(System.in);
            System.out.print("Enter course code you want to edit: ");
            String courseCode = sc.nextLine().strip();
            Course course = service.getCourse(courseCode);
            // TODO: Fetch Course details and edit the course object
            service.editCourse(course);
        } catch (SQLException e) {
            System.out.println("Could not edit course! Something went wrong");
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
