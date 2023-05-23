package logic.item;

import javafx.scene.image.Image;
import logic.game.GameLogic;
import sharedObject.RenderableHolder;
import util.Buyable;
import util.PotionGrade;
import util.Sellable;

public class Potion extends Item implements Sellable, Buyable {

	private PotionGrade grade;
	private int price;

	public Potion(String itemName, int amount) {
		super(itemName, amount);
		setGradeAndType();
		setPrice();
	}

	public void buy() {
		GameLogic.getPlayer().setPlayerMoney(GameLogic.getPlayer().getPlayerMoney() - getPrice());
		System.out.println(GameLogic.getPlayer().getPlayerMoney());
	}

	public void sell(int number) {
		setAmount(getAmount() - number);
		for (int i = 0 ; i < number ; i++) {
			GameLogic.getPlayer().setPlayerMoney(GameLogic.getPlayer().getPlayerMoney() + getPrice() * 0.8);
			System.out.println(GameLogic.getPlayer().getPlayerMoney());
		}
	}

	public void consume(int number) {
		for (int i =0 ; i < number ; i++) {
			switch (getGrade()) {
			case LOW:
				GameLogic.getPlayer().setPlayerStamina(GameLogic.getPlayer().getPlayerStamina() + 10);
				break;
			case MID:
				GameLogic.getPlayer().setPlayerStamina(GameLogic.getPlayer().getPlayerStamina() + 20);
				break;
			case HIGH:
				GameLogic.getPlayer().setPlayerStamina(GameLogic.getPlayer().getPlayerStamina() + 30);
				break;
			}
		}
		setAmount(getAmount() - number);

	}

	public PotionGrade getGrade() {
		return grade;
	}

	public void setGradeAndType() {
		
		if (getItemName().equals("staminaPotionI")) {
			this.grade = PotionGrade.LOW;
		}
		else if (getItemName().equals("staminaPotionII")) {
			this.grade = PotionGrade.MID;
		}
		else if (getItemName().equals("staminaPotionIII")) {
			this.grade = PotionGrade.HIGH;
		}
		else {
			System.out.println("Error");
		}
	}


	public Image getImage() {

		switch (getGrade()) {

		case LOW:
			return RenderableHolder.staminaPotionI;
		case MID:
			return RenderableHolder.staminaPotionII;
		default:
			return RenderableHolder.staminaPotionIII;
		}

	}

	public int getPrice() {
		return price;
	}

	public void setPrice() {
		switch (getGrade()) {
		case LOW:
			this.price = 10;
			break;
		case MID:
			this.price = 20;
			break;
		case HIGH:
			this.price = 30;
			break;
			}
	}

}
