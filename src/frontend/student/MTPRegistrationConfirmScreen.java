package frontend.student;

import backend.StudentService;
import frontend.ProtectedScreen;

import java.sql.SQLException;
import java.util.Scanner;

public class MTPRegistrationConfirmScreen extends ProtectedScreen {
    private Integer mtpId;
    public MTPRegistrationConfirmScreen(String title, String option, Integer mtpId) {
        this.title = title;
        this.option = option;
        this.mtpId = mtpId;
    }

    @Override
    public void process() {
        Scanner sc = new Scanner(System.in);
        StudentService service = StudentService.getInstance();
        System.out.println("Do you really want to register for this MTP? (1/0):");
        int confirm = sc.nextInt();
        if(confirm == 1)
        {
            try {
                service.registerForMtp(mtpId);
                System.out.println("Registered for MTP Successfully");
            } catch (SQLException e) {
                System.out.println("Something went wrong");
            }
        }
    }

    @Override
    public void show() throws RuntimeException {
        preScreenProcess();
        process();
        postScreenProcess();
    }
}
