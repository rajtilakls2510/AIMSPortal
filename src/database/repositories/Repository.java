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
            conn = DriverManager.getConnection(DBCredentials.dbUrl, DBCredentials.username, DBCredentials.password);
        } catch (SQLException e) {
            throw new DBConnectionException("Could not connect to database!");
        }
    }

    public List<Course> getCourses() throws SQLException {
        List<Course> courses = new ArrayList<>();
        ResultSet result = conn.createStatement().executeQuery("Select * from course");
        while(result.next())
        {
            courses.add(
                    new Course(
                            result.getInt("id"),
                            result.getString("code"),
                            result.getString("title"),
                            result.getString("description"),
                            result.getString("credit")
                    )
            );
        }
        return courses;
    }
}
