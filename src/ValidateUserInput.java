import java.util.Scanner;

public class ValidateUserInput {
    private final Scanner scanner = new Scanner(System.in);
    private int minValue;
    private int maxValue;

    public ValidateUserInput(int minValue, int maxValue) {
        /*  Set min & max values for user input.
        For Integers it will set the range and for Strings it will set the length. */
        this.minValue = minValue;
        this.maxValue = maxValue;
    }

    public int userInputAsInteger(String inputMsg, String errorMsg) {
        while (true) {
            System.out.print(inputMsg);

            try {
                // If input is integer, loop will break and return input. 
                // int userInput = scanner.nextInt(); - WHICH OPTION IS BETTER?
                int userInput = Integer.parseInt(scanner.nextLine());
                
                if (userInput >= minValue && userInput <= maxValue) {
                    return userInput;
                } else {
                    System.out.println(errorMsg);
                }
                
            } catch (Exception e) {
                // If user input is not an integer, print an error message and prompt new input.
                System.out.println(errorMsg);
            }
        }
    }

    public String userInputAsString(String inputMsg, String errorMsg) {
        while (true) {
            System.out.print(inputMsg);
            String userInput = scanner.nextLine();
            
            if (userInput.length() >= minValue && userInput.length() <= maxValue) {
                return userInput;
            } else {
                System.out.println(errorMsg);
            }
        }
    }

}

