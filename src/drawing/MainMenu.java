package drawing;

import javafx.animation.AnimationTimer;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.canvas.*;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import sharedObject.AudioManager;
import sharedObject.RenderableHolder;


public class MainMenu {
	
	private GraphicsContext gc;
	
	private Canvas canvas;
	private StackPane root;
	private GameScreen gameScreen;
	private static Stage stage;
	
	private ButtonMainMenuScreen menu;
	
	private AnimationTimer mainMenuScreenSong;

	public MainMenu(Stage stage) {
		
		MainMenu.stage = stage;
		canvas = new Canvas(800, 600);
		gc = canvas.getGraphicsContext2D();
		gc.drawImage(RenderableHolder.mainMenu, 0, 0);
		menu = new ButtonMainMenuScreen();
		setUp();
		draw(gc);
	}
	

	
	public void draw(GraphicsContext gc) {
		
		root = new StackPane();
		root.setPrefSize(800, 600);
		root.setMaxSize(800, 600);
		
		root.getChildren().addAll(canvas);
		root.getChildren().addAll(menu);
		
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.setTitle("Test");
		stage.setResizable(false);
//		mainMenuScreenSong = new AnimationTimer() {
//			public void handle(long now) {
//				if(!AudioManager.gameMusic.isPlaying()) 
//					AudioManager.gameMusic.play();
//			
//			}
//		};
//		mainMenuScreenSong.start();
			
	}
	
	public void setUp() {
		menu.getExitButton().setOnAction((e) -> {
			
			Platform.exit();
		});
		
		menu.getStartButton().setOnAction((e) -> {
			
			root.getChildren().clear();
			gameScreen = new GameScreen(stage);
			
		});
		
		
	}
	
	public GameScreen getGameScreen() {
		return gameScreen;
	}
	

}