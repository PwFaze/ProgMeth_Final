package Entity.Environment;

import Entity.Base.Block;
import javafx.scene.canvas.GraphicsContext;
import sharedObject.RenderableHolder;
import util.Interactable;
import util.MineralType;

public class OreBlock extends Block {
	
	private MineralType mineralType;
	
	public OreBlock(MineralType mineralType) {
		setMineralType(mineralType);
	}

	@Override
	public int getZ() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void draw(GraphicsContext gc) {
		// TODO Auto-generated method stub
		if (isVisible() == true) {
			if (isDestroyed() == false) {
				switch (getMineralType()) {
				case STONE :
					gc.drawImage(RenderableHolder.stone, getX(), getY());
					break;
				case IRON:	
					gc.drawImage(RenderableHolder.iron, getX(), getY());
					break;
				case GOLD:
					gc.drawImage(RenderableHolder.gold, getX(), getY());
					break;
				case RUBY:
					gc.drawImage(RenderableHolder.ruby, getX(), getY());
					break;
				case DIAMOND :	
					gc.drawImage(RenderableHolder.diamond, getX(), getY());
					break;
				default :
					System.out.println("Ore error");
					break;
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


	public MineralType getMineralType() {
		return mineralType;
	}

	public void setMineralType(MineralType mineralType) {
		this.mineralType = mineralType;
	}


	@Override
	public void interact(Interactable interacted) {
		// TODO Auto-generated method stub
		// do nothing since the player do its job
		
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return getMineralType().toString();
	}
	

}
