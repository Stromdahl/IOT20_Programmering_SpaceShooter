package com.company;

import java.awt.*;

public class Ball extends gameObject {

	public Ball(int x, int y,int sizeX, int sizeY, ID id) {
		super(x, y,sizeX, sizeY, id);
		
		velX = 5;
		velY = 5;
		x = Game.WIDTH;
		
	}

	
	public void tick() {
		x += velX;
		y += velY;
		
		if(x <= 0 || x >= Game.WIDTH - 15) velX *= -1;
		if(y <= 0 || y >= Game.HEIGHT - 38) velY *= -1;
		
	}

	
	public void render(Graphics g) {
	
		g.setColor(Color.red);
		
		g.fillRect(x, y, sizeY, sizeX);
		
	}

}
