package drawing;

import Entity.Base.Entity;
import Entity.Environment.Map;
import Input.InputUtility;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.animation.AnimationTimer;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.stage.Stage;
import logic.game.GameLogic;
import sharedObject.IRenderable;
import sharedObject.RenderableHolder;
import util.CSVParser;

public class GameScreen {
    private static final double ROOT_HEIGHT = 600;
    private static final double ROOT_WIDTH = 800;

    private static GraphicsContext gamegc;
    private GameLogic logic;
    private static Canvas gameCanvas;
    private static StackPane gamePane;

    private ShopPane shop = ShopPane.getInstance();
    private InventoryPane inventory;
    private HBox showBox;
    
    private HBox topper;
    private static final TimerPane timer = new TimerPane();
    private static final ShowStaminaPane stamina = new ShowStaminaPane();
    private static final ShowMoneyPane money = new ShowMoneyPane();

    
    private BorderPane root;
    private AnimationTimer playerThread;
    private double viewportX;
    private double viewportY;

    public GameScreen(Stage stage) {
        String[][] map = CSVParser.readCSV("gameMap.csv");
        logic = new GameLogic("Faze", "blue", map);
        GameLogic.getPlayer().setX(0);
        

        root = new BorderPane();
        root.setMaxSize(ROOT_WIDTH, ROOT_HEIGHT);
        root.setMaxHeight(ROOT_HEIGHT);

        // setUp shop and inventory Pane
        shop = ShopPane.getInstance();
        shop.setPrefSize(300, 200);
        inventory = InventoryPane.getInstance();
        
        // setUp gameCanvas
        gameCanvas = new Canvas(ROOT_WIDTH, ROOT_HEIGHT);
        gamegc = gameCanvas.getGraphicsContext2D();
        gamePane = new StackPane();
        gamePane.setPrefSize(ROOT_WIDTH, ROOT_HEIGHT);
//        gamePane.setMaxSize(800, 600);
        gamePane.getChildren().addAll(gameCanvas);
        
        // setUp inventoryButton and shopButton
        showBox = createShowButton();
        showBox.setPrefSize(50, 50);
        
        // setUp topper
        topper = new HBox();
        topper.setStyle("-fx-border-width: 5px; -fx-border-color : rgb(238, 238, 238);");
        topper.setPrefHeight(30);
        topper.setAlignment(Pos.CENTER);
        topper.setSpacing(30);
        topper.getChildren().addAll(stamina, timer, money);
        topper.getChildren().addAll(showBox);
        topper.setBackground(new Background(new BackgroundFill(Color.WHEAT, null, null)));
        
        // adding Children to root
        root.setTop(topper);
        root.setCenter(gamePane);
  
        shop.getBackButton().setOnAction((e) -> {
        	gamePane.getChildren().remove(shop);
        });
        
        inventory.getCloseButton().setOnAction((e) -> {
        	gamePane.getChildren().remove(inventory);
        });
        
   
        Scene scene = new Scene(root);
        addListener(scene);

        playerThread = new AnimationTimer() {
            public void handle(long now) {
                GameLogic.getPlayer().update();
                adjustViewport();
                logicUpdate();
                draw();
                if (GameLogic.getPlayer().getPlayerStamina() <= 0 || timer.getRemainingSeconds() <= 0) {
                    gamePane.getChildren().addAll(new EndingPane(false));
                    timer.getAnimationTimer().stop();
                    playerThread.stop();
                }
                else if (GameLogic.getPlayer().getPlayerMoney() >= 10000) {
                    gamePane.getChildren().addAll(new EndingPane(true));
                    timer.getAnimationTimer().stop();
                    playerThread.stop();
                }
            }
        };
        playerThread.start();

        stage.setScene(scene);
    }

    public void addListener(Scene scene) {
        scene.setOnKeyPressed((KeyEvent event) -> {
            InputUtility.setKeyPressed(event.getCode(), true);
        });

        scene.setOnKeyReleased((KeyEvent event) -> {
            InputUtility.setKeyPressed(event.getCode(), false);
        });

    }

