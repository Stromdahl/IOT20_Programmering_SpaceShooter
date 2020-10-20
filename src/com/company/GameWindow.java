package com.company;

import com.company.gameObjects.GameObject;

import javax.swing.*;
import java.awt.*;

public class GameWindow extends JFrame {
    public static final int SCREEN_WIDTH = 1000;
    public static final int SCREEN_HEIGHT = 600;

    private static final long serialVersionUID = 1L;

    public GameWindow(Game game) {
        game.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        game.setFocusable(true);
        this.add(game);
        this.setTitle("Physics");
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setResizable(false);
        this.pack();
        this.setVisible(true);
        this.setLocationRelativeTo(null);
    }
}