package com.java.game.main.object;

import java.awt.Graphics;
import java.awt.Rectangle;

import com.java.game.graphics.Render;
import com.java.game.main.ID;

public abstract class Object {
	public int x,y;
	public float velX, velY;
	
	private ID id;
	@SuppressWarnings("unused")
	private Render render;
	
	public Object(int x, int y, ID id, Render render) {
		this.x = x;
		this.y = y;
		this.id = id;
		this.render = render;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public float getVelX() {
		return velX;
	}

	public void setVelX(float velX) {
		this.velX = velX;
	}

	public float getVelY() {
		return velY;
	}

	public void setVelY(float velY) {
		this.velY = velY;
	}

	public ID getId() {
		return id;
	}

	public void setId(ID id) {
		this.id = id;
	}

	public abstract void updates();
	public abstract void render(Graphics graphics);
	public abstract Rectangle getBounds();
}