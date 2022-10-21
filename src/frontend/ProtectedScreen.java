package frontend;

import backend.AuthService;

import java.sql.SQLException;

public class ProtectedScreen extends Screen {
    @Override
    public void preScreenProcess() {
        try {
            AuthService.getInstance().checkLoggedInUser(LoggedInUser.getInstance().getEmail());
        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }
}
