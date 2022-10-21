package frontend.faculty;

import frontend.BackScreen;
import frontend.Screen;

public class OfferCourseScreen extends Screen {
    public OfferCourseScreen() {
        title = "Offer a Course";
        option = "Offer Course";
        backScreen = new BackScreen("", "Back");
    }

    @Override
    public void process() {

    }

    @Override
    public void preScreenProcess() {

    }
}
