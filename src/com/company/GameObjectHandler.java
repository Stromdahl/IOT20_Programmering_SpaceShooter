package com.company;

import com.company.gameObjects.GameObject;

import java.awt.*;
import java.util.ArrayList;

public class GameObjectHandler {

    private ArrayList<GameObject> gameObjects = new ArrayList<>();

    public void add(GameObject gameObject){
        gameObjects.add(gameObject);
    }

    public void remove(GameObject gameObject){
        gameObjects.remove(gameObject);
    }

    public GameObject get(int i){
        return gameObjects.get(i);
    }

    public void update(){
        for (int i = gameObjects.size() - 1; i >= 0; i--) {
            gameObjects.get(i).update();
        }
    }

    public void display(Graphics graphics){
        for (int i = gameObjects.size() - 1; i >= 0; i--) {
            gameObjects.get(i).display(graphics);
        }
    }

    public ArrayList<GameObject> getGameObjects() {
        return gameObjects;
    }

    public int getNumberOfObjects() {
        return gameObjects.size();
    }
}
