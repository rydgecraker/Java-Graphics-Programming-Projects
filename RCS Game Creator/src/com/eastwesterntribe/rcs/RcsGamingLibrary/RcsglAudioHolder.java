package com.eastwesterntribe.rcs.RcsGamingLibrary;

import java.text.DecimalFormat;
import java.util.ArrayList;


public class RcsglAudioHolder {
	
	private ArrayList<RcsglAudio>	clips;
	/**
	 * a number between -80 and 6
	 */
	private float					volume;
	
	public RcsglAudioHolder(float initialVolume) {
		volume = initialVolume;
		clips = new ArrayList<RcsglAudio>();
	}
	
	public void addAudio(RcsglAudio clip){
		clips.add(clip);
		volumeChange();
	}
	
	private int findClip(String s){
		int pos = -1;
		
		for (int i = 0; i < clips.size(); i++) {
			if(clips.get(i).isClip(s)) {
				pos = i;
			}
		}
		
		return pos;
	}
	
	public void setVolume(float f){
		volume = f;
		volumeChange();
	}
	
	public void addVolume(float f){
		volume += f;
		volumeChange();
	}
	
	public float getVolume(){
		return volume;
	}
	
	private void volumeChange(){
		for (int i = 0; i < clips.size(); i++) {
			clips.get(i).getGainControl().setValue(volume);
		}
		
	}
	
	public void playClipFromStart(String name){
		clips.get(findClip(name)).playClipFromStart();
		volumeChange();
	}
	
	public void unpauseClip(String name){
		clips.get(findClip(name)).unpauseClip();
		volumeChange();
	}
	
	public void playClipFromPosition(String name, double seconds){
		clips.get(findClip(name)).playClipAtTime(seconds);
		volumeChange();
	}
	
	public void playClipFromPosition(String name, long milliseconds){
		clips.get(findClip(name)).playClipAtTime(milliseconds);
		volumeChange();
	}
	
	public void pauseClip(String name){
		clips.get(findClip(name)).pauseClip();
		volumeChange();
	}
	
	public void stopClip(String name){
		clips.get(findClip(name)).closeClip();
		volumeChange();
	}
	
	public void stopAllClips(){
		for (int i = 0; i < clips.size(); i++) {
			clips.get(i).closeClip();
		}
	}
	
	public void pauseAllClips(){
		for (int i = 0; i < clips.size(); i++) {
			clips.get(i).pauseClip();
		}
		volumeChange();
	}
	
	public void loopClip(String name, double startTimeSeconds, double endTimeSeconds, int numberOfLoops){
		clips.get(findClip(name)).loopClip(startTimeSeconds, endTimeSeconds, numberOfLoops);
		volumeChange();
	}
	
	public void loopClipContinously(String name, double startTimeSeconds, double endTimeSeconds){
		clips.get(findClip(name)).loopClipContinuously(startTimeSeconds, endTimeSeconds);
		volumeChange();
	}
	
	public boolean isPlaying(String name){
		return clips.get(findClip(name)).isPlaying();
	}
	
	public double getClipPositon(String name){
		return clips.get(findClip(name)).getClipPositionSeconds();
	}
	
	public double getClipLength(String name){
		return clips.get(findClip(name)).getClipLengthSeconds();
	}
	
	public int getVolumePercentage(DecimalFormat roundToNearestInt){
		double v = volume + 79.0;
		v = v * (20.0 / 17.0);
		return Integer.parseInt(roundToNearestInt.format(v));
	}
	
	
}
