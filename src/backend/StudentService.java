package backend;

import database.models.CourseOffer;
import database.models.CourseRegister;
import database.models.MTPInfo;
import database.models.Student;
import database.repositories.StudentRepo;
import frontend.LoggedInUser;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentService {
    private static StudentService instance;
    private StudentRepo studentRepo;
    private StudentService() {
        studentRepo = new StudentRepo();
    }

    public static StudentService getInstance() {
        if (instance == null)
            instance = new StudentService();
        return instance;
    }

    public Student getStudentDetails(Integer id) throws SQLException {
        // Fetch the details of the student with this id
        return null;
    }

    public List<CourseRegister> getGrade(String entry) throws SQLException {
        // Return the list of registered Courses and their grades and statuses
        return null;
    }

    public Integer getCgpa(String entry) throws SQLException {
        // Return the cgpa of the student
        return null;
    }

    public List<CourseOffer> getAllOfferedCourses() throws SQLException {
        // Return the list of all offered courses for the current session
        return new ArrayList<>();
    }

    public CourseOffer getCourseOfferDetails(Integer offerId) throws SQLException {
        // Return the course offer using the offerId
        return null;
    }

    public void registerforOffer(Integer offerId) throws SQLException{
        // Register the current logged in student to the offered course if constraints permit. Throw SQLException if couldn't register
    }

    public List<CourseRegister> getRegisteredCourses() throws SQLException {
        // Return the list of all registed courses for the current session
        return studentRepo.getRegisteredCourses(LoggedInUser.getInstance().getId());
    }

    public void withdrawFromCourse(Integer registrationId) throws SQLException {
        // Set the status of the course registration to DROPPED. Throw SQLException if couldn't drop
        studentRepo.withdrawFromCourse(registrationId);
    }

    public boolean checkGraduation() throws SQLException {
        // Check whether the student has graduated or not.
        return true;
    }

    public MTPInfo getRegisteredMTP() throws SQLException {
        // Return the MTPInfo of the student if he is already reistered to that MTP. Otherwise return null
        return null;
    }

    public List<MTPInfo> getOfferedMTPs() throws SQLException {
        // Return all MTPs which are offered but does not have any students working on it
        return null;
    }

    public void registerForMtp(Integer mtpId) throws SQLException {
        // Register this student in the MTP with mtpId. Throw SQLException if couldn't register
    }
}
