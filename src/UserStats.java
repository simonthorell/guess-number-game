public class UserStats implements Comparable<UserStats> {
    private String name;
    private int score;

    public UserStats(String name, int score) {
        this.name = name;
        this.score = score;
    }

    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setScore(int score) {
        this.score = score;
    }

    @Override
    public int compareTo(UserStats other) {
        // Higher scores are lower priority (i.e., we're keeping the lowest scores)
        return Integer.compare(other.getScore(), this.score);
    }

    public String toString() {
        return "Name: " + name + "\t Score: " + score;
    }
    
}