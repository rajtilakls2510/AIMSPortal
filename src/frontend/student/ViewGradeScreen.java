package frontend.student;

import frontend.BackScreen;
import frontend.ProtectedScreen;

public class ViewGradeScreen extends ProtectedScreen {
    public ViewGradeScreen() {
        title = "Your grades uptill date!";
        option = "View Grades and CGPA";
        backScreen = new BackScreen("", "Back");
    }

}
