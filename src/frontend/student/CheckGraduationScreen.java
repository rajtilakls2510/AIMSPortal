package frontend.student;

import frontend.BackScreen;
import frontend.ProtectedScreen;

public class CheckGraduationScreen extends ProtectedScreen {
    public CheckGraduationScreen() {
        title = "Let's see if you are graduated";
        option = "Check Graduation";
        backScreen = new BackScreen("", "Back");
    }

}
