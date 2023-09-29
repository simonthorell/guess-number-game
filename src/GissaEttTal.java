import java.util.Random;

public class GissaEttTal {
    private LowScore lowScore;
    private UserStats user;
    private ValidateUserInput userInput;
    private boolean runGame = true;
    private int secretNumber = 0;
    private int userGuess = 0;
    
    public GissaEttTal() {
        lowScore = new LowScore(); // Initialize new instance of lowscores.

        while (runGame) {
            user = new UserStats(null, 0);  // Create new user/player

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
        secretNumber = random.nextInt(1) + 1;
        //secretNumber = random.nextInt(100) + 1;
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
        // Set a specific keyword that user need to type in consol in order to save lowscore.
        String saveKeyWord = "JA";
        String newLowScoreMsg = "Grattis! Du har satt ett nytt lowscore.";
        // Set message that should be shown to user if new lowscore achieved and ask to save
        String saveScoreMsg = "Skriv " + saveKeyWord + " för att spara: ";
        // Set message asking user for name that should be saved with lowscore.
        String saveNameMsg = "Skriv ditt namn: ";

        // Check if user score is lower than current lowscores and then ask user to save.
        if (LowScore.checkIfLowScore(newLowScoreMsg, user.getScore())) {
            user.setName(LowScore.askToSave(saveScoreMsg, saveKeyWord, saveNameMsg));
            lowScore.addLowScore(user);
            System.out.println(user.getName() + ", ditt lowscore: " + user.getScore() + " är sparat!");
        }
    }

    private void gameMenu() {
        userInput = new ValidateUserInput();

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
                // Restart game
                break;
            case 2:
                // End Game
                runGame = false;
                System.out.println("Tack för att du spelade!");
                break;
            case 3:
                // Print lowscore board
                LowScore.showLowScore();
                gameMenu();
                break;
            default:
                // Error message shown if user input is not a valid option.
                System.out.println("Du kan bara skriva 1, 2 eller 3. Försök igen!");
                break;
        }
    }
}
