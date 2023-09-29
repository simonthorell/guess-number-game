import java.util.PriorityQueue;

public class LowScore {
    private static PriorityQueue<UserStats> lowScores;    
    private static ValidateUserInput userInput;

    public LowScore() {
        // Initialize new instance of Lowscore List.
        lowScores = new PriorityQueue<>();
    }

    public static boolean checkIfLowScore(String newLowScoreMsg, int score, int MAX_LOW_SCORES) {
        // Check if score is lower than current lowscore.
        if (lowScores.size() < MAX_LOW_SCORES) {
            return true;
        } else if (lowScores.peek().getScore() > score) {
            return true;
        } else {
            return false;
        }
    }

    // MOVE THIS METHOD TO GissaEttTal.java
    public static String askToSave(String saveScoreMsg, String saveKeyWord, String saveNameMsg) {
        // Print save message and prompt user to save score.
        System.out.print(saveScoreMsg);
        userInput = new ValidateUserInput();

        // If user wants to save score, prompt user to enter name.
        String userName = null;
        if (userInput.userInputAsString().equals(saveKeyWord)) {
            System.out.print(saveNameMsg);
            userInput = new ValidateUserInput();
            userName = userInput.userInputAsString();
        }
        return userName;
    }
    
    public void addLowScore(UserStats user, int MAX_LOW_SCORES) {
        if (lowScores.size() < MAX_LOW_SCORES) {
            lowScores.offer(user);
        } else if (lowScores.peek().getScore() > user.getScore()) {
            lowScores.poll();
            lowScores.offer(user);
        }
    }

    public static void showLowScore() {
        System.out.println("------- LOWSCORES -------");
        for (UserStats user : lowScores) {
            System.out.println(user.toString());
        }
        System.out.println("-------------------------");
    }
}
