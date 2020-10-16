package com.company.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Keyboard implements KeyListener {

    public static boolean UP, LEFT, DOWN, RIGHT, SPACE;

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_W:
                UP = true;
                break;
            case KeyEvent.VK_A:
                LEFT = true;
                break;
            case KeyEvent.VK_S:
                DOWN = true;
                break;
            case KeyEvent.VK_D:
                RIGHT = true;
                break;
            case KeyEvent.VK_SPACE:
                SPACE = true;
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_W:
                UP = false;
                break;
            case KeyEvent.VK_A:
                LEFT = false;
                break;
            case KeyEvent.VK_S:
                DOWN = false;
                break;
            case KeyEvent.VK_D:
                RIGHT = false;
                break;
            case KeyEvent.VK_SPACE:
                SPACE = false;
                break;
        }
    }
}
