package backend;

import database.models.Course;
import database.models.CourseRegister;
import database.models.MTPInfo;

import java.sql.SQLException;
import java.util.List;

public class FacultyService {
    private static FacultyService instance;

    private FacultyService()
    {}

    public static FacultyService getInstance()
    {
        if(instance==null)
            instance = new FacultyService();
        return instance;
    }

    public List<Course> getAllOfferedCourses() throws SQLException {
        // Fetch all courses that this faculty is currently offering
        return null;
    }

    public List<Course> getAllCoursesForOffering() throws SQLException {
        // Fetch all active courses available for offering in this current session
        return null;
    }

    public CourseRegister getCourseDetails(String courseCode) throws SQLException {
        // Fetch the details of the course and its registered students
        return null;
    }

    public void gradeEntry(String entry, Integer grade, String courseCode) throws SQLException {
        // Enter the grade for student, throw SQLException if grade could not be entered
    }

    public List<MTPInfo> getMTPInfo() throws SQLException {
        // Fetch all the MTPs that the Faculty is offering. Use LEFT JOIN because of nullable student id FK
        return null;
    }

    public void addMTP(MTPInfo mtpInfo) throws SQLException {
        // Add MTP without student.
    }

    public void addMTPCredit(Integer mtpId, Integer credit) throws  SQLException {
        // Update the credit of the mtp
    }
}
