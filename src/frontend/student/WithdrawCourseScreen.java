package frontend.student;

import frontend.BackScreen;
import frontend.ProtectedScreen;

public class WithdrawCourseScreen extends ProtectedScreen {
    public WithdrawCourseScreen() {
        title = "Withdraw from a Course";
        option = "Withdraw Course";
        backScreen = new BackScreen("", "Back");
    }

}