    public void adjustViewport() {
        double playerX = GameLogic.getPlayer().getX();
        double playerY = GameLogic.getPlayer().getY();
        double canvasWidth = gameCanvas.getWidth();
        double canvasHeight = gameCanvas.getHeight();

        double canvasCenterX = canvasWidth / 2;
        double canvasCenterY = canvasHeight / 2;

        double mapWidth = GameLogic.getGameMap().getWidth() * Map.getBlocksize();
        double mapHeight = GameLogic.getGameMap().getHeight() * Map.getBlocksize();

        viewportX = Math.max(0, Math.min(playerX - canvasCenterX, mapWidth - canvasWidth));
        viewportY = Math.max(0, Math.min(playerY - canvasCenterY, mapHeight - canvasHeight));
    }

    public void draw() {
        gamegc.clearRect(0, 0, gameCanvas.getWidth(), gameCanvas.getHeight());
        gamegc.save();
        
        gamegc.translate(-viewportX, -viewportY); // Translate the canvas based on the viewport
        
        // Calculate the background position based on the player's coordinates
        double backgroundX = -(viewportX % RenderableHolder.background.getWidth());
        double backgroundY = -(viewportY % RenderableHolder.background.getHeight());
        
        // Draw the appropriate background image based on the player's color
        drawBackgroundImages(backgroundX, backgroundY);
        
        // Render the objects within the viewport
        for (IRenderable obj : RenderableHolder.getInstance().getObjects()) {
            obj.draw(gamegc);
        }
        
        gamegc.restore();
    }

    private void drawBackgroundImages(double backgroundX, double backgroundY) {
        double desiredWidth = gameCanvas.getWidth();
        double desiredHeight = gameCanvas.getHeight();

        // Calculate the number of repetitions needed horizontally and vertically
        int horizontalRepetitions = (int) Math.ceil(desiredWidth / RenderableHolder.background.getWidth());
        int verticalRepetitions = (int) Math.ceil(desiredHeight / RenderableHolder.background.getHeight());

        // Calculate the adjusted background position based on the player's coordinates
        double adjustedBackgroundX = backgroundX % RenderableHolder.background.getWidth();
        double adjustedBackgroundY = backgroundY % RenderableHolder.background.getHeight();

        // Draw the repeated background images to cover the entire viewport
        for (int y = 0; y < verticalRepetitions; y++) {
            for (int x = 0; x < horizontalRepetitions; x++) {
                double drawX = x * RenderableHolder.background.getWidth() - adjustedBackgroundX;
                double drawY = y * RenderableHolder.background.getHeight() - adjustedBackgroundY;
                gamegc.drawImage(RenderableHolder.background, drawX, drawY);
            }
        }
    }
    
    public void logicUpdate() {
		for (Entity e : GameLogic.getGameObjectContainer()) {
    		e.update();
    	}
    }

    public AnimationTimer getPlayerThread() {
        return playerThread;
    }

    public GameLogic getGameLogic() {
        return logic;
    }
    public static GraphicsContext getGameGc() {
    	return gamegc;
    }
    
    public HBox createShowButton() {
        HBox showBox = new HBox();
        showBox.setMaxSize(100, 50);
        showBox.setStyle("-fx-background-color: transparent;");
        Button shopButton = new Button();
        shopButton.setBackground(new Background(new BackgroundFill(Color.WHEAT, null, null)));
        shopButton.setMaxSize(50, 50);
        shopButton.setGraphic(new ImageView(RenderableHolder.showShopButton));
        shopButton.setOnAction((e) -> {
        	if (gamePane.getChildren().contains(shop)) {
        		gamePane.getChildren().remove(shop);
        	}
        	else {
        		gamePane.getChildren().add(shop);
        	}
        });
        
        Button inventoryButton = new Button();
        inventoryButton.setBackground(new Background(new BackgroundFill(Color.WHEAT, null, null)));
        inventoryButton.setMaxSize(50, 50);
        inventoryButton.setGraphic(new ImageView(RenderableHolder.showInventoryButton));
        inventoryButton.setOnAction((e) -> {
        	if (gamePane.getChildren().contains(inventory)) {
        		gamePane.getChildren().remove(inventory);
        	}
        	else {
        		gamePane.getChildren().add(inventory);
        	}
        });
        
        showBox.getChildren().addAll(shopButton,inventoryButton);
        return showBox;
    }
 }