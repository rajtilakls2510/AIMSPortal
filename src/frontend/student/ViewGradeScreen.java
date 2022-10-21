package frontend.student;

import frontend.BackScreen;
import frontend.Screen;

public class ViewGradeScreen extends Screen {
    public ViewGradeScreen() {
        title = "Your grades uptill date!";
        option = "View Grades and CGPA";
        backScreen = new BackScreen("","Back");
    }

}
