package frontend;

public class WelcomeScreen extends Screen {
    public WelcomeScreen() {
        super();
        title = "Welcome to AIMS Portal";
        subScreens.add(new StudentScreen());
        subScreens.add(new FacultyScreen());
        subScreens.add(new AcadOfficeScreen());
        backScreen = new BackScreen("", "Exit");
    }

    @Override
    public void preScreenProcess() {

    }

    @Override
    public void process() {

    }
}
