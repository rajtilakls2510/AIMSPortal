package frontend.acadoffice;

import backend.AcadOfficeService;
import database.models.CourseRegister;
import database.models.CourseRegistrationStatus;
import database.models.Session;
import database.models.Student;
import frontend.BackScreen;
import frontend.LoggedInUser;
import frontend.MessagePasser;
import frontend.ProtectedScreen;

import java.sql.SQLException;
import java.util.*;

public class StudentGradeScreen extends ProtectedScreen {
    public StudentGradeScreen() {
        title = "View Student Grade";
        option = "Grade of a student";
        subScreens.add(new DownloadTranscriptScreen());
        backScreen = new BackScreen();
    }

    @Override
    public void process() {
        AcadOfficeService service = AcadOfficeService.getInstance();

        Scanner sc = new Scanner(System.in);
        System.out.print("Enter Student's Entry No (Press ENTER to ignore): ");
        String entry = sc.nextLine().strip();
        if(entry.equals(""))
            return;
        try {
            Optional<Student> studentDetails = service.getStudentDetails(entry);
            Student student = studentDetails.orElseThrow(SQLException::new);
            List<CourseRegister> grade = service.getGrade(student.getStudentId());
            List<Session> sessions = service.getSessions(student.getStudentId());
            String transcript = formatGrades(student, grade, sessions);
            System.out.println(transcript);
            MessagePasser.getInstance().getMessages().put("Entry", entry);
            MessagePasser.getInstance().getMessages().put("Transcript", transcript);
        } catch (SQLException e) {
            System.out.println("Could not fetch Grades!");
        }

    }

    public String formatGrades(Student student, List<CourseRegister> grade, List<Session> sessions) {
        String form = "\t\t\t\t\t Indian Institute of Technology, Ropar\n";
        form += "\t\t\t\t\t\t\t\t Grade Sheet \n\n";

        form += "Name: " + student.getFirstName() + " " + student.getLastName() + "\n";
        form += "Entry No: " + student.getEntryNo() + "\n\n";

        Map<String, Integer> columnLengths = new HashMap<>();
        List<Double> gpas = new ArrayList<>();
        for (Session s : sessions) {
            form += "Session: Sem " + s.getSem() + ", Year " + s.getYear() + "\n";
            form += "--------------------------------------------------------------------------------------------\n";

            columnLengths.put("Course Code", 13);
            columnLengths.put("Course Title", 14);
            columnLengths.put("Grade", 7);
            columnLengths.put("Credits Received", 18);
            columnLengths.put("Credits", 8);
            columnLengths.put("Status", 8);

            List<CourseRegister> filteredGrade = new ArrayList<>();
            for (CourseRegister cr : grade) {
                if (cr.getOffer().getSession().equals(s)) {
                    filteredGrade.add(cr);
                    if (cr.getOffer().getCourse().getCode().length() > columnLengths.get("Course Code")) {
                        columnLengths.put("Course Code", cr.getOffer().getCourse().getCode().length());
                    }
                    if (cr.getOffer().getCourse().getTitle().length() > columnLengths.get("Course Title")) {
                        columnLengths.put("Course Title", cr.getOffer().getCourse().getTitle().length());
                    }
                    if (String.valueOf(cr.getGrade()).length() > columnLengths.get("Grade")) {
                        columnLengths.put("Grade", String.valueOf(cr.getGrade()).length());
                    }
                    if (String.valueOf(cr.getOffer().getCourse().getCredit().charAt(cr.getOffer().getCourse().getCredit().length() - 1)).length() > columnLengths.get("Credits")) {
                        columnLengths.put("Credits", String.valueOf(cr.getOffer().getCourse().getCredit().charAt(cr.getOffer().getCourse().getCredit().length() - 1)).length());
                    }
                    if (cr.getStatus().toString().length() > columnLengths.get("Status")) {
                        columnLengths.put("Status", cr.getStatus().toString().length());
                    }
                }
            }

            String formatString = " %-" + (columnLengths.get("Course Code") + 2) + "s %-" + (columnLengths.get("Course Title") + 2) + "s %-" + (columnLengths.get("Grade") + 2) + "s %-" + (columnLengths.get("Credits Received") + 2) + "s %-" + (columnLengths.get("Credits") + 2) + "s %-" + (columnLengths.get("Status") + 2) + "s\n";
            form += String.format(formatString, "Course Code", "Course Title", "Grade", "Credits Received", "Credits", "Status");
            form += "--------------------------------------------------------------------------------------------\n";
            for (CourseRegister cr : filteredGrade) {
                form += String.format(formatString, cr.getOffer().getCourse().getCode(), cr.getOffer().getCourse().getTitle(), cr.getGrade(), cr.getCreditsReceived(), cr.getOffer().getCourse().getCredit().charAt(cr.getOffer().getCourse().getCredit().length() - 1), cr.getStatus());
            }

            double gpa = 0.0;
            int totalCredits = 0;
            for (CourseRegister cr : filteredGrade) {
                if (cr.getStatus() != CourseRegistrationStatus.DROPPED) {
                    gpa += (cr.getGrade() * cr.getCreditsReceived());
                    totalCredits += Integer.parseInt(String.valueOf(cr.getOffer().getCourse().getCredit().charAt(cr.getOffer().getCourse().getCredit().length() - 1)));
                }

            }

            if (totalCredits > 0)
                gpa = gpa / totalCredits;

            form += "\n SGPA: " + gpa + "\n\n";
            gpas.add(gpa);
        }
        double gpa = 0.0;
        for (Double g : gpas)
            gpa += g;
        if (!gpas.isEmpty())
            form += " Cumulative GPA: " + gpa / gpas.size() + "\n";
        return form;
    }
}
