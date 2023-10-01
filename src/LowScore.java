import java.util.PriorityQueue;

public class LowScore {
    private static PriorityQueue<UserStats> lowScores;
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

    public void showLowScore() {
        System.out.println("------- LOWSCORES -------");
        for (UserStats user : lowScores) {
            System.out.println(user.toString());
        }
        System.out.println("-------------------------");
    }
}
