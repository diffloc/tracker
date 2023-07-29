package tracker;

import java.util.EnumMap;
import java.util.Map;

public class UI {

    public static void run() {
        StudentManager studentManager = new StudentManager();
        OutputPrinter.printTitleMsg();

        //Begin primary user interface, return and exit main method for command "exit"
        while (true) {
            String userChoice = InputReader.readInput().strip();
            switch (userChoice) {
                case "add students" -> {
                    OutputPrinter.printEnterCredentials();
                    String inputStudent = InputReader.readInput().strip(); // XXX: .toLowerCase();
                    int studentCount = 0;
                    while (!inputStudent.equalsIgnoreCase("back")) {

                        // Split the inputStudent:
                        // -- first space delimits substring for first name (single word)
                        // -- second space delimits substring for last name (one or more words)
                        // -- third space delimits substring for email address
                        int firstSpaceIndex = inputStudent.indexOf(' ');
                        int lastSpaceIndex = inputStudent.lastIndexOf(' ');

                        // Verify space exist between first name, last name, and email address
                        // If only single space exists then first name, last name, or email address
                        // has been omitted in which case printInvalidCredentials().
                        if (firstSpaceIndex != -1 && lastSpaceIndex != -1 && firstSpaceIndex != lastSpaceIndex) {

                            // Extract the first name, last name, and email using the substrings between the first and last spaces
                            String firstName = inputStudent.substring(0, firstSpaceIndex);
                            String lastName = inputStudent.substring(firstSpaceIndex + 1, lastSpaceIndex);
                            String email = inputStudent.substring(lastSpaceIndex + 1);


                            try {
                                Student student = new Student(firstName, lastName, email);
                                if (studentManager.addStudent(student)) {
                                    OutputPrinter.printStudentAdded(student);
                                    studentCount++;
                                }
                            } catch (IllegalArgumentException e) {

                                // Catches any IllegalArgumentException from Student constructor validation
                                OutputPrinter.printInvalidCredentials();
                                System.out.println(e.getMessage());
                            }
                        } else {
                            OutputPrinter.printInvalidCredentials();
                        }

                        // Input for subsequent students until "back" command entered
                        inputStudent = InputReader.readInput().strip();
                    }
                    System.out.printf("Total %d students have been added.\n", studentCount);
                }
                case "" -> OutputPrinter.printNoInputMsg();
                case "list" -> studentManager.outputStudentIDs();
                case "add points" -> {
                    OutputPrinter.printAddPoints();
                    String inputStudentPoints = InputReader.readInput();

                    outerLoop: while (!inputStudentPoints.equalsIgnoreCase("back")) {
                        String[] parsedStudentPoints = inputStudentPoints.split("\\s+");

                        // int studentID = (Integer.parseInt(parsedStudentPoints[0]));

                        int studentID;
                        try {
                            studentID = Integer.parseInt(parsedStudentPoints[0]);
                        } catch (NumberFormatException e) {
                            System.out.println("Invalid student ID. Please enter a valid integer.");
                            inputStudentPoints = InputReader.readInput();
                            continue;
                        }

                        if (studentManager.getStudents().stream().noneMatch(s -> s.getStudentID() == studentID)) {
                            System.out.printf("No student is found for id=%d.\n", studentID);
                        } else {
                            int numCourses = Course.values().length;
                            if (parsedStudentPoints.length - 1 != numCourses) {
                                // System.out.printf("Incorrect number of points. Please enter points for all %d courses.\n", numCourses);
                                System.out.println("Incorrect points format.");
                            } else {
                                // Map points to the courses in the enum
                                Map<Course, Integer> coursePoints = new EnumMap<>(Course.class);
                                for (int i = 1; i < parsedStudentPoints.length; i++) {
                                    try {
                                        int points = Integer.parseInt(parsedStudentPoints[i]);
                                        if (points < 0) {
                                            System.out.println("Incorrect points format.");
                                            inputStudentPoints = InputReader.readInput(); // Prompt for the next input
                                            continue outerLoop; // Use the label to continue the while loop
                                        }
                                        coursePoints.put(Course.values()[i - 1], points);
                                    } catch (NumberFormatException e) {
                                        // System.out.printf("Invalid points for %s. Please enter a valid integer.\n", Course.values()[i - 1]);
                                        System.out.println("Incorrect points format.");
                                        inputStudentPoints = InputReader.readInput();
                                        continue outerLoop;
                                    }
                                }

                                // Add or update points for the student
                                Student student = studentManager.getStudentByID(studentID);
                                student.addCoursePoints(coursePoints);
                                // System.out.println("Points updated for student with ID: " + studentID);
                                System.out.println("Points updated.");
                            }
                        }
                        inputStudentPoints = InputReader.readInput();
                    }
                }
                case "find" -> {
                    OutputPrinter.printFindStudent();
                    String inputStudentID = InputReader.readInput();

                    while (!inputStudentID.equalsIgnoreCase("back")) {
                        int studentID;
                        try {
                            studentID = Integer.parseInt(parsedStudentPoints[0]);
                        } catch (NumberFormatException e) {
                            System.out.println("Invalid student ID. Please enter a valid integer.");
                            inputStudentPoints = InputReader.readInput();
                            continue;
                        }

                    }
                    /**
                     * Enter an id or 'back' to return:
                     * > 10000
                     * 10000 points: Java=12; DSA=13; Databases=14; Spring=15
                     * > 10001
                     * No student is found for id=10001.
                     */
                }
                case "exit" -> {
                    OutputPrinter.printByeMsg();
                    System.out.println("""
                            ------------
                            Student List
                            ------------""");
                    studentManager.outputStudents();
                    return;
                }
                default -> OutputPrinter.printUnknownCommand();
            }
        }
    }
}