import java.util.Comparator;
import java.util.TreeSet;

public class LowScore {
    private TreeSet<UserStats> lowScores;
    private int maxLowScores;

    public LowScore(int MAX_LOW_SCORES) {
        lowScores = new TreeSet<>(Comparator.reverseOrder());
        this.maxLowScores = MAX_LOW_SCORES;
    }

    public boolean checkIfLowScore(int userScore) {
        // Check if empty slot is available, if not check if score is lower than current lowscores.
        if (lowScores.size() < maxLowScores) {
            return true;
        } else if (lowScores.last().getScore() >= userScore) {
            return true;
        } else {
            return false;
        }
    }
    
    public void addLowScore(UserStats user) {
        // Check if empty slot is available in lowscores list and add new lowscore. 
        if (lowScores.size() < maxLowScores) {
            lowScores.add(user);
        // If no empty slots, kick current highest lowscore and add new lowscore.
        } else if (lowScores.last().getScore() >= user.getScore()) {
            lowScores.pollLast();
            lowScores.add(user);
        }
    }

    public void printLowScores(String nameString, String scoreString) {
        for (UserStats user : lowScores) {
            System.out.println(user.toString(nameString, scoreString));
        }
    }
}
