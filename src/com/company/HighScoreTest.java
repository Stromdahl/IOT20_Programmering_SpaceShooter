package com.company;

import com.company.HighScore;
import com.company.Score;
import org.junit.jupiter.api.Assertions;import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileWriter;

import static org.junit.jupiter.api.Assertions.*;

public class HighScoreTest {

    @Test
    @DisplayName("Test that score is added to the list at correct index")
    void testCreateFile(){
        String path = "res\\TestData\\test_high_score4.txt";
        //noinspection ResultOfMethodCallIgnored
        new File(path).delete();
        assertFalse(new File(path).exists());
        HighScore highScore = new HighScore("res\\TestData\\test_high_score4.txt");
        highScore.createFile();
        assertTrue(new File(path).exists());
    }

    @Test
    @DisplayName("Test that score is added to the list at correct index")
    void testAddsScoreToListSorted(){
        HighScore highScore = new HighScore("res\\TestData\\test_high_score3.txt");
        highScore.clear();
        highScore.addScore(new Score("Player1", 1000));
        highScore.addScore(new Score("Player2", 2000));
        highScore.addScore(new Score("Player3", 500));

        String expected = "Player3,500\nPlayer1,1000\nPlayer2,2000\n";
        Assertions.assertEquals(expected, highScore.toString());
    }

    @Test
    @DisplayName("Test that score is added to the list at correct index")
    void testDoesntAddScoreToListNotSorted(){
        HighScore highScore = new HighScore();
        highScore.clear();
        highScore.addScore(new Score("Player1", 1000));
        highScore.addScore(new Score("Player2", 2000));
        highScore.addScore(new Score("Player3", 500));

        String expected = "Player1 1000\nPlayer2 2000\nPlayer3 500\n";
        Assertions.assertNotEquals(expected, highScore.toString());
    }

    @Test
    @DisplayName("Test that the method reads the highscore file correctly")
    void testReadFile(){
        HighScore highScore = new HighScore("res\\TestData\\test_high_score.txt");
        assertEquals("Player1,10", highScore.getScoresFromFile().get(0).toString());
        assertEquals("Player2,20", highScore.getScoresFromFile().get(1).toString());
        assertEquals("Player3,30", highScore.getScoresFromFile().get(2).toString());
    }

    @Test
    @DisplayName("Test that the method ignores a score that is formatted the wrong way")
    void testReadFileIgnoresFaultyScores(){
        HighScore highScore = new HighScore("res\\TestData\\test_high_score1.txt");
        assertEquals(2, highScore.getScoresFromFile().size());
    }

    @Test
    void testToString(){
        HighScore highScore = new HighScore("res\\TestData\\test_high_score.txt");
        highScore.clear();
        highScore.addScore(new Score("Player1", 1000));
        highScore.addScore(new Score("Player2", 2000));
        highScore.addScore(new Score("Player3", 3000));

        String expected = "Player1,1000\nPlayer2,2000\nPlayer3,3000\n";
        Assertions.assertEquals(expected, highScore.toString());
    }

    @Test
    void testWriteFile(){
        String filePath =  "res\\TestData\\test_high_score6.txt";
        //noinspection ResultOfMethodCallIgnored
        new File(filePath).delete();
        HighScore highScore = new HighScore(filePath);
        highScore.createFile();

        highScore.addScore(new Score("Player1", 1000));
        highScore.addScore(new Score("Player2", 2000));
        highScore.addScore(new Score("Player3", 3000));
        highScore.writeFile();

        assertEquals("Player1,1000", highScore.getScoresFromFile().get(0).toString());
        assertEquals("Player2,2000", highScore.getScoresFromFile().get(1).toString());
        assertEquals("Player3,3000", highScore.getScoresFromFile().get(2).toString());
    }
}
