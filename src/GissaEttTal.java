import java.util.Random;

public class GissaEttTal {
    private final int MAX_SECRET_NUMBER = 100;
    private final int MIN_SECRET_NUMBER = 1;
    private final int MAX_LOW_SCORES = 5;
    private LowScore lowScore;
    private UserStats user;
    private ValidateUserInput userInput;
    private boolean runGame = true;
    private int secretNumber = 0;
    private int userGuess = 0;
    
    public GissaEttTal() {
        // Initialize new lowscores board instance and pass MAX_LOW_SCORES as argument.
        lowScore = new LowScore(MAX_LOW_SCORES); 

        while (runGame) {
            // Initialize new user/player instance.
            user = new UserStats(null, 0);
            
            // Run game
            generateSecretNumber();
            printGameDescription();
            askUserToGuess();
            checkUserGuess();
            checkLowScores();
            gameMenu();
        }
    }

    private void generateSecretNumber() {
        // Generate a random number between MIN_SECRET_NUMBER & MAX_SECRET_NUMBER (including min & max values).
        Random random = new Random();
        secretNumber = random.nextInt((MAX_SECRET_NUMBER - MIN_SECRET_NUMBER) + 1) + MIN_SECRET_NUMBER;
    }

    private void printGameDescription() {
        System.out.println("Gissa ett tal mellan " + MIN_SECRET_NUMBER + " och " + MAX_SECRET_NUMBER + ".");
    }

    private void askUserToGuess() {
        // Increment user score/guesses by 1.
        user.setScore(user.getScore() + 1);     
        // Prompt user to type a number.
        userInput = new ValidateUserInput(MIN_SECRET_NUMBER, MAX_SECRET_NUMBER);    

        userGuess = userInput.userInputAsInteger(
            // Set message that should be displayed to user in front of user input value. 
            "Gissning " + user.getScore() + ": ",
            // Set messeage that should be returned to user if input is not an integer.
            "Du ska gissa ett tal mellan " + MIN_SECRET_NUMBER + " & " + MAX_SECRET_NUMBER + ". Försök igen!"
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

    private void checkLowScores() {
        // Check if user score is lower than current lowscores and then ask if user want to save.
        if (lowScore.checkIfLowScore(user.getScore())) {
            System.out.println("Grattis! Du har satt ett nytt lowscore.");
            askToSave();
        } else {
            System.out.println("Du har tyvärr inte satt ett nytt lowscore.");
        }
    }

    private void askToSave() {
        // Set a specific keyword that user need to type in consol in order to save lowscore.
        String saveKeyWord = "JA";

        userInput = new ValidateUserInput(1, 3);

        // Check if user input is equal to saveKeyWord then ask for name.
        if (userInput.userInputAsString(
                "Skriv " + saveKeyWord + " för att spara: ", 
                "Skriv minst 1 tecken och max 3 tecken. Försök igen!"
                ).equals(saveKeyWord)) {
            
            userInput = new ValidateUserInput(3, 9);
            user.setName(userInput.userInputAsString(
                "Skriv ditt namn: ", 
                "Skriv minst 3 tecken och max 9 tecken. Försök igen!"
                ));
            
            // Save lowscore and confirm to user that lowscore is saved.
            lowScore.addLowScore(user);
            System.out.println(user.getName() + ", ditt lowscore är nu sparat!");
        } else {
            System.out.println("Du valde att inte spara ditt lowscore.");
        }
    }

    private void gameMenu() {
        System.out.println("1. Spela igen");
        System.out.println("2. Avsluta");
        System.out.println("3. Se lowscore");

        userInput = new ValidateUserInput(1,3);

        int menuSelection = userInput.userInputAsInteger(
            // Set message that should be displayed to user in front of user input value. 
            "Ditt val: ",
            // Set messeage that should be returned to user if input is not an integer.
            "Du kan bara skriva ett tal mellan 1 & 3. Försök igen!"
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
                lowScore.showLowScore("Namn", "Försök");
                gameMenu();
                break;
        }
    }
}
