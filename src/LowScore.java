import java.util.PriorityQueue;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

public class LowScore {
    private PriorityQueue<UserStats> lowScores;
    private int maxLowScores;

    public LowScore(int MAX_LOW_SCORES) {
        lowScores = new PriorityQueue<>();
        this.maxLowScores = MAX_LOW_SCORES;
    }

    public boolean checkIfLowScore(int userScore) {
        // Check if empty slot is available, if not check if score is lower than current lowscores.
        if (lowScores.size() < maxLowScores) {
            return true;
        } else if (lowScores.peek().getScore() >= userScore) {
            return true;
        } else {
            return false;
        }
    }
    
    public void addLowScore(UserStats user) {
        // Check if empty slot is available in lowscores list and add new lowscore. 
        if (lowScores.size() < maxLowScores) {
            lowScores.offer(user);
        // If no empty slots, kick current highest lowscore and add new lowscore.
        } else if (lowScores.peek().getScore() >= user.getScore()) {
            lowScores.poll();
            lowScores.offer(user);
        }
    }

    public void printLowScores(String nameString, String scoreString) {
        // Create a new list and sort it in descending order in order to print lowscores in sorted order. 
        List<UserStats> sortedLowScores = new ArrayList<>(lowScores);
        Collections.sort(sortedLowScores, Collections.reverseOrder());

        for (UserStats user : sortedLowScores) {
            System.out.println(user.toString(nameString, scoreString));
        }
    }
}
