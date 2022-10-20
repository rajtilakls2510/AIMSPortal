package frontend;

public class FacultyScreen extends Screen {
    public FacultyScreen() {
        super();
        title = "Welcome Dr. ";
        option = "Faculty";
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
