package com.company;

import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class HighScoreWindow extends JFrame{
    HighScoreWindow(){
        JPanel jPanel = new JPanel();
        this.getContentPane().add(jPanel, "North");
        jPanel.add(new JTextArea(getScoresFromFile()));
        this.pack();
        this.setVisible(true);
        this.setLocationRelativeTo(null);
    }

    private String getScoresFromFile(){
        String pathToScoresFile = "/res/Scores.txt";
        String scores = "";
        try (Scanner scoresFile = new Scanner(new File(pathToScoresFile))) {
            while (scoresFile.hasNext()) {
                scores += scoresFile.next() + "\n";
            }
        } catch (FileNotFoundException e) {
            return null;
        }
        return scores;
    }
}
