package logic.item;

import javafx.scene.image.Image;
import sharedObject.RenderableHolder;

public abstract class Item  {

	protected String itemName;
	protected int amount;
	protected Image img;

	public Item(String itemName, int amount) {

		setItemName(itemName);
		setAmount(amount);
	}

	public Image getImg() {
		return img;
	}
	
	
	public abstract void sell(int number);

	public void setImg(String ItemName) {
		if (ItemName.equals("hpPotionI")) {
			this.img = RenderableHolder.hpPotionI;
		} else if (itemName.equals("hpPotionII")) {
			this.img = RenderableHolder.hpPotionII;
		} else if (itemName.equals("hpPotionIII")) {
			this.img = RenderableHolder.hpPotionIII;
		} else if (itemName.equals("staminaPotionI")) {
			this.img = RenderableHolder.staminaPotionI;
		} else if (itemName.equals("staminaPotionII")) {
			this.img = RenderableHolder.staminaPotionII;
		} else if (itemName.equals("staminaPotionIII")) {
			this.img = RenderableHolder.staminaPotionIII;
		} else if (itemName.equals("STONE")) {
			this.img = RenderableHolder.stone;
		} else if (itemName.equals("GOLD")) {
			this.img = RenderableHolder.goldBar;
		} else if (itemName.equals("RUBY")) {
			this.img = RenderableHolder.rubyBar;
		} else if (itemName.equals("IRON")) {
			this.img = RenderableHolder.ironBar;
		} else if (itemName.equals("DIAMOND")) {
			this.img = RenderableHolder.diamondBar;
		}
		else {
			this.img = RenderableHolder.diamondBar;
		}

	}

	public String getItemName() {
		return itemName;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
		setImg(itemName);
	}

}
