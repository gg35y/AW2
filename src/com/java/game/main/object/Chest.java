package com.java.game.main.object;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import com.java.game.graphics.Render;
import com.java.game.main.ID;

public class Chest extends Object {
	private BufferedImage chest;
		
	public Chest(int x, int y, ID id, Render render) {
		super(x, y, id, render);
	
		chest = render.getTextures(3, 3, 16, 16);
	}

	public void updates() {
		x += velX;
		y += velY;
	}

	public void render(Graphics graphics) {
		graphics.drawImage(chest, x, y, 32, 32, null);
	}

	public Rectangle getBounds() {
		return new Rectangle(x, y, 32, 32);
	}
}