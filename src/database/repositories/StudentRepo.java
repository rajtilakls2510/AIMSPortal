package database.repositories;

import database.models.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentRepo extends Repository {

    public List<CourseRegister> getRegisteredCourses(Integer studentId) throws SQLException {
        ResultSet resultSet = conn.createStatement().executeQuery("select cr.id as crid, student_id, offer_id, grade, creditsreceived, cr.status as status, c.id as cid, c.title as title, c.credit as credit from ((courseregister cr inner join courseoffer co on cr.offer_id = co.id) inner join session s on co.session_id = s.id) inner join course c on c.id=co.course_id where s.status='ACTIVE' and cr.student_id = " + studentId + " and cr.status = 'ENROLLED'");
        List<CourseRegister> courseRegisters = new ArrayList<>();
        while (resultSet.next()) {
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
                            new CourseOffer(resultSet.getInt("offer_id"), new Course(resultSet.getInt("cid"), null, resultSet.getString("title"), null, resultSet.getString("credit")), null, null),
                            resultSet.getInt("grade"),
                            resultSet.getInt("creditsreceived"),
                            CourseRegistrationStatus.valueOf(resultSet.getString("status"))
                    )
            );
        }
        return courseRegisters;
    }

    public List<CourseRegister> getAllSessionRegisteredCourses(Integer studentId) throws SQLException {
        ResultSet resultSet = conn.createStatement().executeQuery("select cr.id as crid, student_id, offer_id, grade, creditsreceived, cr.status as status, c.id as cid, c.title as title, c.credit as credit from (courseregister cr inner join courseoffer co on cr.offer_id = co.id) inner join course c on c.id=co.course_id where cr.student_id = " + studentId + " and cr.status = 'COMPLETED'");
        List<CourseRegister> courseRegisters = new ArrayList<>();
        while (resultSet.next()) {
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
                            new CourseOffer(resultSet.getInt("offer_id"), new Course(resultSet.getInt("cid"), null, resultSet.getString("title"), null, resultSet.getString("credit")), null, null),
                            resultSet.getInt("grade"),
                            resultSet.getInt("creditsreceived"),
                            CourseRegistrationStatus.valueOf(resultSet.getString("status"))
                    )
            );
        }
        return courseRegisters;
    }

    public void withdrawFromCourse(Integer registrationId) throws SQLException {
        conn.createStatement().executeUpdate("update courseregister set status = 'DROPPED' where id=" + registrationId);
    }

    public List<CourseOffer> getAllOfferedCourses(Integer studentId) throws SQLException {
        ResultSet resultSet = conn.createStatement().executeQuery("select co.id as id, c.id as cid, c.code as code, c.title as title, c.credit as credit, co.faculty_id as faculty_id from (courseoffer co inner join session s on co.session_id = s.id) inner join course c on co.course_id = c.id where s.status='ACTIVE' and co.id not in (select DISTINCT(co.id) from (courseoffer co inner join session s on co.session_id = s.id) left join courseregister cr on co.id = cr.offer_id where s.status='ACTIVE' and student_id = " + studentId + ")");
        List<CourseOffer> courseOffers = new ArrayList<>();
        while (resultSet.next()) {
            ResultSet resultSet1 = conn.createStatement().executeQuery("select f.id as id, user_id, first_name, last_name from faculty f inner join user u on f.user_id = u.id where f.id=" + resultSet.getInt("faculty_id"));
            resultSet1.next();
            courseOffers.add(
                    new CourseOffer(
                            resultSet.getInt("id"),
                            new Course(resultSet.getInt("cid"), resultSet.getString("code"), resultSet.getString("title"), null, resultSet.getString("credit")),
                            new Faculty(resultSet1.getInt("user_id"), resultSet1.getString("first_name"), resultSet1.getString("last_name"), null, null, null, resultSet1.getInt("id")),
                            null
                    )
            );
        }
        return courseOffers;
    }

    public Integer getAvgCreditForLast2Sems(Integer studentId) throws SQLException {
        ResultSet sessionResultSet = conn.createStatement().executeQuery("select id from session where status<>'ACTIVE' order by year desc, sem desc limit 2");
        List<Integer> credit = new ArrayList<>();
        while (sessionResultSet.next()) {
            ResultSet resultSet = conn.createStatement().executeQuery("select AVG(creditsreceived) as avgcredit from courseregister cr inner join courseoffer co on cr.offer_id = co.id where co.session_id=" + sessionResultSet.getInt("id") + " and student_id=" + studentId + " and cr.status='COMPLETED'");
            resultSet.next();
            credit.add(resultSet.getInt("avgcredit"));
        }
        int sum = 0, num=0;
        for(Integer c: credit) {
            if (c != 0) {
                sum += c;
                num++;
            }
        }
        if(num==0)
            return 16;
        return sum/num;
    }

    public void registerStudent(Integer offerId, Integer studentId) throws SQLException {
        conn.createStatement().executeUpdate("insert into courseregister (student_id, offer_id, status) values ("+studentId+", "+offerId+" ,'"+ CourseRegistrationStatus.ENROLLED +"')");
    }
}
