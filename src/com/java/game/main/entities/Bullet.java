package com.java.game.main.entities;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import com.java.game.graphics.Render;
import com.java.game.main.Handler;
import com.java.game.main.ID;
import com.java.game.main.object.Object;

public class Bullet extends Object {
	private Handler handler;
	private BufferedImage bullet;
	
	private int speed = 5;
	
	public Bullet(int x, int y, ID id, Handler handler, int mouseX, int mouseY, Render render) {
		super(x, y, id, render);
	
		this.handler = handler;

		bullet = render.getTextures(1, 3, 16, 16);
		
		double bulletPhysic = Math.toDegrees(Math.atan2(mouseY - y, mouseX - x));
		velX = (float) (Math.cos(Math.toRadians(bulletPhysic)) * speed);
		velY = (float) (Math.sin(Math.toRadians(bulletPhysic)) * speed);
	}
	
	public void updates() {
		x += velX;
		y += velY;
	
		for(int i = 0; i < handler.object.size(); i++) {
			Object obj = handler.object.get(i);
			if(obj.getId() == ID.Block) {
				if(getBounds().intersects(obj.getBounds())) handler.removeObject(this);
			}
		}
	}

	public void render(Graphics graphics) {
		graphics.drawImage(bullet, x, y, 32, 32, null);
		
	}

	public Rectangle getBounds() {
		return new Rectangle(x, y, 8, 8);
	}	
}