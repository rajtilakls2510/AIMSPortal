package frontend.faculty;

import frontend.BackScreen;
import frontend.ProtectedScreen;

public class OfferCourseScreen extends ProtectedScreen {
    public OfferCourseScreen() {
        title = "Offer a Course";
        option = "Offer Course";
        backScreen = new BackScreen("", "Back");
    }

}
