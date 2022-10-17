import database.models.Course;
import database.repositories.Repository;

import java.sql.SQLException;
import java.util.List;

public class Application {
    public static void main(String[] args) throws SQLException {
        Repository repo = new Repository();
        List<Course> courses = repo.getCourses();
        for (Course course : courses) {
            System.out.println(course);
        }
    }
}
