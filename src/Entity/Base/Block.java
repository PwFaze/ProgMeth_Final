package Entity.Base;

import javafx.scene.canvas.GraphicsContext;
import logic.game.GameLogic;
import util.Interactable;
import Entity.Environment.*;

public abstract class Block extends Entity implements Interactable {
	
	protected boolean hasPlatform;

	public Block() {
		
		setZ(-1);
		setHp(100);
		// check whether the player can see the block
		setVisible(true);
	}
	
	public abstract String getName();
	
	public void draw(GraphicsContext gc) {
		gc.fillRect(getX(), getY(), Map.getBlocksize(), Map.getBlocksize());
	}
	public boolean hasPlatform() {
		return hasPlatform;
	}
	public void setHasPlatform(boolean hasPlatform) {
		if (isDestroyed() == false) {
			hasPlatform = false;
		}
		this.hasPlatform = hasPlatform;
	}
	public void update() {
		int playerX = GameLogic.getPlayer().getX() / Map.getBlocksize();
	    int playerY = GameLogic.getPlayer().getY() / Map.getBlocksize();

	    int visibilityRange = 3; // Adjust the visibility range as needed
	    int blockX = getX() / Map.getBlocksize();
	    int blockY = getY() / Map.getBlocksize();
	    if (blockY <= Map.getBlockdepth() + 2) {
	    	
	    	setVisible(true);
	    }
	    else {
		    if (blockX < playerX - visibilityRange || blockX > playerX + visibilityRange ||
		        blockY < playerY - visibilityRange || blockY > playerY + visibilityRange) {
		        setVisible(false);
		    } else {
		        setVisible(true);
		    }
	    }
		
	}

}
