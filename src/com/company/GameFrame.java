package com.company;

import javax.swing.*;
import java.awt.*;

public class GameFrame extends Canvas {

    private static final long serialVersionUID = 1L;

    public GameFrame(int width, int height, GamePanel game) {
        JFrame Frame = new JFrame("PhysicsDemo");

        Frame.setPreferredSize(new Dimension(width, height));
        Frame.setMaximumSize(new Dimension(width, height));
        Frame.setMinimumSize(new Dimension(width, height));

        Frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Frame.setResizable(false);

        Frame.setLocationRelativeTo(null);
        Frame.setFocusable(true);
        Frame.add(game);
        Frame.setVisible(true);
        game.start();
    }
}
