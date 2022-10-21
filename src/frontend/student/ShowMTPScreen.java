package frontend.student;

import frontend.BackScreen;
import frontend.ProtectedScreen;

public class ShowMTPScreen extends ProtectedScreen {
    public ShowMTPScreen() {
        title = "Show/Register for an MTP";
        option = "Show/Register for MTP";
        backScreen = new BackScreen("", "Back");
    }

}
