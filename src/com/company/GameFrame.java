package com.company;

import javax.swing.*;
import java.awt.*;

public class GameFrame extends JFrame {
    public static final int SCREEN_WIDTH = 600;
    public static final int SCREEN_HEIGHT = 600;

    private static final long serialVersionUID = 1L;

    public GameFrame() {
        GamePanel gamePanel = new GamePanel();
        gamePanel.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        gamePanel.setFocusable(true);
        this.add(gamePanel);
        this.setTitle("Physics");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.pack();
        this.setVisible(true);
        this.setLocationRelativeTo(null);

        gamePanel.start();
    }
}
