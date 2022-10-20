package frontend;

public class StudentScreen extends Screen {

    public StudentScreen() {
        super();
        title = "Welcome Mr. ";
        option = "Student";
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
