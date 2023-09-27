import java.util.Scanner;

public class UserInput {
    Scanner scanner = new Scanner(System.in);

    public int userInputAsInteger(String askForInputMessage, int antalGissningar, String errorMessage) {
        String userInput;
        int userInputAsInteger = 0;
        boolean validInput = false;

        while (!validInput) {
            System.out.print(askForInputMessage + antalGissningar + ": ");
            userInput = scanner.nextLine();

            try {
                userInputAsInteger = Integer.parseInt(userInput);
                validInput = true;
            } catch (Exception e) {
                System.out.println(errorMessage);
            }
        }
        return userInputAsInteger;
    }    
}
