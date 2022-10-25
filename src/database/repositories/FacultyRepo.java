package database.repositories;

import database.models.Course;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FacultyRepo extends Repository{
    public List<Course> getCourses() throws SQLException {
        List<Course> courses = new ArrayList<>();
        ResultSet result = conn.createStatement().executeQuery("SELECT * FROM course");
        while(result.next())
        {
            List<Course> prereq = getPrerequisites(result.getInt("id"));
            courses.add(
                    new Course(
                            result.getInt("id"),
                            result.getString("code"),
                            result.getString("title"),
                            result.getString("description"),
                            result.getString("credit"),
                            prereq
                    )
            );
        }
        return courses;
    }
    public Course getCourseDetails(Integer courseId) throws SQLException
    {
        ResultSet resultSet = conn.createStatement().executeQuery("select * from course where id=" + courseId);
        resultSet.next();
        List<Course> prereq = getPrerequisites(resultSet.getInt("id"));
        return new Course(
                resultSet.getInt("id"),
                resultSet.getString("code"),
                resultSet.getString("title"),
                resultSet.getString("description"),
                resultSet.getString("credit"),
                prereq
            );
    }

    public List<Course> getPrerequisites(Integer courseId) throws SQLException
    {
        List<Course> prereq = new ArrayList<>();
        ResultSet result2 = conn.createStatement().executeQuery("SELECT c2.id AS id, c2.code AS code, c2.title AS title, c2.description AS description, c2.credit AS credit FROM course c1 INNER JOIN prerequisite p ON c1.id = p.course_id INNER JOIN course c2 ON c2.id = p.prereq_course_id WHERE c1.id="+courseId);
        while(result2.next())
        {
            prereq.add(
                    new Course(
                            result2.getInt("id"),
                            result2.getString("code"),
                            result2.getString("title"),
                            result2.getString("description"),
                            result2.getString("credit")
                    )
            );
        }
        return prereq;
    }

    public List<Course> getAllUnofferedCourses() throws SQLException {
        List<Course> courses = new ArrayList<>();
        ResultSet resultSet = conn.createStatement().executeQuery("select * from course where id not in (select DISTINCT(c.id) from (courseoffer co inner join session s on co.session_id = s.id) right join course c on co.course_id = c.id where s.status='ACTIVE')");
        while(resultSet.next())
        {
            courses.add(
                    new Course(
                            resultSet.getInt("id"),
                            resultSet.getString("code"),
                            resultSet.getString("title"),
                            resultSet.getString("description"),
                            resultSet.getString("credit")
                    )
            );
        }
        return courses;
    }

    public void offerCourse(Course course, Integer facultyId) throws SQLException {
        ResultSet resultSet = conn.createStatement().executeQuery("select id from session where status = 'ACTIVE'");
        resultSet.next();
        int sessionId = resultSet.getInt("id");
        conn.createStatement().executeUpdate("insert into courseoffer (course_id, faculty_id, session_id) values ("+course.getId()+", "+facultyId+", "+sessionId+")");
    }

    public List<Course> getAllOfferedCourses(Integer facultyId) throws SQLException {
        List<Course> courses = new ArrayList<>();
        ResultSet resultSet = conn.createStatement().executeQuery("select c.id as id, c.code as code, c.title as title, c.description as description, c.credit as credit from (courseoffer co inner join session s on co.session_id = s.id) inner join course c on co.course_id = c.id where s.status = 'ACTIVE' and co.faculty_id = "+facultyId);
        while(resultSet.next())
        {
            courses.add(
                    new Course(
                            resultSet.getInt("id"),
                            resultSet.getString("code"),
                            resultSet.getString("title"),
                            resultSet.getString("description"),
                            resultSet.getString("credit")
                    )
            );
        }
        return courses;
    }
}
