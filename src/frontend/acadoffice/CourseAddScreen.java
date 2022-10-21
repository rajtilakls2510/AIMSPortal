package frontend.acadoffice;

import frontend.BackScreen;
import frontend.ProtectedScreen;

public class CourseAddScreen extends ProtectedScreen {
    public CourseAddScreen() {
        title = "Add a Course";
        option = "Add Course";
        backScreen = new BackScreen("", "Back");
    }
}
