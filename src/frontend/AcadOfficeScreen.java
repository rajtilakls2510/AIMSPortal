package frontend;

public class AcadOfficeScreen extends Screen {
    public AcadOfficeScreen() {
        super();
        title = "Welcome Officer ";
        option = "Academic Officer";
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
