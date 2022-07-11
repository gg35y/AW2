package com.java.game.main.sound;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class Sound {
	private String track;
	private Clip clip = null;
	private FloatControl volumeC = null;
	private double wt;  
	
	public void audio(){
		File file = new File(this.track);
		AudioInputStream tr = null;
		try{
			tr = AudioSystem.getAudioInputStream(file);
		}catch (UnsupportedAudioFileException e) {
			e.printStackTrace();
		}catch (IOException e) {
			e.printStackTrace();
		}
		
		try{
			clip = AudioSystem.getClip();		
			clip.open(tr);
			volumeC = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
		
			clip.setFramePosition(0);
			clip.start();
		}catch(LineUnavailableException e){
			e.printStackTrace();
	
		}catch(IOException e){
			e.printStackTrace();
		}
	} 
	
	public void setVolume(){
		if(wt<0) wt = 0;
		if(wt>1) wt = 1;
		float min = volumeC.getMinimum();
		float max = volumeC.getMaximum();
		volumeC.setValue((max-min)*(float)wt+min);
	}

	public Sound(String track, double wt){
		this.track = track;
		this.wt = wt;
	} 
}