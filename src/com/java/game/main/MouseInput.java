package com.java.game.main;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import com.java.game.graphics.Render;
import com.java.game.main.entities.Bullet;
import com.java.game.main.object.Object;
import com.java.game.main.sound.Sound;

public class MouseInput implements MouseListener {
	private Handler handler;
	private Camera camera;
	private Game game; 
	private Render render;
	
	private Sound shootSound = new Sound("res/sound/shoot.wav", 0.6);
	
	public MouseInput(Handler handler, Camera camera, Game game, Render render) {
		this.handler = handler;
		this.camera = camera;
		this.game = game;
		this.render = render;
	}
		
	public void mouseClicked(MouseEvent e) {
		
	}

	public void mousePressed(MouseEvent e) {
		int mouseX = (int) (e.getX() + camera.getX());
		int mouseY = (int) (e.getY() + camera.getY());
		
		for(int i = 0; i < handler.object.size(); i++) {
			Object obj = handler.object.get(i);
			if(obj.getId() == ID.Player && game.ammo >= 1) {
				handler.addObject(new Bullet(obj.getX() + 16, obj.getY() + 24, ID.Bullet, handler, mouseX, mouseY, render));
				shootSound.audio();
				shootSound.setVolume();
				game.ammo--;
			}		
		}
	}

	public void mouseReleased(MouseEvent e) {
		
	}

	public void mouseEntered(MouseEvent e) {	
	
	}

	public void mouseExited(MouseEvent e) {		
	
	}
} 