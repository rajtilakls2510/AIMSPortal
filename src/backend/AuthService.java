package backend;

import java.sql.SQLException;

public class AuthService {

    private static AuthService instance;

    private AuthService()
    {

    }
    public static AuthService getInstance()
    {
        if(instance == null)
            instance = new AuthService();
        return instance;
    }

    public boolean validateUser(String email, String password) throws SQLException {
        // Fetch User from database using email and match password
        // If match found, fill the user details in LoggedInUser singleton object and return true otherwise false
        return true;
    }

    public void logoutUser() throws SQLException {
        // Remove the user from LoggedInUser and from login session
    }
}
