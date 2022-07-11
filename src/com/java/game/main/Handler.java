package com.java.game.main;

import java.awt.Graphics;
import java.util.LinkedList;

import com.java.game.main.object.Object;

public class Handler {
	public LinkedList<Object> object = new LinkedList<Object>(); 

	public boolean up, down, left, right, start;
	
	public Handler() {
		up = down = left = right = start = false;
	}
	
	public void updates() {
		for(int i = 0; i < object.size(); i++) {
			Object obj = object.get(i);
			obj.updates();
		}
	}
	
	public void render(Graphics graphics) {
		for(int i = 0; i < object.size(); i++) {
			Object obj = object.get(i);
			obj.render(graphics);
		}
	}
	
	public void addObject(Object obj) {
		object.add(obj);
	}
	
	public void removeObject(Object obj) {
		object.remove(obj);
	}

	public LinkedList<Object> getObject() {
		return object;
	}

	public void setObject(LinkedList<Object> object) {
		this.object = object;
	}

	public boolean isUp() {
		return up;
	}

	public void setUp(boolean up) {
		this.up = up;
	}

	public boolean isDown() {
		return down;
	}

	public void setDown(boolean down) {
		this.down = down;
	}

	public boolean isLeft() {
		return left;
	}

	public void setLeft(boolean left) {
		this.left = left;
	}

	public boolean isRight() {
		return right;
	}

	public void setRight(boolean right) {
		this.right = right;
	}	
}