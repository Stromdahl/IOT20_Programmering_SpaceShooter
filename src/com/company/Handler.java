package com.company;

import java.awt.*;
import java.util.LinkedList;

public class Handler {

	LinkedList<gameObject> object = new LinkedList<gameObject>();

	public void tick(){
		for(int i = 0; i< object.size(); i++){
			gameObject tempObject = object.get(i);
			
			tempObject.tick();
		}
	}
	
	public void render(Graphics g){
		for(int i = 0; i< object.size(); i++){
			gameObject tempObject = object.get(i);
			
			tempObject.render(g);
		}
	}
	
	public void addObject(gameObject object){
		this.object.add(object);
	}
	
	public void removeObject(gameObject object){
		this.object.remove(object);
	}
	
}
