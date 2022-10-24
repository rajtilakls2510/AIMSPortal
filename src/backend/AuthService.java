package backend;

import database.models.Auth;
import database.repositories.AuthRepo;
import frontend.LoggedInUser;
import frontend.UserType;

import java.sql.SQLException;
import java.util.Optional;

public class AuthService {

    private static AuthService instance;
    private AuthRepo authRepo;

    private AuthService() {
        authRepo = new AuthRepo();
    }

    public static AuthService getInstance() {
        if (instance == null)
            instance = new AuthService();
        return instance;
    }

    public boolean validateUser(String email, String password, UserType userType) throws SQLException {
        // Fetch User from database using email and match password
        // If match found, fill the user details in LoggedInUser singleton object and return true otherwise false
        Optional<Auth> optionalAuth = authRepo.getAuth(email);
        if (optionalAuth.isEmpty() || !optionalAuth.get().getPassword().equals(password))
            return false;
        Auth auth = optionalAuth.get();
        if(!checkValidUserType(auth.getUser().getId(), userType))
            return false;
        authRepo.addLoginSession(auth);
        LoggedInUser.getInstance().setId(auth.getUser().getId());
        LoggedInUser.getInstance().setEmail(auth.getUser().getEmail());
        LoggedInUser.getInstance().setName(auth.getUser().getFirstName() + " " + auth.getUser().getLastName());
        return true;
    }

    public boolean checkValidUserType(Integer userId, UserType userType) throws SQLException {
        switch (userType)
        {
            case STUDENT:
                if(authRepo.getStudent(userId).isPresent())
                    return true;
                break;
            case FACULTY:
                if(authRepo.getFaculty(userId).isPresent())
                    return true;
                break;
            case ACADOFFICE:
                if(authRepo.getAcademicOffice(userId).isPresent())
                    return true;
        }
        return false;
    }

    public void logoutUser() throws SQLException {
        // Remove the user from LoggedInUser and from login session
    }

    public boolean checkLoggedInUser(String email) throws SQLException {
        // Check User has an active login session or not
        return true;
    }
}
