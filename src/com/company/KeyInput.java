package com.company;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.LinkedList;


public class KeyInput extends KeyAdapter{

	private Handler handler;
	private int vel;
	LinkedList<Integer> keys = new LinkedList<Integer>();


	public KeyInput(Handler handler){
		this.handler = handler;


	}

	public void keyPressed(KeyEvent e){
		int key = e.getKeyCode();

		//exit game if escape key is pressed
		if(key == KeyEvent.VK_ESCAPE) System.exit(1);
		
		//add the ascii value of the key
		if(! keys.contains(key)) keys.add(key);
		System.out.println(keys.size());

		
		for(int i = 0; i<handler.object.size(); i++){
			gameObject tempObject	= handler.object.get(i);

			if(tempObject.getId() == ID.Player){
				//check keyPress for Player 1
				vel = tempObject.s;
				if(key == KeyEvent.VK_W) tempObject.setVelY(-vel);
				if(key == KeyEvent.VK_A) tempObject.setVelX(-vel);
				if(key == KeyEvent.VK_S) tempObject.setVelY(vel);
				if(key == KeyEvent.VK_D) tempObject.setVelX(vel);
			}
		}
		
	}

	public void keyReleased(KeyEvent e){
		int key = e.getKeyCode();

		keys.removeFirstOccurrence(key);
		System.out.println(keys.size());


		//go thru every object in handler
		for(int i = 0; i<handler.object.size(); i++){
			gameObject tempObject	= handler.object.get(i);

			//create a temporary object
			if(tempObject.getId() == ID.Player){
				//check keyReleased for Player 1
				vel = tempObject.s;
				if(keys.contains(KeyEvent.VK_S)) tempObject.setVelY(vel);
				else if(keys.contains(KeyEvent.VK_W))  tempObject.setVelY(-vel);
				else tempObject.setVelY(0);

				if(keys.contains(KeyEvent.VK_D)) tempObject.setVelX(vel);
				else if(keys.contains(KeyEvent.VK_A))  tempObject.setVelX(-vel);
				else tempObject.setVelX(0);

				//if(key == KeyEvent.VK_A) tempObject.setVelX(0);
				//if(key == KeyEvent.VK_D) tempObject.setVelX(0);
			}
		}
	}

}
