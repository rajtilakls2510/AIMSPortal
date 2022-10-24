package database.repositories;

import backend.AcadOfficeService;
import database.models.Course;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AcadOfficeRepo extends Repository{

    public List<Course> getCourses() throws SQLException {
        //Fetch all the courses and return them
        List <Course> courses = new ArrayList<>();
        ResultSet result = conn.createStatement().executeQuery("SELECT * FROM course");
        while(result.next()) {
            courses.add(
                    new Course(
                            result.getInt("id"),
                            result.getString("code"),
                            result.getString("title"),
                            result.getString("description"),
                            result.getString("credit"))
            );
        }
        return courses;
    }

    public Course getCourse(String code) throws SQLException {
        //fetch the course
        ResultSet result = conn.createStatement().executeQuery("SELECT id FROM course where code = " + code);
        return new Course(result.getInt("id"),
                          result.getString("code"),
                          result.getString("title"),
                          result.getString("description"),
                          result.getString("credit"));
    }

    public void addCourse(Course course) throws SQLException { //prerequisite course code needed
        //add a course
        conn.createStatement().executeQuery("insert into course(code, title, description, credit) values (" + course.getCode()+","+course.getTitle()+","+course.getDescription()+","+course.getCredit()+")");
        List<Course> prereq_course = course.getPrerequisites();
        //inserting the prerequisite courses id for given courses in prerequisite table
        if(!prereq_course.isEmpty())
            for(Course a : prereq_course)
                conn.createStatement().executeQuery("insert into prerequisite(course_id, prereq_course_id) values (" + course.getId() + "," + a.getId() + ")");
    }

    public void editCourse(Course course) throws SQLException {
        //edit a course
        conn.createStatement().executeQuery("update course code = "+course.getCode()+"title = "+course.getTitle()+"description = "+course.getDescription()+"credit = "+course.getCredit());
        List<Course> prereq_course = course.getPrerequisites();
        if(!prereq_course.isEmpty()){
            for(Course a : prereq_course)
                conn.createStatement().executeQuery("update prerequisite course_id = "+course.getId()+"prepreq_course_id"+a.getId()+"where course_id = "+course.getId());
        }
    }

    public void deleteCourse(String code) throws SQLException{
        //delete a course
        conn.createStatement().executeQuery("delete from course where code = "+code);
    }
}
