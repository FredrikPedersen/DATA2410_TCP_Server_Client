package URLReading;

import java.util.Scanner;

public class UserInput {

    private static String input;
    private  static Scanner inputScanner = new Scanner(System.in);

    protected static String getUserInput() {
        System.out.println("Please tell us where you want to know what time it is");
        return input = inputScanner.nextLine();
    }
}
