import java.util.Random;

public class GissaEttTal {
    int secretNumber;
    int antalGissningar;
    int userGuess;
    
    public GissaEttTal() {
        generateSecretNumer();
        gameDescription();
        askUserToGuess();
        checkUserGuess();
    }   

    private void generateSecretNumer() {
        Random random = new Random();
        secretNumber = random.nextInt(100) + 1;
    }

    private void gameDescription() {
        System.out.println("Gissa ett tal mellan 1 och 100.");
    }

    private void askUserToGuess() {
        antalGissningar++;
        UserInput userInput = new UserInput();

        userGuess = userInput.userInputAsInteger(
            "Gissning ",
            antalGissningar,
            //Set input messeage that should be returned to user if input is not an integer.
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
        System.out.println("Rätt! Du gissade rätt på " + antalGissningar + " försök.");
    }
}
