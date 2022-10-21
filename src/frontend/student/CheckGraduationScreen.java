package frontend.student;

import frontend.BackScreen;
import frontend.Screen;

public class CheckGraduationScreen extends Screen {
    public CheckGraduationScreen() {
        title = "Let's see if you are graduated";
        option = "Check Graduation";
        backScreen = new BackScreen("", "Back");
    }

}
