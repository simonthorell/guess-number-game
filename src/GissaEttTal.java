import java.util.Random;

public class GissaEttTal {
    static boolean runGame = true;
    static int secretNumber = 0;
    static int numberOfGuesses = 0;
    static int userGuess = 0;
    static String userName;
    
    public GissaEttTal() {
        while (runGame) {
            generateSecretNumber();
            printGameDescription();
            askUserToGuess();
            checkUserGuess();
            checkIfLowScore();
            gameMenu();
        }
    }   

    private void generateSecretNumber() {
        // Generate a random number between 1 and 100.
        Random random = new Random();
        secretNumber = random.nextInt(100) + 1;
    }

    private void printGameDescription() {
        System.out.println("Gissa ett tal mellan 1 och 100.");
    }

    private void askUserToGuess() {
        numberOfGuesses++;
        ValidateUserInput userInput = new ValidateUserInput();

        userGuess = userInput.userInputAsInteger(
            // Set message that should be displayed to user in front of user input value. 
            "Gissning " + numberOfGuesses + ": ",
            // Set messeage that should be returned to user if input is not an integer.
            "Du kan bara skriva ett tal med siffror. Försök igen!"
        );
    }

    private void checkUserGuess() {
        // Give instruction to user if guess is correct or too low/high.
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

    private void checkIfLowScore() {
        // Set a specific keyword that user need to type in consol in order to save lowscore.
        String saveKeyWord = "JA";

        // Check if number of guesses is lower than current lowscore.
        LowScore.lowScore(
            numberOfGuesses, 
            // Set message that should be shown to user if new lowscore achieved. 
            "Grattis! Du har ett nytt lowscore!",
            // Set message asking user if lowscore should be saved.
            "Skriv " + saveKeyWord + " för att spara till lowScore: ", 
            // Keyword that user need to type in order to save lowscore.
            saveKeyWord,
            // Set message asking user name that should be saved with lowscore.
            "Skriv ditt namn: "
        );
    }

    private void gameMenu() {
        ValidateUserInput userInput = new ValidateUserInput();

        System.out.println("1. Spela igen");
        System.out.println("2. Avsluta");
        System.out.println("3. Se lowscore");

        int menuSelection = userInput.userInputAsInteger(
            // Set message that should be displayed to user in front of user input value. 
            "Ditt val: ",
            // Set messeage that should be returned to user if input is not an integer.
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
                int lowScore = LowScore.loadLowScore();
                System.out.println("Lowscore: " + lowScore);
                gameMenu();
                break;
            default:
                System.out.println("Du kan bara skriva 1, 2 eller 3. Försök igen!");
                break;
        }
    }
}
