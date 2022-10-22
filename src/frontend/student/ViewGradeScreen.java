package frontend.student;

import backend.StudentService;
import database.models.CourseRegister;
import database.models.Student;
import frontend.BackScreen;
import frontend.LoggedInUser;
import frontend.ProtectedScreen;

import java.sql.SQLException;
import java.util.List;

public class ViewGradeScreen extends ProtectedScreen {
    public ViewGradeScreen() {
        title = "Your grades uptill date!";
        option = "View Grades and CGPA";
        backScreen = new BackScreen("", "Back");
    }

    @Override
    public void process() {
        StudentService service = StudentService.getInstance();

        try {
            Student student = service.getStudentDetails(LoggedInUser.getInstance().getId());
            List<CourseRegister> grade = service.getGrade(student.getEntryNo());
            Integer cgpa = service.getCgpa(student.getEntryNo());
            // TODO: Show all grades
        } catch (SQLException e) {
            System.out.println("Something went wrong");
        }
    }
}
