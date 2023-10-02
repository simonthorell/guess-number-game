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
        int valueComparison = Integer.compare(other.getScore(), this.score);
        if (valueComparison != 0) {
            return valueComparison;  // sort by value
        } else {
            return this.name.compareTo(other.name);  // if values are equal, sort by name
        }
    }

    public String toString(String nameString, String scoreString) {
        return nameString + ": " + name + "\t" + scoreString +  ": " + score;
    }
    
}