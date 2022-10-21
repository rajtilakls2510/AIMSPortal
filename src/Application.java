import backend.CourseService;
import database.models.Course;
import database.repositories.Repository;
import frontend.WelcomeScreen;

import java.sql.SQLException;
import java.util.List;

public class Application {
    public static void main(String[] args) throws SQLException {
        /*Repository repo = new Repository();
        List<Course> courses = repo.getCourses();
        for (Course course : courses) {
            System.out.println(course+ "-->"+ course.getPrerequisites());
        }*/
        new WelcomeScreen().show();
        /*CourseService courseService = new CourseService(new Repository());
        List<Course> courses = courseService.getCourses();
        for (Course c : courses) {
            System.out.println(c + " : " + c.getPrerequisites());
        }*/
    }
}
