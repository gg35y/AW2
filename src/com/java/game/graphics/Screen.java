package com.java.game.graphics;

import java.awt.Dimension;

import javax.swing.JFrame;

import com.java.game.main.Game;

public class Screen {
	public Screen(int width, int height, int scale, String name, Game game) {
		JFrame jFrame = new JFrame(name);
		
		jFrame.setPreferredSize(new Dimension(width * scale, height * scale));
		jFrame.setMaximumSize(new Dimension(width * scale, height * scale));
		jFrame.setMinimumSize(new Dimension(width * scale, height * scale));
		jFrame.add(game);
		jFrame.pack();
		jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jFrame.setLocationRelativeTo(null);
		jFrame.setResizable(false);	
		jFrame.setVisible(true);
	}
} 