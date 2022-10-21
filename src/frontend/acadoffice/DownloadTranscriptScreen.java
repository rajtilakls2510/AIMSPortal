package frontend.acadoffice;

import backend.AcadOfficeService;
import database.models.CourseRegister;
import frontend.BackScreen;
import frontend.ProtectedScreen;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class DownloadTranscriptScreen extends ProtectedScreen {

    public DownloadTranscriptScreen() {
        title = "Download Transcript";
        option = "Download Transcript";
        backScreen = new BackScreen("", "Back");
    }

    @Override
    public void process() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter Path to download file: ");
        String path = sc.nextLine().strip();
        String entry = (String) MessagePasser.getInstance().getMessages().get("Entry");
        AcadOfficeService service = AcadOfficeService.getInstance();
        try {
            List<CourseRegister> registeredCourses = service.getGrade(entry);
            // TODO: Store formatted Courses
            Integer cgpa = service.getCgpa(entry);
            // TODO: Store Cgpa
            // TODO: Store in file
            System.out.println("File Downloaded");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ignored) {
            }
        } catch (SQLException e) {
            System.out.println("Could not fetch Grades!");
        }
    }

    @Override
    public void show() throws RuntimeException {
        process();
    }
}
