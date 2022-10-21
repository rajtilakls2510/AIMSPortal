package frontend.acadoffice;

import frontend.BackScreen;
import frontend.Screen;

public class CourseCatalogScreen extends Screen {
    public CourseCatalogScreen() {
        title = "Course Catalogue";
        option = "Course catalog";
        backScreen = new BackScreen("", "Back");
    }

    @Override
    public void process() {

    }

    @Override
    public void preScreenProcess() {

    }
}
