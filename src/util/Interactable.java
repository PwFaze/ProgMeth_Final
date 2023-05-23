package util;

import Exception.InteractFailedException;

public interface Interactable {
	
	void interact(Interactable interacted) throws InteractFailedException;

}
