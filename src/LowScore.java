import java.io.*;

public class LowScore {
    private static final String LOW_SCORE_FILE = "lowscores.txt";
    static int score;
    static String name;

    public static void lowScore(int userScore, String lowScoreMsg, String saveScoreMsg, String saveKeyWord, String userNameMsg) {
        score = userScore;

        // Check and save if user has new lowscore.
        boolean isLowScore = checkIfLowScore(lowScoreMsg);
        if (isLowScore) {
            askToSave(saveScoreMsg, saveKeyWord, userNameMsg);
        }
    }

    private static boolean checkIfLowScore(String lowScoreMsg) {
        // Check if user score is lower than current lowscore.
        if (score < loadLowScore()) {
            System.out.println(lowScoreMsg);
            return true;
        } else {
            return false;
        }
    }

    private static void askToSave(String saveScoreMsg, String saveKeyWord, String userNameMsg) {
        System.out.print(saveScoreMsg);
        ValidateUserInput userInput = new ValidateUserInput();
        if (userInput.userInputAsString().equals("JA")) {  
            askForUserName(userNameMsg);
            saveLowScore();
            System.out.println("Ditt lowscore är nu sparat!");
        } else {
            System.out.println("Sparar inte...");
        }
        
    }
    
    private static void askForUserName(String userNameMsg) {
        // Ask user for name and save to class variable.
        System.out.print(userNameMsg);
        ValidateUserInput userInput = new ValidateUserInput();
        name = userInput.userInputAsString();
    }

    private static void saveLowScore() {
        // Save user name and score to file.
        try (FileWriter writer = new FileWriter(LOW_SCORE_FILE)) {
            writer.write(Integer.toString(score));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static int loadLowScore() {
        int lowScore = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(LOW_SCORE_FILE))) {
            String line = reader.readLine();
            if (line != null) {
                lowScore = Integer.parseInt(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lowScore;
    }
}
