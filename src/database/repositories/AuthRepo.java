package database.repositories;

import database.models.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Optional;

public class AuthRepo extends Repository {

    public Optional<Auth> getAuth(String email) throws SQLException {
        ResultSet resultSet = conn.createStatement().executeQuery("select * from " +
                "auth inner join user on auth.user_id = user.id " +
                "where email = '" +
                email +
                "'");
        if (!resultSet.next())
            return Optional.empty();
        User user = new User(
                resultSet.getInt("id"),
                resultSet.getString("first_name"),
                resultSet.getString("last_name"),
                resultSet.getString("email"),
                resultSet.getString("phone"),
                resultSet.getDate("join_date").toLocalDate()
        );
        Auth auth = new Auth(user, resultSet.getString("password"));
        return Optional.of(auth);
    }

    public Optional<Object> getStudent(Integer userId) throws SQLException {
        ResultSet resultSet = conn.createStatement().executeQuery("select * from user u inner join student s on u.id = s.user_id where u.id = " + userId);
        if(!resultSet.next())
            return Optional.empty();
        return Optional.of(new Object());
    }

    public Optional<Object> getFaculty(Integer userId) throws SQLException {
        ResultSet resultSet = conn.createStatement().executeQuery("select * from user u inner join faculty f on u.id = f.user_id where u.id = " + userId);
        if(!resultSet.next())
            return Optional.empty();
        return Optional.of(new Object());
    }

    public Optional<Object> getAcademicOffice(Integer userId) throws SQLException {
        ResultSet resultSet = conn.createStatement().executeQuery("select * from user u inner join academicoffice a on u.id = a.user_id where u.id = " + userId);
        if(!resultSet.next())
            return Optional.empty();
        return Optional.of(new Object());
    }


    public void addLoginSession(Auth auth) throws SQLException {
        conn.createStatement().executeUpdate("insert into loginsession(user_id, timestamp) values("+auth.getUser().getId()+", '"+ LocalDateTime.now() +"')");
    }
}
