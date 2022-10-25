package frontend.acadoffice;

import backend.AcadOfficeService;
import database.models.Course;
import frontend.BackScreen;
import frontend.ProtectedScreen;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CourseCatalogScreen extends ProtectedScreen {
    public CourseCatalogScreen() {
        title = "Course Catalogue";
        option = "Course catalog";
        subScreens.add(new CourseAddScreen());
        subScreens.add(new CourseEditScreen());
        subScreens.add(new CourseDeleteScreen());
        backScreen = new BackScreen();
    }

    @Override
    public void process() {
        AcadOfficeService service = AcadOfficeService.getInstance();

        List<Course> courses;
        try {
            courses = service.getCourses();
            System.out.println("Courses Available:- ");
            showCourses(courses);
        } catch (SQLException e) {
            System.out.println("Could not fetch courses from database!");
        }

    }

    private void showCourses(List<Course> courses) {
        Map<String, Integer> columnLengths = new HashMap<>();
        columnLengths.put("Code", 0);
        columnLengths.put("Title", 0);
        columnLengths.put("Credit", 0);

        courses.forEach(course -> {
            if(course.getCode().length() > columnLengths.get("Code"))
                columnLengths.put("Code", course.getCode().length());
        });
        courses.forEach(course -> {
            if(course.getTitle().length() > columnLengths.get("Title"))
                columnLengths.put("Title", course.getTitle().length());
        });
        courses.forEach(course -> {
            if(course.getCredit().length() > columnLengths.get("Credit"))
                columnLengths.put("Credit", course.getCredit().length());
        });

        String formatString = " %-" + (columnLengths.get("Code")+2) + "s %-"+(columnLengths.get("Title")+2)+"s %-"+(columnLengths.get("Credit")+2)+"s\n";

        courses.forEach(course -> System.out.printf(formatString, course.getCode(), course.getTitle(), course.getCredit()));
        System.out.println("");
    }

}
