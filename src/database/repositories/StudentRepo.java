package database.repositories;

import database.models.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentRepo extends Repository{

    public List<CourseRegister> getRegisteredCourses(Integer studentId) throws SQLException {
        ResultSet resultSet = conn.createStatement().executeQuery("select cr.id as crid, student_id, offer_id, grade, creditsreceived, cr.status as status, c.id as cid, c.title as title from ((courseregister cr inner join courseoffer co on cr.offer_id = co.id) inner join session s on co.session_id = s.id) inner join course c on c.id=co.course_id where s.status='ACTIVE' and cr.student_id = " + studentId + " and cr.status = 'ENROLLED'");
        List<CourseRegister> courseRegisters = new ArrayList<>();
        while(resultSet.next())
        {
            courseRegisters.add(
                    new CourseRegister(resultSet.getInt("crid"), new Student(
                            resultSet.getInt("student_id"),
                            null,
                            null,
                            null,
                            null,
                            null,
                            null,
                            null,
                            null
                    ),
                            new CourseOffer(resultSet.getInt("offer_id"), new Course(resultSet.getInt("cid"), null, resultSet.getString("title"), null, null), null, null),
                            resultSet.getInt("grade"),
                            resultSet.getInt("creditsreceived"),
                            CourseRegistrationStatus.valueOf(resultSet.getString("status"))
                    )
            );
        }
        return courseRegisters;
    }

    public void withdrawFromCourse(Integer registrationId) throws SQLException {
        conn.createStatement().executeUpdate("update courseregister set status = 'DROPPED' where id="+registrationId);
    }

    public MTPInfo getRegisteredMTP(Integer student_id) throws SQLException {
        // Return the MTPInfo of the student if he is already registered to that MTP. Otherwise return null
        ResultSet resultSet = conn.createStatement().executeQuery("select * from mtpinfo where student_id = " + student_id);
        if (!resultSet.isBeforeFirst() && resultSet.getRow() == 0) {
            return null;
        }
        return new MTPInfo(
                            resultSet.getInt("id"),
                            new Student(
                                    null,
                                    null,
                                    null,
                                    null,
                                    null,
                                    null,
                                    resultSet.getInt("student_id"),
                                    null,
                                    null
                            ),
                            new Faculty(
                                    null,
                                    null,
                                    null,
                                    null,
                                    null,
                                    null,
                                    resultSet.getInt("faculty_id")
                            ),
                            resultSet.getString("title"),
                            resultSet.getString("domains"),
                            resultSet.getInt("credits")
                    );
    }

    public List<MTPInfo> getOfferedMTPs() throws SQLException {
        // Return all MTPs which are offered but does not have any students working on it
        List<MTPInfo> mtpinfo = new ArrayList<>();

        ResultSet resultSet = conn.createStatement().executeQuery("select * from mtpinfo where student_id IS NULL");
        while(resultSet.next()) {
            mtpinfo.add(
                    new MTPInfo(
                            resultSet.getInt("id"),
                            new Student(
                                    null,
                                    null,
                                    null,
                                    null,
                                    null,
                                    null,
                                    null,
                                    null,
                                    null
                            ),
                            new Faculty(
                                    null,
                                    null,
                                    null,
                                    null,
                                    null,
                                    null,
                                    resultSet.getInt("faculty_id")
                            ),
                            resultSet.getString("title"),
                            resultSet.getString("domains"),
                            resultSet.getInt("credits")
                    )
            );
        }
        return mtpinfo;
    }

    public void registerForMtp(Integer mtpId, Integer student_id) throws SQLException {
        // Register this student in the MTP with mtpId. Throw SQLException if couldn't register
        ResultSet resultSet = conn.createStatement().executeQuery("select * from mtpinfo where student_id = " + student_id + " AND id = " + mtpId);
        if (!resultSet.isBeforeFirst() && resultSet.getRow() == 0) {
            conn.createStatement().executeUpdate("update mtpinfo set student_id = " + student_id + " where id = " + mtpId);
        }
    }
}
