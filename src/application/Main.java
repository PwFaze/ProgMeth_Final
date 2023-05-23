package application;

import javafx.application.Application;
import javafx.stage.Stage;
import drawing.*;

public class Main extends Application{
	
	private MainMenu start;

	public void start(Stage stage) {
		start = new MainMenu(stage);
		stage.setTitle("Clear The Debt Advanture");
		stage.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
}
