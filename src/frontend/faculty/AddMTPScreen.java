package frontend.faculty;

import backend.FacultyService;
import database.models.MTPInfo;
import frontend.BackScreen;
import frontend.ProtectedScreen;

import java.sql.SQLException;

public class AddMTPScreen extends ProtectedScreen {
    public AddMTPScreen() {
        title = "Offer a MTP";
        option = "Offer New MTP";
        backScreen = new BackScreen("", "Back");
    }

    @Override
    public void process() {
        FacultyService service = FacultyService.getInstance();
        System.out.println("Add an MTP");
        // TODO: Take MTP info from user
        try {
            service.addMTP(new MTPInfo(null,null,null,null,null, null));
        } catch (SQLException e) {
            System.out.println("Sorry! Something went wrong");
        }
    }

    @Override
    public void show() throws RuntimeException {
        preScreenProcess();
        process();
        postScreenProcess();
    }
}
