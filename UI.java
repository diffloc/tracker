package tracker;

public class UI {

    private static boolean running = true;

    public static void run() {
        StudentManager studentManager = new StudentManager();
        CommandHandler commandHandler = new CommandHandler(studentManager);
        OutputPrinter.printTitleMsg();

        //Begin primary user interface, return and exit main method for command "exit"

        while (running) {
            String userChoice = InputReader.readInput().strip();
            commandHandler.executeCommand(userChoice);
        }
    }

    static void exitProgram() {
        OutputPrinter.printByeMsg();
        running = false;
    }
}