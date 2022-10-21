package frontend.acadoffice;

import frontend.BackScreen;
import frontend.ProtectedScreen;

public class CourseEditScreen extends ProtectedScreen {
    public CourseEditScreen() {
        title = "Edit a Course";
        option = "Edit Course";
        backScreen = new BackScreen("", "Back");
    }
}
