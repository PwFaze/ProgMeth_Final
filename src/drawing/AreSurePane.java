package drawing;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import logic.item.Potion;

public class AreSurePane extends StackPane {
	private String itemName;

	public AreSurePane(String itemName) {
		VBox allPaneInStackPaneShop = new VBox();
		// Text
		Text titleStackPaneText = new Text("Are you to buy " + itemName + " ?");
		titleStackPaneText.setFill(Color.BLACK);
		titleStackPaneText.setFont(new Font(20));

		// ButtonBox
		HBox buttonBox = new HBox();
		buttonBox.setPrefSize(450, 200);
		buttonBox.setSpacing(100);
		buttonBox.setAlignment(Pos.CENTER);

		// OkButton
		Button okButton = new Button("OK");
		okButton.setPrefSize(100, 50);
		okButton.setOnMouseClicked(new EventHandler<Event>() {
			public void handle(Event arg0) {
				okButtonClick(itemName);
			}
		});

		// CancelButton
		Button cancelButton = new Button("CANCEL");
		cancelButton.setPrefSize(100, 50);
		cancelButton.setOnMouseClicked(new EventHandler<Event>() {
			public void handle(Event arg0) {
				cancelButtonClick();
			}
		});
		//

		buttonBox.getChildren().addAll(okButton, cancelButton);

		allPaneInStackPaneShop.getChildren().addAll(titleStackPaneText, buttonBox);
		allPaneInStackPaneShop.setAlignment(Pos.CENTER);

		this.setBackground(new Background(new BackgroundFill(Color.WHITESMOKE, null, getInsets())));
		this.setMaxSize(450, 450);
		this.getChildren().addAll(allPaneInStackPaneShop);

	}

	public void okButtonClick(String itemName) {
		Potion boughtPotion = new Potion(itemName, 1);
		boughtPotion.buy();
		InventoryPane.getInstance().addItem(boughtPotion);
		ShopPane.getInstance().getChildren().remove(this);
	}

	public void cancelButtonClick() {
		ShopPane.getInstance().getChildren().remove(this);
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

}
