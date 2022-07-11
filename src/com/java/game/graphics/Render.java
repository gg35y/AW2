package com.java.game.graphics;

import java.awt.image.BufferedImage;

public class Render {
	private BufferedImage image; 
	
	public Render(BufferedImage image) {
		this.image = image;
	}
	
	public BufferedImage getTextures(int x, int y, int width, int height) {
		return image.getSubimage((x * 16) - 16, (y * 16) - 16, width, height); 
	}
} 