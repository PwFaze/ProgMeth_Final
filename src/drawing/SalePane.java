package drawing;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import logic.item.Item;
import logic.item.Potion;

public class SalePane extends StackPane {
	
	private HBox buttonsBox;
	
	public SalePane(Item item, ItemBox itemBox) {
        TextField amountTextField = new TextField();
        buttonsBox = new HBox(10);
        amountTextField.setMaxWidth(80);
        amountTextField.setPromptText("Amount");
        amountTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            // Validate that the input is a number
            if (!newValue.matches("\\d*")) {
                amountTextField.setText(newValue.replaceAll("[^\\d]", ""));
            }
        });

        Button consumeButton = new Button("Consume");
        consumeButton.setOnAction(event -> {
            String inputText = amountTextField.getText();
            int consumeAmount = inputText.isEmpty() ? 1 : Integer.parseInt(inputText);

            if (consumeAmount <= item.getAmount() && item instanceof Potion) {
                ((Potion) item).consume(consumeAmount);
                if (item.getAmount() == 0) {
                    InventoryPane.getInstance().removeItem(item, itemBox);
                }
            } else if(consumeAmount > item.getAmount()){
                System.out.println("Invalid amount");
            }
            else {
            	System.out.println("Invalid Type");
            }

            InventoryPane.getInstance().checkAndUpdateInventory();
            InventoryPane.getInstance().getChildren().remove(this);
        });

        Button sellButton = new Button("Sell");
        sellButton.setOnAction(event -> {
            String inputText = amountTextField.getText();
            int sellAmount = inputText.isEmpty() ? 1 : Integer.parseInt(inputText);

            if (sellAmount <= item.getAmount()) {
                item.sell(sellAmount);
                if (item.getAmount() == 0) {
                	InventoryPane.getInstance().removeItem(item, itemBox);
                }
            } else {
                System.out.println("Invalid amount");
            }

            InventoryPane.getInstance().checkAndUpdateInventory();
            InventoryPane.getInstance().getChildren().remove(this);
        });

        Button backButton = new Button("Back");
        backButton.setOnAction((e) -> { 
        	InventoryPane.getInstance().getChildren().remove(this);
        });
     
        buttonsBox.getChildren().addAll(consumeButton, sellButton, backButton);
        VBox vbox = new VBox(10, amountTextField, buttonsBox);
        vbox.setMaxSize(200, 300);
//        vbox.setBackground(new Background(new BackgroundFill(Color.WHITE, null, null)));
        getChildren().addAll(vbox);
        vbox.setAlignment(Pos.CENTER);
        setAlignment(vbox, Pos.CENTER);
        setAlignment(Pos.CENTER);
//        setBackground(new Background(new BackgroundFill(Color.AQUA, null, null)));

      
    }
}
