package frontend.faculty;

import frontend.AuthScreen;
import frontend.BackScreen;
import frontend.Screen;

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
    public void process() {

    }
}
