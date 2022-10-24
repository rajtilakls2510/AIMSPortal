package frontend.faculty;

import frontend.BackScreen;
import frontend.ProtectedScreen;
import frontend.MessagePasser;

import java.util.Scanner;

public class GradeEntryScreen extends ProtectedScreen {
    public GradeEntryScreen() {
        this.title = "Perform Grade Entry";
        option = "Grade Entry";
        backScreen = new BackScreen();
    }

    @Override
    public void process() {
        Scanner sc = new Scanner(System.in);

        String courseCode = (String) MessagePasser.getInstance().getMessages().get("courseCode");
        System.out.print("Enter path of the csv file: ");
        String path = sc.nextLine().strip();
        parseGradeCsv(path);
        System.out.println("Grade Entry Complete");
    }

    void parseGradeCsv(String path)
    {
        // TODO: Read each line of the csv and call gradeEntry of FacultyService
    }
}
