package backend;

import database.models.Course;
import database.models.CourseRegister;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AcadOfficeService {
    private static AcadOfficeService instance;

    private AcadOfficeService()
    {}

    public static AcadOfficeService getInstance()
    {
        if(instance==null)
            instance = new AcadOfficeService();
        return instance;
    }

    public List<Course> getCourses() throws SQLException {
        // Fetch all the courses from database and return them
        return new ArrayList<>();
    }

    public Course getCourse(String courseCode) throws SQLException {
        // Fetch the course
        return null;
    }

    public List<CourseRegister> getGrade(String entry) throws SQLException {

        StudentService studentService = StudentService.getInstance();
        return studentService.getGrade(entry);
    }

    public Integer getCgpa(String entry) throws SQLException {

        StudentService studentService = StudentService.getInstance();
        return studentService.getCgpa(entry);
    }

    public void addCourse(Course course) throws SQLException {
        // Add a course
    }

    public void editCourse(Course course) throws SQLException{
        // Edit a course
    }
    public void deleteCourse(String courseCode) throws SQLException {
        // Delete a course
    }
}
