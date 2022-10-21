package frontend.faculty;

import frontend.BackScreen;
import frontend.Screen;

public class ViewOfferedCoursesScreen extends Screen {
    public ViewOfferedCoursesScreen() {
        title = "Offered Courses";
        option = "View Offered courses and their details";
        backScreen = new BackScreen("", "Back");
    }

}
