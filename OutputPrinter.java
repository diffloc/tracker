package tracker;

import java.util.List;
import java.util.Set;

public class OutputPrinter {

    public static void printTitleMsg() {
        System.out.println("Learning Progress Tracker");
    }

    public static void printEnterCredentials() {
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

    public static void printAddPoints () {
        System.out.println("Enter an id and points or 'back' to return:");
    }

    public static void printFindStudent() {
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
}