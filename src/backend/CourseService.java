package backend;

import database.models.Course;
import database.repositories.Repository;

import java.sql.SQLException;
import java.util.List;

public class CourseService {

    Repository repo;

    public CourseService(Repository repo) {
        this.repo = repo;
    }

    public List<Course> getCourses() {
        try {
            return repo.getCourses();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
