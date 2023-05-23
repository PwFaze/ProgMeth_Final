package drawing;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import sharedObject.RenderableHolder;


public class ButtonShop extends Button {
	private ImageView img;
	private String itemName;
	private AreSurePane areYouSurePane;

	public ButtonShop(String itemName, ImageView img) {
		this.intializeButton(itemName, img);
		AreSurePane areyouSurePane = new AreSurePane(itemName);
		this.areYouSurePane = areyouSurePane;
	}

	public void intializeButton(String itemName, ImageView img) {
		this.setPrefSize(125, 150);
		img.setFitHeight(110);
		img.setFitWidth(85);
		Image imageBackground = RenderableHolder.buttonShopBackground;
		this.setGraphic(img);

		BackgroundImage background = new BackgroundImage(imageBackground, BackgroundRepeat.NO_REPEAT,
				BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
				new BackgroundSize(125, 150, false, false, true, false));

		this.setBackground(new Background(background));
		this.setOnMouseClicked(new EventHandler<Event>() {

			public void handle(Event arg0) {

				ShopPane.getInstance().getChildren().addAll(areYouSurePane);
			}
		});

	}

	public ImageView getImg() {
		return img;
	}

	public void setImg(ImageView img) {
		this.img = img;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

}
