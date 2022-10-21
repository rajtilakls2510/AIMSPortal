package frontend.acadoffice;

import frontend.AuthScreen;
import frontend.BackScreen;
import frontend.Screen;

public class AcadOfficeScreen extends Screen {
    public AcadOfficeScreen() {
        title = "Welcome Officer "; // TODO: Add name
        option = "Academic Officer";
        subScreens.add(new CourseCatalogScreen());
        subScreens.add(new StudentGradeScreen());
        backScreen = new BackScreen("", "Logout");
    }

    @Override
    public void preScreenProcess() {
        new AuthScreen().show();
    }

    @Override
    public void postScreenProcess() {
        // TODO: Remove Login Session and AuthInfo
    }
}
