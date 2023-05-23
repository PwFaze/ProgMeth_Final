package logic.tools;


import logic.item.Item;
import util.MineralType;

public abstract class Tools extends Item  {
	
	private MineralType ore;
	private int staminaUsed;
	private int efficiency;

	public Tools(String itemName, MineralType ore, int staminaUsed) {

		super(itemName, 1);
		
		setOre(ore);
		setStaminaUsed(staminaUsed);
		setEfficiency(ore);

	}
	public void sell(int num) {
		System.out.println("Cannot sell tools");
	}

	public abstract void upgrade();

	public String toString() {
		return this.getClass().getSimpleName() + " Made of: " + this.getOre().toString() + 
				 " StaminaUsed: " + this.getStaminaUsed();
	}
	

	public int getStaminaUsed() {
		return staminaUsed;
	}

	public void setStaminaUsed(int staminaUsed) {
		this.staminaUsed = Math.max(1, staminaUsed);
	}

	public MineralType getOre() {
		return ore;
	}

	public void setOre(MineralType ore) {
		this.ore = ore;
	}

	public int getEfficiency() {
		return efficiency;
	}

	public void setEfficiency(MineralType ore) {
		switch(ore) {
		case STONE:
			this.efficiency = 100;
			break;
		case IRON:
			this.efficiency = 20;
			break;
		case GOLD:
			this.efficiency = 30;
			break;
		case RUBY:
			this.efficiency = 40;
			break;
		case DIAMOND :
			this.efficiency = 50;
			break;
		default:
			System.out.println("Invalid type of ore");
		}
	}


}
