package frontend.student;

import backend.StudentService;
import database.models.CourseRegister;
import database.models.Student;
import frontend.BackScreen;
import frontend.LoggedInUser;
import frontend.ProtectedScreen;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class ViewGradeScreen extends ProtectedScreen {
    public ViewGradeScreen() {
        title = "Your grades uptill date!";
        option = "View Grades and CGPA";
        backScreen = new BackScreen();
    }

    @Override
    public void process() {
        StudentService service = StudentService.getInstance();

        try {
            Optional<Student> studentDetails = service.getStudentDetails(LoggedInUser.getInstance().getId());
            Student student = studentDetails.orElseThrow(SQLException::new);
            List<CourseRegister> grade = service.getGrade(student.getStudentId());
            for(CourseRegister cr: grade)
                System.out.println(cr);
            Integer cgpa = service.getCgpa(student.getEntryNo());
            // TODO: Show all grades
        } catch (SQLException e) {
            System.out.println("Something went wrong");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
            }
        }
    }
}
