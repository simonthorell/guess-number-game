import java.util.PriorityQueue;

public class LowScore {
    private static final int MAX_LOW_SCORES = 5;
    private static PriorityQueue<UserStats> lowScores;
    private static String userName;

    public LowScore() {
        lowScores = new PriorityQueue<>();
    }   

    public static boolean checkIfLowScore(String newLowScoreMsg, int userScore) {
        // Check if user score is lower than current lowscore.
        if (userScore < 99) { // add for loop here
            System.out.println(newLowScoreMsg);
            return true;
        } else {
            return false;
        }
    }

    public static String askToSave(String saveScoreMsg, String saveKeyWord, String saveNameMsg) {
        System.out.print(saveScoreMsg);
        ValidateUserInput userInput = new ValidateUserInput();
        if (userInput.userInputAsString().equals(saveKeyWord)) {
            userName = askForUserName(saveNameMsg);
        }
        return userName;
    }
    
    private static String askForUserName(String saveNameMsg) {
        // Ask user for name return name
        System.out.print(saveNameMsg);
        ValidateUserInput userInput = new ValidateUserInput();
        String name = userInput.userInputAsString();
        return name;
    }

    public void addLowScore(UserStats userStats) {
        if (lowScores.size() < MAX_LOW_SCORES) {
            lowScores.offer(userStats);
        } else if (lowScores.peek().getScore() > userStats.getScore()) {
            lowScores.poll();
            lowScores.offer(userStats);
        }
    }

    public static void showLowScore() {
        System.out.println("------- LOWSCORES -------");
        for (UserStats userStats : lowScores) {
            System.out.println(userStats.toString());
        }
        System.out.println("-------------------------");
    }
}
