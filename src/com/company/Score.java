package com.company;

public class Score {
    private final String name;
    private int score;

    public Score(String name, int score) {
        this.name = name;
        this.score = score;
    }

    public Score(String name) {
        this(name, 0);
    }

    /**
     * Returns the current score
     *
     * @return Int with the current score
     */
    public int getScore() {
        return score;
    }

    /**
     * Returns the name of the player wirth this score
     *
     * @return Strin width the name of the player
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the name and score with concatenated together with a comma between
     *
     * @return String with the name and score
     */
    public String toString() {
        return name + "," + score;
    }

    /**
     * Adds a new score to the current score
     *
     * @param score The score to be added to the current score
     */
    public void addScore(int score) {
        this.score += score;
    }
}
