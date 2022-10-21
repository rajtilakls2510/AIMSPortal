package frontend.acadoffice;

import frontend.BackScreen;
import frontend.ProtectedScreen;

public class CourseDeleteScreen extends ProtectedScreen {
    public CourseDeleteScreen() {
        title = "Delete a course";
        option = "Delete Course";
        backScreen = new BackScreen("", "Back");
    }
}
