package drawing;

import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import logic.item.Item;
import sharedObject.RenderableHolder;

public class ItemBox extends Button {
	
	private Item item;
	
	public ItemBox(Item item) {
		this.item = item;
		setPrefSize(80, 80);
		BackgroundImage background = new BackgroundImage(RenderableHolder.buttonShopBackground, BackgroundRepeat.NO_REPEAT,
				BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
				new BackgroundSize(125, 150, false, false, true, false));
		setBackground(new Background(background));
		updateText(getItem());
	}
	
	public void updateText(Item item) {
		setText(item.getItemName() + " (" + item.getAmount() + ")");
		setFont(new Font(12));
		setStyle("-fx-font-weight: bold;");
		setTextFill(Color.valueOf("FF0303"));
	}
	
	public Item getItem() {
		return item;
	}
	
}
