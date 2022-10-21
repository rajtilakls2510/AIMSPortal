package frontend.acadoffice;

import frontend.BackScreen;
import frontend.Screen;

public class CourseDeleteScreen extends Screen {
    public CourseDeleteScreen() {
        title = "Delete a course";
        option = "Delete Course";
        backScreen = new BackScreen("", "Back");
    }
}
