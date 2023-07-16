package tracker;

import java.util.Scanner;

public class InputReader {
    private final static Scanner scanner = new Scanner(System.in);

    public static String readInput() {
        return scanner.nextLine();
    }
}