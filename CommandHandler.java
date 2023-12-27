package tracker;

import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;

public class CommandHandler {
    private final StudentManager studentManager;
    private final Map<String, Runnable> commandMap;

    public CommandHandler(StudentManager studentManager) {
        this.studentManager = studentManager;
        this.commandMap = new HashMap<>();
        initializeCommands();
    }

    private void initializeCommands() {
        commandMap.put("add students", this::addStudents);
        commandMap.put("", OutputPrinter::printNoInputMsg);
        commandMap.put("list", studentManager::outputStudentIDs);
        commandMap.put("add points", this::addPoints);
        commandMap.put("find", this::findStudent);
        commandMap.put("statistics", this::handleStatistics);
        commandMap.put("notify", this::notifyStudents);
        commandMap.put("exit", UI::exitProgram);
    }

    public void executeCommand(String command) {
        Runnable action = commandMap.get(command);
        if (action != null) {
            action.run();
        } else {
            OutputPrinter.printUnknownCommand();
        }
    }

    private void handleStatistics() {
        Statistics statistics = new Statistics(studentManager);
        displayGeneralStatistics(statistics);

        while (true) {
            System.out.println("Enter a course name for details or 'back' to quit:");
            String courseName = InputReader.readInput().strip();
            if ("back".equalsIgnoreCase(courseName)) {
                break;
            }
            displayCourseDetails(courseName, statistics);
        }
    }


    private void displayGeneralStatistics(Statistics statistics) {
        String mostPopular = statistics.calculateMostPopularCourse();
        String leastPopular = statistics.calculateLeastPopularCourse();
        String highestActivity = statistics.calculateHighestActivityCourse();
        String lowestActivity = statistics.calculateLowestActivityCourse();
        String easiestCourse = statistics.calculateEasiestCourse();
        String hardestCourse = statistics.calculateHardestCourse();

        OutputPrinter.printGeneralStatistics(mostPopular, leastPopular, highestActivity, lowestActivity, easiestCourse, hardestCourse);
    }


    private void displayCourseDetails(String courseName, Statistics statistics) {
        try {
            Course course = Course.valueOf(courseName.toUpperCase());
            String courseDetails = statistics.getCourseDetails(course);
            System.out.println(courseDetails);
        } catch (IllegalArgumentException e) {
            System.out.println("Unknown course.");
        }
    }


    private void addStudents() {
        OutputPrinter.promptEnterCredentials();
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

    private void addPoints() {
        OutputPrinter.promptAddPoints();
        String inputStudentPoints = InputReader.readInput();

        outerLoop: while (!inputStudentPoints.equalsIgnoreCase("back")) {
            String[] parsedStudentPoints = inputStudentPoints.split("\\s+");

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
                            System.out.println("Incorrect points format.");
                            inputStudentPoints = InputReader.readInput();
                            continue outerLoop;
                        }
                    }
                    studentManager.updateStudentPoints(studentID, coursePoints);
                    System.out.println("Points updated.");
                }
            }
            inputStudentPoints = InputReader.readInput();
        }
    }

    private void findStudent() {
        OutputPrinter.promptFindStudent();
        String inputStudentID = InputReader.readInput();

        while (!inputStudentID.equalsIgnoreCase("back")) {
            int studentID;
            try {
                studentID = Integer.parseInt(inputStudentID);
            } catch (NumberFormatException e) {
                System.out.println("Invalid student ID. Please enter a valid integer.");
                inputStudentID = InputReader.readInput();
                continue;
            }

            Student student = studentManager.getStudentByID(studentID);
            if (student != null) {
                System.out.printf("%d points: %s\n", studentID, student.getCoursePointsString());
            } else {
                System.out.printf("No student is found for id=%d.\n", studentID);
            }
            inputStudentID = InputReader.readInput();
        }
    }

    private void notifyStudents() {
        NotificationService notificationService = new NotificationService(studentManager);
        notificationService.sendNotifications();
    }
}
