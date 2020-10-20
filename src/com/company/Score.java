package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Score {
    private int score = 0;

    public void addScore(int score) {
        this.score += score;
    }

    public int getScore() {
        return score;
    }

    public ArrayList<String> getScoresFromFile(){
        String pathToScoresFile = "\\res\\Score.txt";
        ArrayList<String> scores = new ArrayList<>();
        try (Scanner scoresFile = new Scanner(new File(pathToScoresFile))) {
            while (scoresFile.hasNext()) {
                scores.add(scoresFile.next());
            }
        } catch (FileNotFoundException e) {
            return null;
        }
        return scores;
    }

    public static void writeScoresToFile(ArrayList<String> scores){
        String pathToScoresFile = "\\res\\Scores.txt";
        FileWriter scoresFile;
        try {
            scoresFile = new FileWriter(pathToScoresFile);
            for (String score: scores) {

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
