package drawing;

import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import logic.game.GameLogic;
import logic.item.Item;
import logic.item.Ore;
import logic.item.Potion;
import logic.tools.Pickaxe;
import logic.tools.Shovel;
import logic.tools.Tools;
import sharedObject.RenderableHolder;


public class InventoryPane extends StackPane {
	private static Item[] items = GameLogic.getPlayer().getInventory();
	private static GridPane gridPane;
	private Button closeButton;
	private static InventoryPane instance = new InventoryPane();

	public InventoryPane() {
        setStyle("-fx-border-color: brown; -fx-border-width: 5px;");

		closeButton = new Button();
		closeButton.setPrefSize(50, 50);
		Image imageButtonBack = RenderableHolder.bactToHomeButton;
		BackgroundImage buttonBackground = new BackgroundImage(imageButtonBack, BackgroundRepeat.NO_REPEAT,
				BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
				new BackgroundSize(50, 50, false, false, true, false));
		closeButton.setBackground(new Background(buttonBackground));
		
		setPrefSize(500, 400);
		setMaxSize(500, 400);
		if (items == null) {
			items = new Item[9];
		}
		if (gridPane == null) {
			gridPane = new GridPane();
			gridPane.setPadding(new Insets(10));
			gridPane.setHgap(10);
			gridPane.setVgap(10);
			gridPane.setPrefSize(500, 400);
	        gridPane.add(closeButton, 0, 0);
	        getChildren().addAll(gridPane);
	        initialize();
		}
		setBackground(new Background(new BackgroundFill(Color.valueOf("FFE194"), null, null)));
	}
	

	
	public void initialize() {
		for (int i = 1; i < items.length; i++) {
			Item item = items[i-1] ;
			if (item != null) {
				ItemBox itemBox = createButton(item);
				itemBox.setBackground(new Background(new BackgroundImage(item.getImg(), BackgroundRepeat.NO_REPEAT,
					BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
					new BackgroundSize(500, 400, false, false, true, false))));
				gridPane.add(itemBox, i % 4 + 1, i / 4);
			}
		}
		
	}

	public void addItem(Item item) {
		boolean itemFound = false;

		for (int i = 0; i < items.length; i++) {
			Item currentItem = items[i];

			if (currentItem != null && currentItem.getItemName().equals(item.getItemName())) {
				currentItem.setAmount(currentItem.getAmount() + item.getAmount());
				itemFound = true;
				updateGrid();
				System.out.println(item.getItemName() + " stacked.");
				break;
			}
		}

		if (!itemFound) {
			if (isInventoryFull()) {
				System.out.println("Inventory is full. Cannot add more items.");
			} else {
				for (int i = 0; i < items.length; i++) {
					if (items[i] == null) {
						items[i] = item;
						ItemBox itemBox = createButton(item);
						itemBox.setBackground(new Background(new BackgroundImage(item.getImg(), BackgroundRepeat.NO_REPEAT,
								BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
								new BackgroundSize(500, 400, false, false, true, false))));
						gridPane.add(itemBox, i % 3 + 2, i / 3);
						System.out.println(item.getItemName() + " added to the inventory.");
						break;
					}
				}
			}
		}
	}

	protected boolean isInventoryFull() {
		for (Item item : items) {
			if (item == null) {
				return false;
			}
		}
		return true;
	}

	protected void removeItem(Item item, ItemBox button) {
		int index = -1;

		for (int i = 0; i < items.length; i++) {
			if (items[i] == item) {
				index = i;
				break;
			}
		}

		if (index != -1) {
			items[index] = null;
			for (Node n : gridPane.getChildren()) {
				if (n instanceof ItemBox) {
			        ItemBox itemBox = (ItemBox) n;
			        if (itemBox.getItem().getItemName().equals(item.getItemName())) {
						gridPane.getChildren().remove(n);
						System.out.println(item.getItemName() + " removed from the inventory.");
						break;
			        }
				}
			}
		}
	}

	protected void checkAndUpdateInventory() {
		for (Item item : items) {
			if (item != null && item.getAmount() == 0 ) {
				if (!(item instanceof Pickaxe || item instanceof Shovel)) {
					removeItem(item, null);
				}
			}
		}
	}




	private ItemBox createButton(Item item) {
		ItemBox itemBox = new ItemBox(item);
		itemBox.setPrefSize(80, 80);
		itemBox.setOnAction((e) -> {
			createStackPane(item, itemBox);
		});

		return itemBox;
	}


	private void createStackPane(Item item, ItemBox itemBox) {
		
		if (item instanceof Potion) {
			SalePane potionSalePane = new SalePane(item, itemBox);
			getChildren().addAll(potionSalePane);
		} else if (item instanceof Ore) {
			SalePane oreSalePane = new SalePane(item, itemBox);
			getChildren().addAll(oreSalePane);
		} else if (item instanceof Tools) {
			UpgradePane toolsUpgradePane = new UpgradePane((Tools) item, itemBox);
			getChildren().addAll(toolsUpgradePane);
		}
	

	}
	
	public void updateGrid() {
		for (Node n : gridPane.getChildren()) {
			if (n instanceof ItemBox) {
		        ItemBox itemBox = (ItemBox) n;
		        itemBox.updateText(itemBox.getItem());
			}
		}
	}
	

	public GridPane getGridPane() {
		return gridPane;
	}

	public Button getCloseButton() {
		return closeButton;
	}

	public static InventoryPane getInstance() {
		return instance;
	}
	
}


