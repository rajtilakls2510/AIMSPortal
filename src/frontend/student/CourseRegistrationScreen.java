package frontend.student;

import frontend.BackScreen;
import frontend.ProtectedScreen;

public class CourseRegistrationScreen extends ProtectedScreen {
    public CourseRegistrationScreen() {
        title = "Course Registration";
        option = "Register for a Course";
        backScreen = new BackScreen("", "Back");
    }

}
