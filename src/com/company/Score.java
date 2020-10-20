package com.company;

public class Score {
    public static int score = 0;

    public static void addScore(int score_) {
        score += score_;
    }

    public static int getScore() {
        return score;
    }
}
