package com.company;

import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class HighScore extends JFrame{
    private final String filePath ="res/high_score.txt";
    private ArrayList<Score> scores;

    public HighScore(){
        this.scores = getScoresFromFile();
    }

    /**
     * Reads the scores stored in the file
     * @return a arrayList with scores
     */
    public ArrayList<Score> getScoresFromFile(){
        ArrayList<Score> scores = new ArrayList<>();
        try (Scanner scanner = new Scanner(new File(filePath))) {
            while(scanner.hasNextLine()){
                String name = scanner.next();
                int score = scanner.nextInt();
                scores.add(new Score(name, score));
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
        return scores;
    }

    /**
     * Creates a file to the path if the file doesn't exists
     */
    public void createFile(){
        try {
            //noinspection ResultOfMethodCallIgnored
            new File(this.filePath).createNewFile();
        } catch (IOException e) {
            System.out.println("IO exception");
        }
    }

    /**
     * Adds the score to the correct index in the ArrayList so the ArrayList still is sorted
     * @param score The score that will be added
     */
    public void addScore(Score score) {
        for (int i = 0, scoresSize = scores.size(); i < scoresSize; i++) {
            Score tempScore = scores.get(i);
            if(tempScore.getScore() < score.getScore()) continue;
            scores.add(i, score);
            return;
        }
        scores.add(score);
    }

    /**
     * writes a string to the high score file
     * @param content the text to be written to the file
     */
    void writeFile(String content){
        try {
            FileWriter fileWriter = new FileWriter(this.filePath);
            fileWriter.write(String.valueOf(this.toString()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Returns string with all the scores. The scores are seperated with a new line
     * @return a string width all the scores
     */
    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (Score score : scores) {
            stringBuilder.append(score.toString());
            stringBuilder.append("\n");
        }
        return stringBuilder.toString();
    }

    /**
     * Removes every score from the scores arrayList
     */
    public void clear(){
        this.scores.clear();
    }

    /**
     * Returns the number of scores in the ArrayList
     * @return the number of scores in the ArrayList
     */
    public int getNumberOfScores(){
        return scores.size();
    }

}
