package frontend.acadoffice;

import backend.AuthService;
import frontend.*;

import java.sql.SQLException;

public class AcadOfficeScreen extends Screen {
    public AcadOfficeScreen() {
        title = "Welcome Mr. ";
        option = "Academic Officer";
        subScreens.add(new CourseCatalogScreen());
        subScreens.add(new StudentGradeScreen());
        backScreen = new BackScreen("", "Logout");
    }

    @Override
    public void preScreenProcess() {
        new AuthScreen(UserType.ACADOFFICE).show();
        title += LoggedInUser.getInstance().getName();
    }

    @Override
    public void postScreenProcess() {
        try {
            AuthService.getInstance().logoutUser();
        } catch (SQLException ignored) {
        }
    }
}
