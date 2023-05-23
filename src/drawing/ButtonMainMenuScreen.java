package drawing;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class ButtonMainMenuScreen extends VBox {
	
	private Button startButton;
	private Button exitButton;
	
	public ButtonMainMenuScreen() {
		
		setSpacing(10);
		setPrefWidth(700);
		setPrefHeight(150);
		setAlignment(Pos.CENTER);

		
		startButton = new Button("Start");
		startButton.setStyle("-fx-background-color: #537188;-fx-background-radius:20px;");
		startButton.setOnMouseEntered((e) -> {
			startButton.setStyle("-fx-background-color: #CBB279;-fx-background-radius:20px;");
		});
		startButton.setOnMouseExited((e) -> {
			startButton.setStyle("-fx-background-color: #537188;-fx-background-radius:20px;");
		});
		startButton.setPrefSize(300, 75);
		startButton.setFont(new Font(30));
		startButton.setTextFill(Color.WHITE);
		
		
		exitButton = new Button("Exit");
		exitButton.setStyle("-fx-background-color: #537188;-fx-background-radius:20px;");
		exitButton.setOnMouseEntered((e) -> {
			exitButton.setStyle("-fx-background-color: #CBB279;-fx-background-radius:20px;");
		});
		exitButton.setOnMouseExited((e) -> {
			exitButton.setStyle("-fx-background-color: #537188;-fx-background-radius:20px;");
		});
		exitButton.setPrefSize(300, 75);
		exitButton.setFont(new Font(30));
		exitButton.setTextFill(Color.WHITE);
		
		getChildren().addAll(startButton, exitButton);
		
		
	}
	
	
	public Button getExitButton() {
		return exitButton;
	}
	public Button getStartButton() {
		return startButton;
	}
	



}
