package drawing;

import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import sharedObject.RenderableHolder;
import javafx.scene.layout.HBox;
import javafx.scene.image.ImageView;

public class EndingPane extends StackPane {
	
	private Text gameEndingText;
	private BorderPane theScene;
	private Button toQuit;
	private boolean isWin;
	
	public EndingPane(boolean isWin) {
		
		setMaxSize(600, 400);
		setAlignment(Pos.CENTER);
		setWin(isWin);
		setPane();
		setStyle("-fx-border-width:10px; -fx-border-color :rgb(203, 178, 121);");
//		BackgroundImage background = new BackgroundImage(RenderableHolder.bactToHomeButton, BackgroundRepeat.NO_REPEAT,
//				BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
//				new BackgroundSize(500, 400, false, false, true, false));
//		setBackground(new Background(background));
		setBackground(new Background(new BackgroundFill(Color.WHEAT, null, null)));
	}
	
	private void setPane() {
		
		theScene = new BorderPane();
		theScene.setMaxSize(500, 400);
		
		gameEndingText = new Text();
		gameEndingText.setFill(Color.ALICEBLUE);
		gameEndingText.setFont(new Font(25));
		
		if(isWin() == true) {
			gameEndingText.setText("Congratulations!!");
		}
		else {
			gameEndingText.setText("GAME OVER");
		}
		VBox image = new VBox();
		image.setAlignment(Pos.CENTER);
		ImageView gif;
		if (isWin() == true) {
			gif = new ImageView(RenderableHolder.win);
		}
		else {
			gif = new ImageView(RenderableHolder.lose);
		}
		gif.setFitHeight(300);
		gif.setFitWidth(600);
		image.getChildren().addAll(gif);
		
		
		HBox confirm = new HBox();
		confirm.setAlignment(Pos.CENTER);
		confirm.setSpacing(50);
		toQuit = new Button("Quit");
		
		toQuit.setOnAction((e) -> {
			
			Platform.exit();
			
		});

		toQuit.setPrefSize(100, 20);
		
		confirm.getChildren().addAll(toQuit);
		
		VBox gameEndingBox = new VBox(gameEndingText);
		gameEndingBox.setPrefSize(100, 50);
		gameEndingBox.setAlignment(Pos.CENTER);
		gameEndingBox.setBackground(new Background(new BackgroundFill(Color.valueOf("A2A378"), null, null)));
		theScene.setTop(gameEndingBox);
		theScene.setCenter(gif);
		theScene.setBottom(confirm);
		
		getChildren().add(theScene);
	}

	public boolean isWin() {
		return isWin;
	}

	public void setWin(boolean win) {
		this.isWin = win;
	}
}

