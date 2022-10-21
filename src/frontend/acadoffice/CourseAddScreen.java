package frontend.acadoffice;

import frontend.BackScreen;
import frontend.Screen;

public class CourseAddScreen extends Screen {
    public CourseAddScreen() {
        title = "Add a Course";
        option = "Add Course";
        backScreen = new BackScreen("", "Back");
    }
}
