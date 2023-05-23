package logic.tools;

import sharedObject.RenderableHolder;
import util.MineralType;

public class Pickaxe extends Tools {

	public Pickaxe(String itemName, MineralType ore, int staminaUsed) {
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
			this.img = RenderableHolder.stonePickaxe;
			break;
		case IRON:
			this.img = RenderableHolder.ironPickaxe;
			break;
		case GOLD:
			this.img = RenderableHolder.goldPickaxe;
			break;
		case RUBY:
			this.img = RenderableHolder.rubyPickaxe;
			break;
		case DIAMOND:
			this.img = RenderableHolder.diamondPickaxe;
			break;
		default :
			break;
		}
	}

	

}
