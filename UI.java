package tracker;

public class UI {

    public static void run() {
        StudentManager studentManager = new StudentManager();
        OutputPrinter.printTitleMsg();
        while (true) {
            String userChoice = InputReader.readInput().strip();
            switch (userChoice) {
                case "add students" -> {
                    OutputPrinter.printEnterCredentials();
                    String inputStudent = InputReader.readInput().strip();
                    int studentCount = 0;
                    while (!inputStudent.equalsIgnoreCase("back")) {

                        // Split the inputStudent using the first and last occurrences of a space
                        int firstSpaceIndex = inputStudent.indexOf(' ');
                        int lastSpaceIndex = inputStudent.lastIndexOf(' ');

                        if (firstSpaceIndex != -1 && lastSpaceIndex != -1 && firstSpaceIndex != lastSpaceIndex) {
                            // Extract the first name, last name, and email using the substrings between the first and last spaces
                            String firstName = inputStudent.substring(0, firstSpaceIndex);
                            String lastName = inputStudent.substring(firstSpaceIndex + 1, lastSpaceIndex);
                            String email = inputStudent.substring(lastSpaceIndex + 1);

                            try {
                                Student student = new Student(firstName, lastName, email);
                                studentManager.addStudent(student);
                                OutputPrinter.printStudentAdded(student);
                                studentCount++;
                            } catch (IllegalArgumentException e) {
                                OutputPrinter.printInvalidCredentials();
                                System.out.println(e.getMessage());
                            }
                        } else {
                            OutputPrinter.printInvalidCredentials();
                        }
                        inputStudent = InputReader.readInput().strip();
                    }
                    System.out.printf("Total %d students have been added.\n", studentCount);
                }
                case "" -> OutputPrinter.printNoOutputMsg();
                case "exit" -> {
                    OutputPrinter.printByeMsg();
                    return;
                }
                default -> OutputPrinter.printErrorMsg();
            }
        }
    }
}