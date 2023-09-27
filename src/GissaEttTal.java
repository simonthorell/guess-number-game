import java.util.Random;

public class GissaEttTal {
    int secretNumber;
    int numberOfGuesses;
    int userGuess;
    
    public GissaEttTal() {
        generateSecretNumer();
        gameDescription();
        askUserToGuess();
        checkUserGuess();
    }   

    private void generateSecretNumer() {
        // Generate a random number between 1 and 100.
        Random random = new Random();
        secretNumber = random.nextInt(100) + 1;
    }

    private void gameDescription() {
        System.out.println("Gissa ett tal mellan 1 och 100.");
    }

    private void askUserToGuess() {
        numberOfGuesses++;
        UserInput userInput = new UserInput();

        userGuess = userInput.userInputAsInteger(
            // Set message that should be displayed to user in front of user input value. 
            "Gissning ",
            numberOfGuesses,
            // Set input messeage that should be returned to user if input is not an integer.
            "Du kan bara skriva ett tal med siffror. Försök igen!"
        );
    }

    private void checkUserGuess() {
        while (userGuess != secretNumber) {
            if (userGuess < secretNumber) {
                System.out.println("Din gissning är för låg!");
            } else {
                System.out.println("Din gissning är för hög!");
            }
            askUserToGuess();
        }
        System.out.println("Rätt! Du gissade rätt på " + numberOfGuesses + " försök.");
    }
}
