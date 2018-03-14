package quest;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class IntroMusic implements Runnable{

	public Clip clipbg;
	
	public IntroMusic(Clip clipbg){
		this.clipbg = clipbg;
		Thread th = new Thread(this);
		th.start();
	}

	public void run() {
		File yourFile = new File("Music/intro(Rock On).mid");
	    AudioInputStream stream;
	    AudioFormat format;
	    DataLine.Info info;

	    try {
			stream = AudioSystem.getAudioInputStream(yourFile);
			format = stream.getFormat();
		    info = new DataLine.Info(Clip.class, format);
		    clipbg = (Clip) AudioSystem.getLine(info);
		    clipbg.open(stream);
		    FloatControl gainControl = (FloatControl) clipbg.getControl(FloatControl.Type.MASTER_GAIN);
		    gainControl.setValue(-10.0f); // Reduce volume by 10 decibels.
		    clipbg.loop(Clip.LOOP_CONTINUOUSLY);
		} catch (UnsupportedAudioFileException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (LineUnavailableException e) {
			e.printStackTrace();
		}
	}
	
}
