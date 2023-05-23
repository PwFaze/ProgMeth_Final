package logic.item;

import logic.game.GameLogic;
import util.MineralType;
import util.Sellable;

public class Ore extends Item implements Sellable {

	private MineralType type;
	private int price;

	public Ore(String name, int amount) {
		super(name, amount);
		setType(name);
		setImg(name);
		setPrice(getType());

	}

	@Override
	public void sell(int number) {
		setAmount(getAmount() - number);
		for (int i = 0 ; i < number ; i++) {
			GameLogic.getPlayer().setPlayerMoney(GameLogic.getPlayer().getPlayerMoney() + getPrice());
			System.out.println(GameLogic.getPlayer().getPlayerMoney());

		}
	}
	

	public int getPrice() {
		return price;
	}

	public void setPrice(MineralType type) {
		switch (type) {
		case STONE:
			this.price = 10;
			break;
		case IRON:
			this.price = 200;
			break;
		case GOLD:
			this.price = 400;
			break;
		case RUBY:
			this.price = 800;
			break;
		case DIAMOND:
			this.price = 1500;
			break;
		default:
			throw new IllegalArgumentException("Unexpected value: " + type);
		}
	}

	public MineralType getType() {
		return type;
	}
	
	public void consume() {
		System.out.println("cannot use");
	}

	public void setType(String name) {
		if (getItemName().equals("DIRT")) {
			this.type = MineralType.DIRT;
		} else if (getItemName().equals("STONE")) {
			this.type = MineralType.STONE;
		} else if (getItemName().equals("GOLD")) {
			this.type = MineralType.GOLD;
		} else if (getItemName().equals("RUBY")) {
			this.type = MineralType.RUBY;
		} else if (getItemName().equals("IRON")) {
			this.type = MineralType.IRON;
		} else if (getItemName().equals("DIAMOND")) {
			this.type = MineralType.DIAMOND;
		}
		setImg(name);
	}
	
	public String toString() {
		return getItemName() + " " + getAmount();
	}

}
