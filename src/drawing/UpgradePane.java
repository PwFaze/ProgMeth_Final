package drawing;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import logic.game.GameLogic;
import logic.item.Item;
import logic.item.Ore;
import logic.tools.Tools;
import util.MineralType;

public class UpgradePane extends VBox {
	
	private VBox itemContainer;
	private HBox buttonsContainer;
	private HBox oreUsesContainer;
	private ImageView oreImage;
	private ImageView toolsImage;
	private Button upgradeButton;
	private Button returnButton;
	
	private Ore neededOres;
	private static final int NEEDEDAMOUNT = 4;
	
	public UpgradePane(Tools tool,ItemBox itemBox) {
		setMaxSize(180, 250);
		setSpacing(20);
		setPadding(new Insets(20));
		setAlignment(Pos.CENTER);
		setStyle("-fx-border-color: brown; -fx-border-width: 5px;");
		setBackground(new Background(new BackgroundFill(Color.valueOf("F79540"), null, null)));

		itemContainer = new VBox();
		
		returnButton = new Button("return");
		
		toolsImage = new ImageView(tool.getImg());
		toolsImage.setFitHeight(100);
		toolsImage.setFitWidth(100);
		
		upgradeButton = new Button("upgrade");
		itemContainer.getChildren().addAll(toolsImage);
		itemContainer.setMaxSize(100, 100);
		
		buttonsContainer = new HBox();
		buttonsContainer.setSpacing(10);
		buttonsContainer.getChildren().addAll(upgradeButton, returnButton);
		
		oreUsesContainer = new HBox();
		oreUsesContainer.setSpacing(10);
		oreUsesContainer.setAlignment(Pos.CENTER);
		oreImage = new ImageView();
		neededOres = new Ore(MineralType.getUpgrade(tool.getOre()).toString(), NEEDEDAMOUNT);
		oreImage.setImage(neededOres.getImg());
		oreUsesContainer.getChildren().addAll(oreImage, new Text("x " + NEEDEDAMOUNT));
		getChildren().addAll(itemContainer, buttonsContainer, oreUsesContainer);
		
		// Add action
		
		returnButton.setOnAction((e) -> {
			InventoryPane.getInstance().getChildren().remove(this);
		});
		
		upgradeButton.setOnAction((e) -> {
			if (isUpgradeable(itemBox) == true && !tool.getOre().equals(MineralType.DIAMOND)) {
				tool.upgrade();
				toolsImage.setImage(tool.getImg());
				itemBox.setBackground(new Background(new BackgroundImage(tool.getImg(), BackgroundRepeat.NO_REPEAT,
						BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
						new BackgroundSize(500, 400, false, false, true, false))));
				neededOres.setItemName(MineralType.getUpgrade(tool.getOre()).toString());
				oreImage.setImage(neededOres.getImg());
				System.out.println("Upgrade complete");
				InventoryPane.getInstance().checkAndUpdateInventory();
				InventoryPane.getInstance().updateGrid();
				for (Item it : GameLogic.getPlayer().getInventory()) {
					System.out.println(it);
				}
			}
			else if (tool.getOre().equals(MineralType.DIAMOND)) {
				System.out.println("Cannot Upgrade anymore");
			}
			else {
				System.out.println("Insufficient Ore");
			}
		});
		
	}


	public Button getCloseButton() {
		return returnButton;
	}
	
	public boolean isUpgradeable(ItemBox itemBox) {
		Item[] items = GameLogic.getPlayer().getInventory();
		for (Item it: items) {
			if (it instanceof Ore) {
				if (((Ore) it).getType().equals(neededOres.getType()) && ((Ore) it).getAmount() >= NEEDEDAMOUNT) {
					it.setAmount(it.getAmount() - NEEDEDAMOUNT);
					InventoryPane.getInstance().checkAndUpdateInventory();
					InventoryPane.getInstance().updateGrid();
					return true;
				}
			}
		}
		return false;
	}
	

}
