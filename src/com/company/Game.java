package com.company;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.util.LinkedList;

public class Game extends Canvas implements Runnable{

	private static final long serialVersionUID = 1L;
	public static final int HEIGHT = 480, WIDTH = HEIGHT*4/3; 
	private static final String title = "Template";
	
	
	
	Thread thread;
	private static boolean running = false;
	
	private Handler handler; 
	 
	public Game(){
		handler = new Handler();
		this.addKeyListener(new KeyInput(handler));
		
		new Window(WIDTH,HEIGHT,title,this);
		System.out.println(HEIGHT + ", " + WIDTH);
		handler.addObject(new Player(WIDTH/2, HEIGHT/2-32/2, 32, 32, ID.Player));
		handler.addObject(new Ball(194, 100, 10, 10, ID.Ball));
	}

	public synchronized void start(){
		thread = new Thread(this);
		thread.start();
		running = true;
	}
	
	public synchronized void stop(){
		try{
			thread.join();
			running = false;
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	public void run() {
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0; 
		long timer = System.currentTimeMillis();
		int frames = 0;
		while(running){
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while(delta >= 1){
				tick();
				delta--;
			}
			if(running){
				render();
			}
			frames++;
			
			if(System.currentTimeMillis() - timer > 1000){
				timer += 1000;
				//System.out.println("Fps:" + frames);
				frames = 0;
			}
		}
		stop();
	}
	
	public static int clamp(int var, int min, int max){
		if(var >= max){
			return var = max;
		}
		else if(var <= min){
			return var = min;
		}
		else {
			return var;
		}
		
	}
	
	public static int firstInList(LinkedList<Integer> keys, LinkedList<Integer> pressedKeys){
		for(int i = 0; i < pressedKeys.size(); i++){
			if(keys.contains(pressedKeys.get(i))){
				return pressedKeys.get(i);
			}
		}
		return 0;
	}
	
	private void tick() {
		handler.tick();
	}

	private void render() {
		BufferStrategy bs = this.getBufferStrategy();
		if(bs == null){
			this.createBufferStrategy(3);
			return;
		}
		Graphics g = bs.getDrawGraphics();
		
		g.setColor(Color.black);
		g.fillRect(0, 0, getWidth(), getHeight());
		
		handler.render(g);
		
		
		
		g.dispose();
		bs.show();
	}

	public static void main(String[] args) {
		new Game();
	}

}
