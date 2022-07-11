package com.java.game.main.entities;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.Random;

import com.java.game.graphics.Animation;
import com.java.game.graphics.Render;
import com.java.game.main.Handler;
import com.java.game.main.ID;
import com.java.game.main.object.Object;
import com.java.game.main.sound.Sound;

public class Skeleton extends Object {
	private Handler handler;
	private Random random;
	private Animation animation; 
	private BufferedImage[] skeleton = new BufferedImage[2];
	
	private Sound hitSkeletonSound = new Sound("res/sound/hitEnemy.wav", 0.6);
	
	private int movement, hp = 20;
	
	public Skeleton(int x, int y, ID id, Handler handler, Render render) {
		super(x, y, id, render);
		
		random = new Random();
		
		this.handler = handler;
		
		skeleton[0] = render.getTextures(1, 5, 16, 16); 
		skeleton[1] = render.getTextures(2, 5, 16, 16); 
	
		animation = new Animation(2, skeleton[0], skeleton[1]);
		
	}

	public void updates() {
		x += velX;
		y += velY;
	
		movement = random.nextInt(10);
		if(movement == 0) {
			velX = (random.nextInt(4 - -4) + -4);
			velY = (random.nextInt(4 - -4) + -4);	
		}
	
		for(int i = 0; i < handler.object.size(); i++) {
			Object obj = handler.object.get(i);
			
			if(obj.getId() == ID.Block) {
				if(getBoundsComponent().intersects(obj.getBounds())) {
					x += (velX * 2) * -1;
					y += (velY * 2) * -1;
					velX *= -1;
					velY *= -1;
				}else if(movement == 0) {
					velX = (random.nextInt(4 - -4) + -4);
					velY = (random.nextInt(4 - -4) + -4);
				}
			}
		
			if(obj.getId() == ID.Bullet) {
				if(getBounds().intersects(obj.getBounds())) {
					hp -= 10;
					hitSkeletonSound.audio();
					hitSkeletonSound.setVolume();
					handler.removeObject(obj);
				}
			}
		}
		
		if(hp <= 0) handler.removeObject(this);
	
		animation.runAnimation();
	}

	public void render(Graphics graphics) {				
		animation.drawAnimation(graphics, x, y, 32, 32, 0);
	}

	public Rectangle getBounds() {
		return new Rectangle(x, y, 32, 32);
	}
	
	public Rectangle getBoundsComponent() {
		return new Rectangle(x - 16, y - 16, 32, 32);
	}
}