package backend;

import database.models.CourseRegister;

import java.sql.SQLException;
import java.util.List;

public class StudentService {
    private static StudentService instance;

    private StudentService()
    {}

    public static StudentService getInstance()
    {
        if(instance==null)
            instance = new StudentService();
        return instance;
    }

    public List<CourseRegister> getGrade(String entry) throws SQLException {
        // Return the list of registered Courses
        return null;
    }

    public Integer getCgpa(String entry) throws SQLException {
        // Return the cgpa of the student
        return null;
    }
}
