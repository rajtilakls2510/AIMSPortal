package frontend.student;

import backend.StudentService;
import database.models.CourseOffer;
import frontend.BackScreen;
import frontend.ProtectedScreen;

import java.sql.SQLException;
import java.util.Scanner;

public class CourseRegisterConfirmScreen extends ProtectedScreen {
    private Integer offerId;
    public CourseRegisterConfirmScreen(String title, String option, Integer offerId) {
        this.title = title;
        this.option = option;
        this.offerId = offerId;
    }

    @Override
    public void process() {
        StudentService service = StudentService.getInstance();
        try {
            CourseOffer courseOfferDetails = service.getCourseOfferDetails(offerId);
            // TODO: Show the course offer details
            Scanner sc = new Scanner(System.in);
            System.out.print("Do you really want to register for this course? (1/0): ");
            int confirm = sc.nextInt();
            if(confirm == 1)
            {
                service.registerforOffer(offerId);
                System.out.println("Registered Successfully");
            }
        } catch (SQLException e) {
            System.out.println("Something went wrong");
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
