import frontend.WelcomeScreen;

import java.sql.SQLException;

public class Application {
    public static void main(String[] args) throws SQLException {
        /*Repository repo = new Repository();
        List<Course> courses = repo.getCourses();
        for (Course course : courses) {
            System.out.println(course+ "-->"+ course.getPrerequisites());
        }*/
        new WelcomeScreen().show();
    }
}
