package com.java.game.main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import com.java.game.main.object.Object;

public class Controll implements KeyListener {
	private Handler handler;
	
	public Controll(Handler handler) {
		this.handler = handler;
	}
	
	public void keyTyped(KeyEvent e) {		
		
	}

	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		
		for(int i = 0; i < handler.object.size(); i++) {
			Object obj = handler.object.get(i);
			if(obj.getId() == ID.Player) {
				if(key == KeyEvent.VK_W) handler.up = true;
				if(key == KeyEvent.VK_S) handler.down = true;
				if(key == KeyEvent.VK_A) handler.left = true;
				if(key == KeyEvent.VK_D) handler.right = true;
				if(key == KeyEvent.VK_SPACE) handler.start = true;
			}
		}
	}

	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		
		for(int i = 0; i < handler.object.size(); i++) {
			Object obj = handler.object.get(i);
			if(obj.getId() == ID.Player) {
				if(key == KeyEvent.VK_W) handler.up = false;
				if(key == KeyEvent.VK_S) handler.down = false;
				if(key == KeyEvent.VK_A) handler.left = false;
				if(key == KeyEvent.VK_D) handler.right = false;
				if(key == KeyEvent.VK_SPACE) handler.start = false;
			}
		}
	}
}