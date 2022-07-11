package com.java.game.main.object;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import com.java.game.graphics.Render;
import com.java.game.main.ID;

public class AmmoBox extends Object{
	private BufferedImage ammoBox;
	
	public AmmoBox(int x, int y, ID id, Render render) {
		super(x, y, id, render);
	
		ammoBox = render.getTextures(2, 3, 16, 16);
	}

	public void updates() {
		
	}

	public void render(Graphics graphics) {
		graphics.drawImage(ammoBox, x, y, 32, 32, null);
	}

	public Rectangle getBounds() {
		return new Rectangle(x, y, 32, 32);
	}
}