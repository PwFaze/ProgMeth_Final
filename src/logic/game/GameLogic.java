package logic.game;

import java.util.ArrayList;
import Entity.Base.Entity;
import Entity.Environment.Map;
import logic.player.Player;
import sharedObject.RenderableHolder;
import util.Interactable;

public class GameLogic {
	
	private static ArrayList<Entity> gameObjectContainer;
	private static Map gameMap;
	private static Player p;
	
	public GameLogic(String name, String color, String[][] map) {
		gameObjectContainer = new ArrayList<Entity>();
		initializeGameMap(map);
		initializePlayer(name, color);
		
	}

	public void initializeGameMap(String[][] map) {
		gameMap = new Map(map);

	}
	
	public void initializePlayer(String name, String color) {
		p = new Player("Faze");
		p.setY(Map.getBlockdepth() * Map.getBlocksize());
		addEntity(p);
	}

	
	public static void addEntity(Entity entity) {
		gameObjectContainer.add(entity);
		RenderableHolder.getInstance().add(entity);
		
	}
	
	public static Map getGameMap() {
		return gameMap;
	}
	
	public static Player getPlayer() {
		return p;
	}
	
	public static ArrayList<Entity> getGameObjectContainer() {
		return gameObjectContainer;
	}
	
	public static void interactWithBlock(Player p, int targetX, int targetY, Interactable interacted) {
		p.interact(interacted);
	}
	
}
