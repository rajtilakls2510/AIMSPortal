package frontend.acadoffice;

import frontend.AuthScreen;
import frontend.BackScreen;
import frontend.Screen;

public class AcadOfficeScreen extends Screen {
    public AcadOfficeScreen() {
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
