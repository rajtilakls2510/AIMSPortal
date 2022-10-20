package frontend.student;

import frontend.AuthScreen;
import frontend.BackScreen;
import frontend.Screen;

public class StudentScreen extends Screen {

    public StudentScreen() {
        title = "Welcome Mr. ";
        option = "Student";
        subScreens.add(new ViewGradeScreen());
        subScreens.add(new CourseRegistrationScreen());
        subScreens.add(new WithdrawCourseScreen());
        subScreens.add(new CheckGraduationScreen());
        subScreens.add(new ShowMTPScreen());
        backScreen = new BackScreen("", "Logout");
    }

    @Override
    public void preScreenProcess() {
        new AuthScreen().show();
    }

    @Override
    public void process() {

    }
}
