import java.util.Scanner;

public class ValidateUserInput {
    private final Scanner scanner = new Scanner(System.in);

    public int userInputAsInteger(String inputMsg, String errorMsg) {
        while (true) {
            System.out.print(inputMsg);

            try {
                // If input is integer, loop will break and return input. 
                return scanner.nextInt(); 
                
            } catch (Exception e) {
                // If user input is not an integer, print an error message and prompt new input.
                System.out.println(errorMsg);
                scanner.nextLine();
            }
        }
    }

    public String userInputAsString() {
        String userInputAsString = scanner.nextLine();
        return userInputAsString;
    }

}

