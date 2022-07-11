package com.java.game.main.entities;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import com.java.game.graphics.Render;
import com.java.game.main.Game;
import com.java.game.main.Game.STATUS;
import com.java.game.main.Handler;
import com.java.game.main.ID;
import com.java.game.main.object.Object;
import com.java.game.main.sound.Sound;

public class Player extends Object {	
	private Handler handler;
	private Game game;
	private BufferedImage playerStart, playerOneModel, playerTwoModel, playerTreeModel;
	private Sound hitSound = new Sound("res/sound/hit.wav", 0.6);
	private Sound ammoSound = new Sound("res/sound/pickupAmmo.wav", 0.6);
	private Sound powerupSound = new Sound("res/sound/powerup.wav", 0.6);
	
	private boolean po, pt, ptt; 
	private int randomWeapon = (int) (0 + Math.random() * 2);  
	
	public Player(int x, int y, ID id, Handler handler, Game game, Render render) {
		super(x, y, id, render);
		
		this.handler = handler;
		this.game = game;
	
		
		playerStart = render.getTextures(1, 4, 16, 16);
		playerOneModel = render.getTextures(2, 4, 16, 16);
		playerTwoModel = render.getTextures(3, 4, 16, 16);
		playerTreeModel = render.getTextures(4, 4, 16, 16);
	}

	public void updates() {
		x += velX;
		y += velY;

		collision();	
		
		if(handler.isUp()) velY = -4;
		else if(!handler.isDown()) velY = 0;
	
		if(handler.isDown()) velY = 4;
		else if(!handler.isUp()) velY = 0;
		
		if(handler.isRight()) velX = 4;
		else if(!handler.isLeft()) velX = 0;
		
		if(handler.isLeft()) velX =  -4;
		else if(!handler.isRight()) velX = 0;
		
	}

	public void collision() {
		for(int i = 0; i < handler.object.size(); i++) {
			Object obj = handler.object.get(i);
			if(obj.getId() == ID.Block) {
				if(getBounds().intersects(obj.getBounds())) {
					x += velX * -1;
					y += velY * -1;
				}
			}
	
		if(obj.getId() == ID.AmmoBox) {
			if(getBounds().intersects(obj.getBounds())) {
					game.ammo += 2;
					game.score += 8;
					ammoSound.audio();
					ammoSound.setVolume();
					handler.removeObject(obj);
				}
			}	
	
		if(obj.getId() == ID.Chest) {
			if(getBounds().intersects(obj.getBounds())) {
				if(randomWeapon == 0) {
					 po = true;
					 game.hpPlayer += 1;
					 game.ammo += 10;
					 powerupSound.audio();
					 powerupSound.setVolume();
				} 
				if(randomWeapon == 1) {
					 pt = true;
					 game.hpPlayer += 15;
					 game.ammo += 3;
					 powerupSound.audio();
					 powerupSound.setVolume();
				} 
				
				if(randomWeapon == 2) {
					 ptt = true;
					 game.hpPlayer += 15;
					 game.ammo += 15;
					 powerupSound.audio();
					 powerupSound.setVolume();
				}
				
				game.score += 20;
				handler.removeObject(obj);
				}
			}	
	
		if(obj.getId() == ID.Transition) {
			if(getBounds().intersects(obj.getBounds())) {
				Game.status = STATUS.GAMEWIN;		
				}
			}	
		
		if(obj.getId() == ID.Skeleton) {
			if(getBounds().intersects(obj.getBounds())) {
					game.hpPlayer--;
					hitSound.audio();
					hitSound.setVolume();
				}
			}	
		}
	}
	
	public void render(Graphics graphics) {
		 graphics.drawImage(playerStart, x, y, 32, 32, null);
		 if (po) graphics.drawImage(playerOneModel, x, y, 32, 32, null);
		 if (pt) graphics.drawImage(playerTwoModel, x, y, 32, 32, null);
		 if (ptt) graphics.drawImage(playerTreeModel, x, y, 32, 32, null);
		 
	}

	public Rectangle getBounds() {
		return new Rectangle(x, y, 32, 32);
	}	
}