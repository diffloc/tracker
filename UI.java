package tracker;

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
                    String inputStudent = InputReader.readInput().strip().toLowerCase();
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
                                studentManager.addStudent(student);
                                OutputPrinter.printStudentAdded(student);
                                studentCount++;
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
                case "" -> OutputPrinter.printNoOutputMsg();
                // TODO: add "list"
                //TODO: add "add points"
                case "exit" -> {
                    OutputPrinter.printByeMsg();
                    // System.out.println("""
                    //         ------------
                    //         Student List
                    //         ------------""");
                    // studentManager.outputStudents();
                    return;
                }
                default -> OutputPrinter.printUnknownCommand();
            }
        }
    }
}