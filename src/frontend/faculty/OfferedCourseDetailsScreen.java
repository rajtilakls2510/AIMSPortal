package frontend.faculty;

import backend.FacultyService;
import database.models.Course;
import database.models.CourseRegister;
import database.models.CourseRegistrationStatus;
import frontend.BackScreen;
import frontend.ProtectedScreen;
import frontend.MessagePasser;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OfferedCourseDetailsScreen extends ProtectedScreen {
    Course course;
    public OfferedCourseDetailsScreen(String title, String option, Course course) {
        this.title = title;
        this.option = option;
        this.course = course;
        subScreens.add(new GradeEntryScreen());
        backScreen = new BackScreen();
    }

    @Override
    public void process() {
        FacultyService service = FacultyService.getInstance();
        try {
            List<CourseRegister> courseDetails = service.getCourseDetails(course.getId());
            System.out.println("L-T-P-S-C: "+course.getCredit());
            System.out.println("\nDescription:- ");
            System.out.println(course.getDescription());
            System.out.println("\nPrerequisites:- ");
            for (Course cp : course.getPrerequisites())
                System.out.println("* " + cp.getCode() + " - " + cp.getTitle());

            System.out.println("\nRegistered Students:- ");
            showStudents(courseDetails);

            MessagePasser.getInstance().getMessages().put("courseCode", course.getId());
        } catch (SQLException e) {
            System.out.println("Sorry! Some error occured");
        }
    }

    private void showStudents(List<CourseRegister> courseRegisters) {
        Map<String, Integer> columnLengths = new HashMap<>();
        columnLengths.put("Entry", 7);
        columnLengths.put("Name", 6);
        columnLengths.put("Grade", 7);
        columnLengths.put("Credit", 9);

        courseRegisters.forEach(cr -> {
            if(cr.getStatus() == CourseRegistrationStatus.ENROLLED && cr.getStudent().getEntryNo().length() > columnLengths.get("Entry"))
                columnLengths.put("Entry", cr.getStudent().getEntryNo().length());
        });
        courseRegisters.forEach(cr -> {
            if(cr.getStatus() == CourseRegistrationStatus.ENROLLED && cr.getStudent().getFirstName().length()+cr.getStudent().getLastName().length() > columnLengths.get("Name"))
                columnLengths.put("Name", cr.getStudent().getFirstName().length()+cr.getStudent().getLastName().length());
        });

        courseRegisters.forEach(cr -> {
            if(cr.getStatus() == CourseRegistrationStatus.ENROLLED && String.valueOf(cr.getGrade()).length() > columnLengths.get("Grade"))
                columnLengths.put("Grade", String.valueOf(cr.getGrade()).length());
        });

        courseRegisters.forEach(cr -> {
            if(cr.getStatus() == CourseRegistrationStatus.ENROLLED && String.valueOf(cr.getCreditsReceived()).length() > columnLengths.get("Credit"))
                columnLengths.put("Credit", String.valueOf(cr.getCreditsReceived()).length());
        });
        System.out.println();
        String formatString = " %-" + (columnLengths.get("Entry")+2) + "s %-"+(columnLengths.get("Name")+3)+"s %-"+(columnLengths.get("Grade")+2)+"s %-"+(columnLengths.get("Credit")+2)+"s\n";
        System.out.printf(formatString, "Entry", "Name", "Grade", "Credits");
        courseRegisters.forEach(cr -> {
            if(cr.getStatus() == CourseRegistrationStatus.ENROLLED)
                System.out.printf(formatString, cr.getStudent().getEntryNo(), cr.getStudent().getFirstName()+" "+cr.getStudent().getLastName(), String.valueOf(cr.getGrade()), String.valueOf(cr.getCreditsReceived()));
        });
        System.out.println();
    }
}
