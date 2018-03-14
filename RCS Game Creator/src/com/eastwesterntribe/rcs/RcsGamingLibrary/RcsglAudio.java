package com.eastwesterntribe.rcs.RcsGamingLibrary;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;


public class RcsglAudio {
	
	private String	name;
	private Clip	clip;
	private File	f;
	
	public RcsglAudio(String name, String filename) {
		this.name = name;
		f = new File(filename);
		
		if(f.exists()) {
			try {
				clip = AudioSystem.getClip();
				AudioInputStream sound = AudioSystem.getAudioInputStream(f);
				clip.open(sound);
			} catch (LineUnavailableException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (UnsupportedAudioFileException e) {
				e.printStackTrace();
			}
		}
	}
	
	public boolean isClip(String s){
		return s.equalsIgnoreCase(name);
	}
	
	
	public String getName(){
		return name;
	}
	
	public void playClipFromStart(){
		clip.setFramePosition(0);
		clip.start();
	}
	
	public void unpauseClip(){
		clip.start();
	}
	
	public void pauseClip(){
		clip.stop();
	}
	
	public void playClipAtTime(double seconds){
		long num = (long) (seconds * 1000000L);
		if(num < clip.getMicrosecondLength()) {
			clip.setMicrosecondPosition(num);
			clip.start();
		}
	}
	
	public void playClipAtTime(long microseconds){
		if(microseconds < clip.getMicrosecondLength()) {
			clip.setMicrosecondPosition(microseconds);
			clip.start();
		}
	}
	
	public double getClipLengthSeconds(){
		return clip.getMicrosecondLength() / 1000000;
	}
	
	public long getClipLengthMicroseconds(){
		return clip.getMicrosecondLength();
	}
	
	public double getClipPositionSeconds(){
		return clip.getMicrosecondPosition() / 1000000;
	}
	
	public long getClipPositionMicroseconds(){
		return clip.getMicrosecondPosition();
	}
	
	public void closeClip(){
		clip.close();
	}
	
	public void loopClip(double startSeconds, double endSeconds, int numTimes){
		long ms = (long) (startSeconds * 1000000L);
		clip.setMicrosecondPosition(ms);
		int s1 = clip.getFramePosition();
		ms = (long) (endSeconds * 1000000L);
		clip.setMicrosecondPosition(ms);
		int s2 = clip.getFramePosition();
		clip.setFramePosition(s1);
		clip.setLoopPoints(s1, s2);
		clip.loop(numTimes);
	}
	
	public void loopClipContinuously(double startSeconds, double endSeconds){
		long ms = (long) (startSeconds * 1000000L);
		clip.setMicrosecondPosition(ms);
		int s1 = clip.getFramePosition();
		ms = (long) (endSeconds * 1000000L);
		clip.setMicrosecondPosition(ms);
		int s2 = clip.getFramePosition();
		clip.setFramePosition(s1);
		clip.setLoopPoints(s1, s2);
		clip.loop(Clip.LOOP_CONTINUOUSLY);
	}
	
	public boolean isPlaying(){
		return clip.isRunning();
	}
	
	public FloatControl getGainControl(){
		return (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
	}
	
}
