package com.java.game.main.menu;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;


public class Menu {
	
	private Image menu = new ImageIcon("res/titlemenu.png").getImage();
	
	public Menu() {
	}
	
	public void render(Graphics graphics) {
		graphics.drawImage(menu, 0, 0, 200 * 3, 180 * 3, null);
	}
}