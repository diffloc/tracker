package tracker;

import java.util.Set;

public class OutputPrinter {

    public static void printTitleMsg() {
        System.out.println("Learning Progress Tracker");
    }

    public static void promptEnterCredentials() {
        System.out.println("Enter student credentials or 'back' to return");
    }

    public static void printStudentAdded(Student student) {
        System.out.println("The student has been added.");
    }

    public static void printStudentList(Set<Student> students) {
        for (Student student: students) {
            System.out.println(student);
        }
    }

    public static void printStudentIDs(Set<Student> students) {
        System.out.println("Students:");
        for (Student student : students) {
            System.out.println(student.getStudentID());
        }
    }

    public static void promptAddPoints () {
        System.out.println("Enter an id and points or 'back' to return:");
    }

    public static void promptFindStudent() {
        System.out.println("Enter an id or 'back' to return:");
    }

    public static void printInvalidCredentials() {
        System.out.println("Incorrect credentials.");
    }

    public static void printEmailTaken() {
        System.out.println("This email is already taken.");
    }

    public static void printUnknownCommand() {
        System.out.println("Unknown command!");
    }

    public static void printNoInputMsg() {
        System.out.println("No input.");
    }

    public static void printByeMsg() {
        System.out.println("Bye!");
    }

    public static void printGeneralStatistics(String mostPopular, String leastPopular, String highActivity, String lowActivity, String easiestCourse, String hardestCourse) {
        System.out.println("Type the name of a course to see details or 'back' to quit");
        System.out.println("Most popular: " + mostPopular);
        System.out.println("Least popular: " + leastPopular);
        System.out.println("Highest activity: " + highActivity);
        System.out.println("Lowest activity: " + lowActivity);
        System.out.println("Easiest course: " + easiestCourse);
        System.out.println("Hardest course: " + hardestCourse);
    }
}