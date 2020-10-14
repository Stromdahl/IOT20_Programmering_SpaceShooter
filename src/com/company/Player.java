package com.company;

import java.awt.*;

public class Player extends gameObject{

	public Player(int x, int y,int sizeX,int sizeY, ID id) {
		super(x, y, sizeX, sizeY, id);
		
	}

	public void tick(){
		x += velX;
		y += velY;
		
		this.s = 10;
		
		x = Game.clamp(x, 0, Game.WIDTH - 38);
		y = Game.clamp(y, 0, Game.HEIGHT- 60);
	}

	public void render(Graphics g) {
		if(id == ID.Player){ g.setColor(Color.white); }
		else if(id == ID.Player2){ g.setColor(Color.blue); }
		else if(id == ID.Enemy){ g.setColor(Color.red); }
		
		g.fillRect(x, y, sizeX, sizeY);
	}

}
