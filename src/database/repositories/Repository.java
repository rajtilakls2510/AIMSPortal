package database.repositories;

import database.DBCredentials;
import database.models.Course;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Repository {

    Connection conn;

    public Repository() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(DBCredentials.dbUrl, DBCredentials.username, DBCredentials.password);
        } catch (Exception e) {
            throw new DBConnectionException("Could not connect to database!");
        }
    }

    public List<Course> getCourses() throws SQLException {
        List<Course> courses = new ArrayList<>();
        ResultSet result = conn.createStatement().executeQuery("SELECT * FROM course");
        while(result.next())
        {
            List<Course> prereq = new ArrayList<>();
            ResultSet result2 = conn.createStatement().executeQuery("SELECT c2.id AS id, c2.code AS code, c2.title AS title, c2.description AS description, c2.credit AS credit FROM course c1 INNER JOIN prerequisite p ON c1.id = p.course_id INNER JOIN course c2 ON c2.id = p.prereq_course_id WHERE c1.id="+result.getInt("id"));
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
}
