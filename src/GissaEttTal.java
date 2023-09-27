import java.util.Random;

public class GissaEttTal {
    boolean runGame = true;
    int secretNumber;
    int numberOfGuesses;
    int userGuess;
    
    public GissaEttTal() {
        while (runGame) {
            generateSecretNumer();
            printGameDescription();
            askUserToGuess();
            checkUserGuess();
            showGameMenu();
        }
    }   

    private void generateSecretNumer() {
        // Generate a random number between 1 and 100.
        Random random = new Random();
        secretNumber = random.nextInt(100) + 1;
    }

    private void printGameDescription() {
        System.out.println("Gissa ett tal mellan 1 och 100.");
    }

    private void askUserToGuess() {
        numberOfGuesses++;
        UserInput userInput = new UserInput();

        userGuess = userInput.userInputAsInteger(
            // Set message that should be displayed to user in front of user input value. 
            "Gissning " + numberOfGuesses + ": ",
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

    private void showGameMenu() {
        UserInput userInput = new UserInput();

        System.out.println("1. Spela igen");
        System.out.println("2. Avsluta");
        System.out.println("3. Se lowscore");

        int menuSelection = userInput.userInputAsInteger(
            // Set message that should be displayed to user in front of user input value. 
            "Ditt val: ",
            // Set input messeage that should be returned to user if input is not an integer.
            "Du kan bara skriva ett tal med siffror. Försök igen!"
        );

        switch (menuSelection) {
            case 1:
                // Reset and restart game
                numberOfGuesses = 0;
                break;
            case 2:
                // End Game
                runGame = false;
                System.out.println("Tack för att du spelade!");
                break;
            case 3:
                // Show lowscore board
                break;  
            default:
                System.out.println("Du kan bara skriva 1, 2 eller 3. Försök igen!");
        }
    }
}
