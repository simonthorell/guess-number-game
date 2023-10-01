import java.util.PriorityQueue;

public class LowScore {
    private static PriorityQueue<UserStats> lowScores;    

    public LowScore() {
        lowScores = new PriorityQueue<>();
    }

    public boolean checkIfLowScore(int score, int MAX_LOW_SCORES) {
        // Check if empty slot is available, if not check if score is lower than current lowscores.
        if (lowScores.size() < MAX_LOW_SCORES) {
            return true;
        } else if (lowScores.peek().getScore() > score) {
            return true;
        } else {
            return false;
        }
    }
    
    public void addLowScore(UserStats user, int MAX_LOW_SCORES) {
        // Check if empty slot is available in lowscores list and add new lowscore. 
        if (lowScores.size() < MAX_LOW_SCORES) {
            lowScores.offer(user);
        // If no empty slots, kick current highest lowscore and add new lowscore.
        } else if (lowScores.peek().getScore() > user.getScore()) {
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
