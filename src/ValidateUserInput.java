import java.util.Scanner;

public class ValidateUserInput {
    private final Scanner scanner = new Scanner(System.in);

    public int userInputAsInteger(String inputMsg, String errorMsg) {
        String userInput;
        int userInputAsInteger = 0;
        boolean validInput = false;
        
        while (!validInput) {
            System.out.print(inputMsg);
            userInput = scanner.nextLine();
            
            try {
                // Try to convert user String input to integer.
                userInputAsInteger = Integer.parseInt(userInput);
                validInput = true;

            } catch (Exception e) {
                // If user input is not an integer, print an error message.
                System.out.println(errorMsg);
            }
        }
        return userInputAsInteger;
    }

    public String userInputAsString() {
        String userInputAsString = scanner.nextLine();
        return userInputAsString;
    }

}

