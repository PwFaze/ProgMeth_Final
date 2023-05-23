package logic.tools;

import sharedObject.RenderableHolder;
import util.MineralType;

public class Shovel extends Tools {

	public Shovel(String itemName, MineralType ore, int staminaUsed) {
		super(itemName, ore, staminaUsed);
		setImg(ore);
	}
	

	@Override
	public void upgrade() {
		// TODO Auto-generated method stub
		setOre(MineralType.getUpgrade(getOre()));
		setImg(getOre());
		setEfficiency(getOre());
		
	}

	
	
	public void setImg(MineralType ore) {
		switch (ore) {
		case STONE:
			this.img = RenderableHolder.stoneShovel;
			break;
		case IRON:
			this.img = RenderableHolder.ironShovel;
			break;
		case GOLD:
			this.img = RenderableHolder.goldShovel;
			break;
		case RUBY:
			this.img = RenderableHolder.rubyShovel;
			break;
		case DIAMOND:
			this.img = RenderableHolder.diamondShovel;
			break;
		default :
			break;
		}
	}

}
