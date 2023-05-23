package drawing;

import javafx.scene.layout.*;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import sharedObject.RenderableHolder;

public class ShopPane extends StackPane {

	private static final ShopPane shopPane = new ShopPane();
	private Button back;

	public ShopPane() {
		
		GridPane shop = new GridPane();
		Button btn1 = new ButtonShop("staminaPotionI", new ImageView(RenderableHolder.staminaPotionI));
		Button btn2 = new ButtonShop("staminaPotionII", new ImageView(RenderableHolder.staminaPotionII));
		Button btn3 = new ButtonShop("staminaPotionIII", new ImageView(RenderableHolder.staminaPotionIII));
		// Go back
		back = new Button();
		back.setPrefSize(50, 50);
		Image imageButtonBack = RenderableHolder.bactToHomeButton;
		BackgroundImage background = new BackgroundImage(imageButtonBack, BackgroundRepeat.NO_REPEAT,
				BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
				new BackgroundSize(50, 50, false, false, true, false));
		back.setBackground(new Background(background));
		
		shop.setPadding(new Insets(20, 20, 20, 20));
		shop.setAlignment(Pos.CENTER);
		shop.setVgap(100);
		shop.setHgap(100);
		shop.getChildren().addAll(btn1, btn2, btn3);
		shop.setConstraints(btn1, 0, 0);
		shop.setConstraints(btn2, 1, 0);
		shop.setConstraints(btn3, 2, 0);
		this.setStyle("-fx-background-image: url(Images/backgroundShop.png) ; -fx-background-size: 800px 600px;");

		this.setMaxSize(650, 450);

		this.getChildren().add(shop);
		this.setAlignment(Pos.CENTER);
		setAlignment(back, Pos.TOP_RIGHT);
		this.setPadding(new Insets(15,15,50,50));
		this.getChildren().addAll(back);

	}

	public static ShopPane getInstance() {
		return shopPane;
	}
	public Button getBackButton() {
		return back;
	}
}
