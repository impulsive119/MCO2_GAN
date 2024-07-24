import java.util.Scanner;


/**
 * The InputHelper class is a utility class containing a scanner that lets the system handle user inputs.
 */


public class InputHelper {


    private static final Scanner scanner =  new Scanner(System.in);


    /**
     * Reads the next integer input from the user.
     *
     * @return The integer provided by the user, or -1 if the input is not a valid integer.
     */


    public static int nextInt() {
        String input = scanner.nextLine();
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            return -1;
        }
    }


    /**
     * Reads the next string input from the user.
     *
     * @return The string provided by the user.
     */


    public static String nextStr() {
        return scanner.nextLine();
    }


    /**
     * Reads the next double input from the user.
     *
     * @return The double provided by the user.
     */


    public static double nextDouble(){
        String input = scanner.nextLine();
        try {
            return Double.parseDouble(input);
        } catch (NumberFormatException e) {
            return -1;
        }
    }


    /**
     * Closes the Scanner object to release resources.
     */


    public static void close() {
        scanner.close();
    }
}
