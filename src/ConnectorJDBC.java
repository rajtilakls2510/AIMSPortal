import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectorJDBC {
    public static void main(String[] args) throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/aimsdb", "root", "rajtilak");
    }
}
