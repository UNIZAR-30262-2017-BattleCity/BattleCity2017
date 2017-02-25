package controller;

import java.io.BufferedInputStream;
import java.io.InputStream;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class SoundControl {

	private Clip backgroundSound;    

	public void playBackgroundSound(int nameSound){
		String route = "sounds/" + nameSound + ".wav";
		InputStream path = getClass().getResourceAsStream(route);
		try{
			InputStream bufferedIn = new BufferedInputStream(path);
			AudioInputStream audioStream =  AudioSystem.getAudioInputStream(bufferedIn);
			backgroundSound = AudioSystem.getClip();
			backgroundSound.open(audioStream);
			backgroundSound.loop(Clip.LOOP_CONTINUOUSLY);
			backgroundSound.start();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	public void stopBackgroundSound(){
		backgroundSound.stop();
		backgroundSound.close();
	}
	
	public void playSound(int nameSound) {
		String route = "sounds/" + nameSound + ".wav";
        SoundThread t = new SoundThread("Thread",route);
        t.start();
    }

}
