package Entity.Environment;

import Entity.Base.Block;
import javafx.scene.canvas.GraphicsContext;
import sharedObject.RenderableHolder;
import util.Interactable;

public class DirtBlock extends Block{
	
	private boolean hasGrass;
	
	public DirtBlock(boolean hasGrass) {
	
		setHasGrass(hasGrass);
	}

	@Override
	public void draw(GraphicsContext gc) {
		// TODO Auto-generated method stub
		if (isVisible() == true) {
			if (isDestroyed() == false) {
				if (isHasGrass() == true) {
					gc.drawImage(RenderableHolder.grass, getX(), getY());
				}
				else {
					gc.drawImage(RenderableHolder.dirt, getX(), getY());
				}
			}
			else {
				gc.drawImage(RenderableHolder.digged, getX(), getY());
	
			}
		}
		else {
			super.draw(gc);
		}
		
		
	}


	

	@Override
	public void interact(Interactable interacted) {
		// TODO Auto-generated method stub
		// do nothing since the player do its job
	}

	public boolean isHasGrass() {
		return hasGrass;
	}

	public void setHasGrass(boolean hasGrass) {
		this.hasGrass = hasGrass;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "DIRT";
	}

}
