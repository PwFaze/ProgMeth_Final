package Exception;

import sharedObject.AudioManager;

public class InteractFailedException extends Exception {
	private String message;
	
	public InteractFailedException(String message) {
		setMessage(message);
	}
	
	public void setMessage(String message) {
		this.message = message;
	}
	
	public void playSound() {
		AudioManager.ERROR.play();
	}
	public String getMessage() {
		return message;
	}

}
