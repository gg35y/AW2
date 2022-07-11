package com.java.game.main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;

import com.java.game.graphics.Loader;
import com.java.game.graphics.Screen;
import com.java.game.main.entities.FinalBoss;
import com.java.game.main.entities.Player;
import com.java.game.main.entities.Skeleton;
import com.java.game.main.menu.Menu;
import com.java.game.main.object.AmmoBox;
import com.java.game.main.object.Block;
import com.java.game.main.object.Chest;
import com.java.game.main.object.Transition;
import com.java.game.main.object.Tree;
import com.java.game.graphics.Render;

public class Game extends Canvas implements Runnable {	
	private static final long serialVersionUID = 1L;
	
	private boolean running = false;
	public int ammo = 10, hpPlayer = 5, score = 0;
	
	private BufferedImage levelOne = null;
	private BufferedImage textures = null;
	private BufferedImage floorCastleLevel = null;
	private Image loseTitle = new ImageIcon("res/loseTitle.png").getImage();
	private Image winTitle = new ImageIcon("res/winTitle.png").getImage();
	private Thread thread;
	private Handler handler;
	private Loader loader;
	private Camera camera;
	private Render render;
	private Menu menu; 
	
	public static enum STATUS {
		MENU, GAMESTART, GAMEOVER, GAMEWIN 
	};
	
	public static STATUS status = STATUS.MENU;
	
	public void run() {
		createBufferStrategy(3);
		requestFocus();
		long lastTime = System.nanoTime();
		final double amountOfticks = 60.0;
		double ns = 1000000000.0 /amountOfticks;
		double delta = 0;
		int frames = 0;
		long timer = System.currentTimeMillis();
		while(running){
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			if(delta >= 1){
				updates();
				delta--;	
			}

			try {
				Thread.sleep(2);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			render();
			frames++;
			
			if(System.currentTimeMillis() - timer > 1000){
				timer += 1000;
				System.out.println(frames + " fps");
				frames = 0;
			}			
		}		
		stop();
	}

	public synchronized void start() {
		if(running) return;
		
		running  = true;
		thread = new Thread(this); 
		thread.start();
	}
	
	public synchronized void stop() {
		if(!running) return;
		
		running = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public void render() {
		BufferStrategy bs = getBufferStrategy();
		Graphics graphics = bs.getDrawGraphics();
		Graphics2D graphics2D = (Graphics2D) graphics;	

	if(handler.start) status = STATUS.GAMESTART;
		
	if(status == STATUS.GAMESTART) {
		for(int xx = 0; xx < 30 * 72; xx += 16) {
			for(int yy = 0; yy < 30 * 72; yy += 16) {
				graphics.drawImage(floorCastleLevel, xx, yy, null);
			}
		}	
		
		graphics2D.translate(-camera.getX(), -camera.getY());

		handler.render(graphics);
		graphics2D.translate(camera.getX(), camera.getY());		

		graphics.setColor(Color.white);
		graphics.drawString("Кол-во здоровья: " + hpPlayer, 5, 20);
		graphics.setColor(Color.white);
		graphics.drawString("Кол-во зарядов: " + ammo, 5, 40);
		graphics.setColor(Color.white);
		graphics.drawString("Кол-во очков: " + score, 5, 60);
	}else if(status == STATUS.MENU) {
		menu.render(graphics);
		graphics.setColor(Color.WHITE);
		graphics.drawString("Нажмите <SPACE>, чтобы начать", 205, (180 * 5) / 2);
		graphics.drawString("Игра сделана gg35y 2022 [c]", 220, (190 * 5) / 2);
			
	}  
	
	if(hpPlayer <= 0) status = STATUS.GAMEOVER;	
	if(status == STATUS.GAMEOVER) graphics.drawImage(loseTitle, 200 / 2, 100, 400, 400, null);
	if(status == STATUS.GAMEWIN) graphics.drawImage(winTitle, 200 / 2, 100, 400, 400, null);
			
		graphics.dispose();
		bs.show();
	}
	
	public void updates() {	
		for(int i = 0; i < handler.object.size(); i++) {
			if(handler.object.get(i).getId() == ID.Player) camera.updates(handler.object.get(i));
		}
		
		handler.updates();	
	}
	
	public void loadLevel(BufferedImage image) {
		int width = image.getWidth();
		int height = image.getHeight();
		
		for(int xx = 0; xx < width; xx++) {
			for(int yy = 0; yy < height; yy++) {
				int pixel = image.getRGB(xx, yy);
				int red = (pixel >> 16) & 0xff;
				int green = (pixel >> 8) & 0xff;
				int blue = (pixel) & 0xff;
				
				if(red == 255) handler.addObject(new Block(xx * 32, yy * 32, ID.Block, render));
				if(blue == 255 && green == 38) handler.addObject(new Player(xx * 32, yy * 32, ID.Player, handler, this, render));
				if(blue == 0 && green == 255) handler.addObject(new Skeleton(xx * 32, yy * 32, ID.Skeleton, handler, render));
				if(red == 0 && green == 148 && blue == 255) handler.addObject(new AmmoBox(xx * 32, yy * 32, ID.AmmoBox, render));
				if(red == 127 && green == 255 && blue == 255) handler.addObject(new Chest(xx * 32, yy * 32, ID.Chest, render));
				if(red == 127 && green == 57 && blue == 34) handler.addObject(new Transition(xx * 32, yy * 32, ID.Transition, render));
				if(red == 255 && green == 109 && blue == 0) handler.addObject(new Tree(xx * 32, yy * 32, ID.Tree, render));
				if(red == 165 && green == 255 && blue == 127) handler.addObject(new FinalBoss(xx * 32, yy * 32, ID.Boss, render, handler));
			}
		}
	}
	
	public Game() {
		new Screen(200, 180, 3, "AW2", this);
		menu = new Menu();

		handler = new Handler();
		camera = new Camera(0, 0);	
		loader = new Loader();
		
		levelOne = loader.load("res/level1.png");
		textures = loader.load("res/tex.png");
		
		render = new Render(textures);
			
		floorCastleLevel = render.getTextures(1, 1, 16, 16);
		
		this.addKeyListener(new Controll(handler));
		this.addMouseListener(new MouseInput(handler, camera, this, render));
		loadLevel(levelOne);	
		start();
	}
	
	public static void main(String[] argvs) {
		new Game();
	}
}