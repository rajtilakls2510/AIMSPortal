package frontend.acadoffice;

import frontend.BackScreen;
import frontend.Screen;

public class CourseEditScreen extends Screen {
    public CourseEditScreen() {
        title = "Edit a Course";
        option = "Edit Course";
        backScreen = new BackScreen("", "Back");
    }
}
