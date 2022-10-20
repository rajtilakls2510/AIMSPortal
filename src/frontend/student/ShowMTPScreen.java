package frontend.student;

import frontend.BackScreen;
import frontend.Screen;

public class ShowMTPScreen extends Screen {
    public ShowMTPScreen() {
        title = "Show/Register for an MTP";
        option = "Show/Register for MTP";
        backScreen = new BackScreen("", "Back");
    }

    @Override
    public void process() {

    }

    @Override
    public void preScreenProcess() {

    }
}
