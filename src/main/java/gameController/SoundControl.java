package gameController;

import java.io.BufferedInputStream;
import java.io.InputStream;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class SoundControl {

	public static Clip backgroundSound;
	public static boolean isPlay;

	public static void playBackgroundSound(String nameSound){
		String route = "/resources/sounds/" + nameSound + ".wav";
		InputStream path = SoundControl.class.getResourceAsStream(route);
		try{
			InputStream bufferedIn = new BufferedInputStream(path);
			AudioInputStream audioStream =  AudioSystem.getAudioInputStream(bufferedIn);
			backgroundSound = AudioSystem.getClip();
			backgroundSound.open(audioStream);
			backgroundSound.loop(Clip.LOOP_CONTINUOUSLY);
			backgroundSound.start();
			isPlay=true;
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	public static void stopBackgroundSound(){
		backgroundSound.stop();
		backgroundSound.close();
		isPlay = false;
	}
	
	public static void playSound(String nameSound) {
		String route = "/resources/sounds/" + nameSound + ".wav";
        SoundThread t = new SoundThread("Thread",route);
        t.start();
    }	
}
