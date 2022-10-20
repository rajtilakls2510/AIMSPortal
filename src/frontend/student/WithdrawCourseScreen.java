package frontend.student;

import frontend.BackScreen;
import frontend.Screen;

public class WithdrawCourseScreen extends Screen {
    public WithdrawCourseScreen() {
        title = "Withdraw from a Course";
        option = "Withdraw Course";
        backScreen = new BackScreen("","Back");
    }

    @Override
    public void process() {

    }

    @Override
    public void preScreenProcess() {

    }
}
