package util;

public class User {
    private static final int INITIAL_GOLD = 800;
    private static final int INITIAL_SCORE = 0;

    private int score;
    private int gold;

    public User() {
        this.score = INITIAL_SCORE;
        this.gold = INITIAL_GOLD;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getGold() {
        return gold;
    }

    public void setGold(int gold) {
        this.gold = gold;
    }

}
