package gameController;

import java.io.BufferedInputStream;
import java.io.InputStream;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class SoundThread extends Thread{

	private String route;
	
    public SoundThread(String str, String route){
        super(str);
        this.route = route;
    }
    
    @Override
    public void run(){
    	
        try{
            InputStream bufferedIn = new BufferedInputStream(getClass().getResourceAsStream(route));
            AudioInputStream audioStream =  AudioSystem.getAudioInputStream(bufferedIn);

            Clip sound = AudioSystem.getClip();
            sound.open(audioStream);            
            sound.start();
            Thread.sleep(100);
            while (sound.isRunning()){
                Thread.sleep(500);
            }
            sound.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
	
}