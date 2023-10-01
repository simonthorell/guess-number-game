import java.util.Random;

public class GissaEttTal {
    private LowScore lowScore;
    private UserStats user;
    private ValidateUserInput userInput;
    private final int MAX_LOW_SCORES = 5;
    private boolean runGame = true;
    private int secretNumber = 0;
    private int userGuess = 0;
    
    public GissaEttTal() {
        // Initialize new lowscores board instance. 
        lowScore = new LowScore(); 

        while (runGame) {
            // Initialize new user/player instance.
            user = new UserStats(null, 0);
            
            // Run game
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
        user.setScore(user.getScore() + 1);     // Increment user score/guesses by 1.
        userInput = new ValidateUserInput();    // Prompt user to type a number.

        userGuess = userInput.userInputAsInteger(
            // Set message that should be displayed to user in front of user input value. 
            "Gissning " + user.getScore() + ": ",
            // Set messeage that should be returned to user if input is not an integer.
            "Du kan bara skriva ett tal med siffror. Försök igen!"
        );
    }

    private void checkUserGuess() {
        // Give instruction to user if guess is secretNumber or lower/higher.
        while (userGuess != secretNumber) {
            if (userGuess < secretNumber) {
                System.out.println("Din gissning är för låg!");
            } else {
                System.out.println("Din gissning är för hög!");
            }
            askUserToGuess();
        }
        System.out.println("Rätt! Du gissade rätt på " + user.getScore() + " försök.");
    }

    private void checkIfLowScore() {
        // Check if user score is lower than current lowscores and then ask if user want to save.
        if (lowScore.checkIfLowScore(user.getScore(), MAX_LOW_SCORES)) {
            System.out.println("Grattis! Du har satt ett nytt lowscore.");
            askToSave();
        } else {
            System.out.println("Du har tyvärr inte satt ett nytt lowscore.");
        }
    }

    private void askToSave() {
        // Set a specific keyword that user need to type in consol in order to save lowscore.
        String saveKeyWord = "JA";

        System.out.print("Skriv " + saveKeyWord + " för att spara: ");
        userInput = new ValidateUserInput();

        // Check if user input is equal to saveKeyWord then save lowscore.
        if (userInput.userInputAsString().equals(saveKeyWord)) {
            System.out.print("Skriv ditt namn: ");
            userInput = new ValidateUserInput();
            user.setName(userInput.userInputAsString());
            lowScore.addLowScore(user, MAX_LOW_SCORES);
        }
    }

    private void gameMenu() {
        System.out.println("1. Spela igen");
        System.out.println("2. Avsluta");
        System.out.println("3. Se lowscore");

        userInput = new ValidateUserInput();
        boolean validInput = false;

        while (!validInput) {
            int menuSelection = userInput.userInputAsInteger(
                // Set message that should be displayed to user in front of user input value. 
                "Ditt val: ",
                // Set messeage that should be returned to user if input is not an integer.
                "Du kan bara skriva ett tal med siffror. Försök igen!"
            );

            switch (menuSelection) {
                case 1:
                    // Restart game
                    validInput = true;
                    break;
                case 2:
                    // End Game
                    runGame = false;
                    System.out.println("Tack för att du spelade!");
                    validInput = true;
                    break;
                case 3:
                    // Print lowscore board
                    lowScore.showLowScore();
                    gameMenu();
                    validInput = true;
                    break;
                default:
                    // Error message shown if user input is not a valid option.
                    System.out.println("Du kan bara skriva 1, 2 eller 3. Försök igen!");
                    break;
            }
        }
    }
}
