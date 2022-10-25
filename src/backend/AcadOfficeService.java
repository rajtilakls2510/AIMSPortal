package backend;

import database.models.Course;
import database.models.CourseRegister;
import database.repositories.AcadOfficeRepo;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class AcadOfficeService {
    AcadOfficeRepo acadOfficeRepo;
    private static AcadOfficeService instance;

    private AcadOfficeService() {
        acadOfficeRepo = new AcadOfficeRepo();
    }

    public static AcadOfficeService getInstance() {
        if (instance == null)
            instance = new AcadOfficeService();
        return instance;
    }

    public List<Course> getCourses() throws SQLException {
        // Fetch all the courses from database and return them
        List<Course> course = acadOfficeRepo.getCourses();
        return course;
    }

    public Optional<Course> getCourse(String courseCode) throws SQLException {
        // Fetch the course
        return acadOfficeRepo.getCourse(courseCode);
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
        acadOfficeRepo.addCourse(course);
    }

    public void editCourse(Course course) throws SQLException {
        acadOfficeRepo.editCourseDetails(course);
        acadOfficeRepo.deletePrerequisites(course.getId());
        acadOfficeRepo.addPrerequisites(course);
    }

    public void deleteCourse(String courseCode) throws SQLException {
        acadOfficeRepo.deleteCourse(courseCode);
    }
}
