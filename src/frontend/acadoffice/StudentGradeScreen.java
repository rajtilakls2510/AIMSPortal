package frontend.acadoffice;

import frontend.BackScreen;
import frontend.Screen;

public class StudentGradeScreen extends Screen {
    public StudentGradeScreen() {
        title = "View Student Grade";
        option = "Grade of a student";
        backScreen = new BackScreen("", "Back");
    }

}
