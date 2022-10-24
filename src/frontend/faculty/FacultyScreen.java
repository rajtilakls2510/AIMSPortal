package frontend.faculty;

import backend.AuthService;
import frontend.AuthScreen;
import frontend.BackScreen;
import frontend.Screen;

import java.sql.SQLException;

public class FacultyScreen extends Screen {
    public FacultyScreen() {
        title = "Welcome Dr. "; // TODO: Add name
        option = "Faculty";
        subScreens.add(new OfferCourseScreen());
        subScreens.add(new ViewOfferedCoursesScreen());
        subScreens.add(new MTPDetailsScreen());
        backScreen = new BackScreen("", "Logout");
    }

    @Override
    public void preScreenProcess() {
        new AuthScreen().show();
    }

    @Override
    public void postScreenProcess() {
        try {
            AuthService.getInstance().logoutUser();
        } catch (SQLException ignored) {
        }
    }
}
