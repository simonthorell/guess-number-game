import java.util.Random;

public class GissaEttTal {
    private LowScore lowScore = new LowScore();
    private boolean runGame = true;
    private int secretNumber = 0;
    private int userGuess = 0;
    private int userScore = 0;
    private String userName;
    
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
        secretNumber = random.nextInt(1) + 1;
        //secretNumber = random.nextInt(100) + 1;
    }

    private void printGameDescription() {
        System.out.println("Gissa ett tal mellan 1 och 100.");
    }

    private void askUserToGuess() {
        userScore++;
        ValidateUserInput userInput = new ValidateUserInput();

        userGuess = userInput.userInputAsInteger(
            // Set message that should be displayed to user in front of user input value. 
            "Gissning " + userScore + ": ",
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
        System.out.println("Rätt! Du gissade rätt på " + userScore + " försök.");
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
        boolean newLowScore = LowScore.checkIfLowScore(newLowScoreMsg, userScore);
        if (newLowScore) {
            userName = LowScore.askToSave(saveScoreMsg, saveKeyWord, saveNameMsg);
            UserStats userStats = new UserStats(userName, userScore);
            lowScore.addLowScore(userStats);
            System.out.println(userName + ", ditt lowscore: " + userScore+ " är sparat!");
        }
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
                userScore = 0;
                break;
            case 2:
                // End Game
                runGame = false;
                System.out.println("Tack för att du spelade!");
                break;
            case 3:
                // Show lowscore board
                LowScore.showLowScore();
                gameMenu();
                break;
            default:
                System.out.println("Du kan bara skriva 1, 2 eller 3. Försök igen!");
                break;
        }
    }
}
