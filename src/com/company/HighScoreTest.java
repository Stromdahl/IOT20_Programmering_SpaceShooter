package com.company;

import org.junit.jupiter.api.Assertions;import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

public class HighScoreTest {

    @Test
    @DisplayName("Test that score is added to the list at correct index")
    void testCreateFile(){
        String path = "res\\high_score.txt";
        //noinspection ResultOfMethodCallIgnored
        new File(path).delete();
        assertFalse(new File(path).exists());
        HighScore highScore = new HighScore();
        highScore.createFile();
        assertTrue(new File(path).exists());
    }

    @Test
    @DisplayName("Test that score is added to the list at correct index")
    void testAddsScoreToListSorted(){
        HighScore highScore = new HighScore();
        highScore.clear();
        highScore.addScore(new Score("Player1", 1000));
        highScore.addScore(new Score("Player2", 2000));
        highScore.addScore(new Score("Player3", 500));

        String expected = "Player3 500\nPlayer1 1000\nPlayer2 2000\n";
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
    void testToString(){
        HighScore highScore = new HighScore();
        highScore.clear();
        highScore.addScore(new Score("Player1", 1000));
        highScore.addScore(new Score("Player2", 2000));
        highScore.addScore(new Score("Player3", 3000));

        String expected = "Player1 1000\nPlayer2 2000\nPlayer3 3000\n";
        Assertions.assertEquals(expected, highScore.toString());
    }
}
