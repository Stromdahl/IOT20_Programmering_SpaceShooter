package com.company;

import java.awt.*;

public abstract class gameObject {

	protected int x,y,s;
	protected int sizeX,sizeY;
	protected int velX,velY;
	protected ID id;
	
	public gameObject(int x, int y,int sizeX, int sizeY, ID id){
		this.x = x;
		this.y = y;
		this.s = 5;
		this.sizeX = sizeX;
		this.sizeY = sizeY;
		this.id = id;
		
	}
	
	public abstract void tick();
	public abstract void render(Graphics g);
	
	public void setX(int x){
		this.x = x;
	}
	public void setY(int y){
		this.y = y;
	}
	public void setS(int s){
		this.s = s;
	}
	public void setId(ID id){
		this.id = id;
	}
	public void setVelX(int velX){
		this.velX = velX;
	}
	public void setVelY(int velY){
		this.velY = velY;
	}
	
	
	public int getX(){
		return x;
	}
	public int getY(){
		return y;
	}
	public int getSizeX(){
		return this.sizeX;
	}
	public int getSizeY(){
		return this.sizeY;
	}
	public int getVelX(){
		return this.velX;
	}
	public int getVelY(){
		return this.velY;
	}
	public ID getId(){
		return this.id;
	}
	
}
