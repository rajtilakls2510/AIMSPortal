package frontend.student;

import frontend.BackScreen;
import frontend.Screen;

public class CourseRegistrationScreen extends Screen {
    public CourseRegistrationScreen() {
        title="Course Registration";
        option="Register for a Course";
        backScreen=new BackScreen("", "Back");
    }

    @Override
    public void process() {

    }

    @Override
    public void preScreenProcess() {

    }
}
