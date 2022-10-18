package frontend;

public class WelcomeScreen extends Screen {
    public WelcomeScreen() {
        super();
        title = "Welcome to AIMS Portal";
        subScreens.add(new ExampleScreen1());
        subScreens.add(new ExampleScreen2());
        subScreens.add(new ExampleScreen3());
        backScreen = new BackScreen("", "Logout");
    }

    @Override
    public void process() {

    }
}
