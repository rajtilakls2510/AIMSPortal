package frontend.faculty;

import backend.FacultyService;
import frontend.ProtectedScreen;

import java.sql.SQLException;
import java.util.Scanner;

public class MTPCreditEntryScreen extends ProtectedScreen {
    Integer mtpId;
    public MTPCreditEntryScreen(String title, String option, Integer mtpId) {
        this.title = title;
        this.option = option;
        this.mtpId = mtpId;
    }

    @Override
    public void process() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter credit: ");
        Integer credit = sc.nextInt();
        FacultyService service = FacultyService.getInstance();
        try {
            service.addMTPCredit(mtpId, credit);
        } catch (SQLException e) {
            System.out.println("Sorry! Something went wrong");
        }
    }

    @Override
    public void show() throws RuntimeException {
        preScreenProcess();
        clearConsole();
        System.out.println("\n" + title + "\n");
        process();
        postScreenProcess();
    }
}
