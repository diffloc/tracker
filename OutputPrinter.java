package tracker;

public class OutputPrinter {

    public static void printTitleMsg() {
        System.out.println("Learning Progress Tracker");
    }

    public static void printEnterCredentials() {
        System.out.println("Enter student credentials or 'back' to return");
    }

    public static void printStudentAdded(Student student) {
        System.out.printf("The student [%s] has been added.\n", student);
    }

    public static void printInvalidCredentials() {
        System.out.println("Incorrect credentials.");
    }

    public static void printErrorMsg() {
        System.out.println("Unknown command!");
    }

    public static void printNoOutputMsg() {
        System.out.println("No input.");
    }

    public static void printByeMsg() {
        System.out.println("Bye!");
    }
}