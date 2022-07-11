package com.java.game.main;

import com.java.game.main.object.Object;

public class Camera {
	private float x,y;
	
	public Camera(float x, float y) {
		this.x = x;
		this.y = y;
	}

	public void updates(Object object) {
		x += ((object.getX() - x) - (200 * 3) / 2);
		y += ((object.getY() - y) - (180 * 3) / 2);
	
		if(x <= 0) x = 0;
		if(x >= 200 + 1255) x = 200 + 1255;
		if(y <= 0) y = 0;
		if(y >= 180 + 1355) y = 180 + 1355;
	}

	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}	
}