package frontend.acadoffice;

import backend.AcadOfficeService;
import database.models.CourseRegister;
import frontend.BackScreen;
import frontend.MessagePasser;
import frontend.ProtectedScreen;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class StudentGradeScreen extends ProtectedScreen {
    public StudentGradeScreen() {
        title = "View Student Grade";
        option = "Grade of a student";
        subScreens.add(new DownloadTranscriptScreen());
        backScreen = new BackScreen();
    }

    @Override
    public void process() {
        AcadOfficeService service = AcadOfficeService.getInstance();

        Scanner sc = new Scanner(System.in);
        System.out.print("Enter Student's Entry No (Press ENTER to ignore): ");
        String entry = sc.nextLine().strip();
        if(entry.equals(""))
            return;
        MessagePasser.getInstance().getMessages().put("Entry", entry);
        try {
            List<CourseRegister> registeredCourses = service.getGrade(entry);
            // TODO: Show formatted Courses
            Integer cgpa = service.getCgpa(entry);
            // TODO: Show Cgpa
        } catch (SQLException e) {
            System.out.println("Could not fetch Grades!");
        }

    }
}
