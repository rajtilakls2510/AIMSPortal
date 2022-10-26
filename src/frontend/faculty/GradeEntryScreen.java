package frontend.faculty;

import database.models.Course;
import database.models.CourseOffer;
import frontend.BackScreen;
import frontend.ProtectedScreen;
import frontend.MessagePasser;

import java.sql.SQLException;
import java.util.Scanner;

public class GradeEntryScreen extends ProtectedScreen {
    private CourseOffer courseOffer;
    public GradeEntryScreen(CourseOffer courseOffer) {
        this.title = "Perform Grade Entry";
        option = "Grade Entry";
        this.courseOffer = courseOffer;
        backScreen = new BackScreen();
    }

    @Override
    public void process() {
        Scanner sc = new Scanner(System.in);
        try {
            System.out.print("Enter path of the csv file: ");
            String path = sc.nextLine().strip();
            parseGradeCsv(path);
            System.out.println("Grade Entry Complete");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
            }
        }
        catch (SQLException e)
        {
            System.out.println("Some error occured!");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {

            }
        }
    }

    void parseGradeCsv(String path) throws SQLException
    {
        // TODO: Read each line of the csv and call gradeEntry of FacultyService
    }
}
