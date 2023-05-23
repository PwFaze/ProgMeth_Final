package sharedObject;

import javafx.scene.media.AudioClip;


public class AudioManager {
	
	public static AudioClip gameMusic = new AudioClip(ClassLoader.getSystemResource("sound/gamemusic-6082.mp3").toString());;
	public static AudioClip ERROR = new AudioClip(ClassLoader.getSystemResource("sound/sound.mp3").toString());;
	
	static {
		gameMusic.setVolume(0.2);
	}
	

}
