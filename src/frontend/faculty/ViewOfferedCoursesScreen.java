package frontend.faculty;

import frontend.BackScreen;
import frontend.ProtectedScreen;

public class ViewOfferedCoursesScreen extends ProtectedScreen {
    public ViewOfferedCoursesScreen() {
        title = "Offered Courses";
        option = "View Offered courses and their details";
        backScreen = new BackScreen("", "Back");
    }

}
