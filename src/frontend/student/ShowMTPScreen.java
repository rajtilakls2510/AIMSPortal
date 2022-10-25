package frontend.student;

import backend.StudentService;
import database.models.MTPInfo;
import database.models.Student;
import frontend.BackScreen;
import frontend.ProtectedScreen;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ShowMTPScreen extends ProtectedScreen {
    public ShowMTPScreen() {
        title = "Show/Register for an MTP";
        option = "Show/Register for MTP";
        backScreen = new BackScreen();
    }

    @Override
    public void process() {
        StudentService service = StudentService.getInstance();
        try {
            MTPInfo registeredMTP = service.getRegisteredMTP();
            if(registeredMTP == null)
            {
                System.out.println("You are not registered to any MTP. Please register for one: ");
                List<MTPInfo> offeredMTPs = service.getOfferedMTPs();
                subScreens = new ArrayList<>();
                for(MTPInfo mtp: offeredMTPs)
                    subScreens.add(new MTPRegistrationConfirmScreen(mtp.getTitle(), mtp.getTitle(), mtp.getId())); // TODO: Format MTP Title
            }
            else{
                // TODO: Just show the MTP
            }
        } catch (SQLException e) {
            System.out.println("Something went wrong");
        }

    }
}
