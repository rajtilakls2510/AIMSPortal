package frontend.faculty;

import frontend.BackScreen;
import frontend.ProtectedScreen;

public class MTPDetailsScreen extends ProtectedScreen {
    public MTPDetailsScreen() {
        title = "Master Thesis Projects";
        option = "MTP Details";
        backScreen = new BackScreen("", "Back");
    }

}
