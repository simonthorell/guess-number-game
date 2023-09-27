import java.util.Scanner;

public class UserInput {
    Scanner scanner = new Scanner(System.in);

    public int userInputAsInteger(String inputMessage, int numberOfGuesses, String errorMessage) {
        String userInput;
        int userInputAsInteger = 0;
        boolean validInput = false;

        while (!validInput) {
            System.out.print(inputMessage + numberOfGuesses + ": ");
            userInput = scanner.nextLine();

            try {
                // Try to convert user String input to integer.
                userInputAsInteger = Integer.parseInt(userInput);
                validInput = true;
            } catch (Exception e) {
                // If user input is not an integer, print an error message.
                System.out.println(errorMessage);
            }
        }
        return userInputAsInteger;
    }    
}
