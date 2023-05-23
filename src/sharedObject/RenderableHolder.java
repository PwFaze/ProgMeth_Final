package sharedObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import javafx.scene.image.Image;


public class RenderableHolder {
	
	private static final RenderableHolder instance = new RenderableHolder();
	private ArrayList<IRenderable> entities;
	private Comparator<IRenderable> comparator;
	
	public static final int PLAYERSIZE = 60;
	public static final int BLOCKSIZE = 42;
	
	// background
	public static Image mainMenu;
	public static Image background;
	public static Image showInventoryButton;
	public static Image buttonShopBackground;
	public static Image shopBackground;
	public static Image bactToHomeButton;
	public static Image backgroundInventory; 
	public static Image showShopButton;
	public static Image lose;
	public static Image win;
	
	
	// AllBlock 
	public static Image dirt;
	public static Image stone;
	public static Image copper;
	public static Image iron;
	public static Image gold;
	public static Image ruby;
	public static Image diamond;
	public static Image grass;
	public static Image digged;
	
	// AllOreBar
	public static Image ironBar;
	public static Image goldBar;
	public static Image rubyBar;
	public static Image diamondBar;
	
	// Tools
	public static Image stonePickaxe;
	public static Image ironPickaxe;
	public static Image goldPickaxe;
	public static Image rubyPickaxe;
	public static Image diamondPickaxe;
	public static Image stoneShovel;
	public static Image ironShovel;
	public static Image goldShovel;
	public static Image rubyShovel;
	public static Image diamondShovel;

	
	// AllPotion
	public static Image hpPotionI;
	public static Image hpPotionII;
	public static Image hpPotionIII;
	public static Image staminaPotionI;
	public static Image staminaPotionII;
	public static Image staminaPotionIII;
	
	// Player
	public static Image idle;
	public static Image jump;
	public static Image fall;
	public static Image left;
	public static Image right;
	// 
	public static Image[] pickaxeDigging;
	public static Image[] shovelDigging;
	public static Image[] verticalDigging;
	

	
	
	static {
		loadResource();
	}
	
	
	public RenderableHolder() {
		entities = new ArrayList<IRenderable>();
		comparator = (IRenderable o1, IRenderable o2) -> {
			if (o1.getZ() > o2.getZ())
				return 1;
			return -1;
		};
		
	}
	
	public static RenderableHolder getInstance() {
		return instance;
	}
	public ArrayList<IRenderable> getObjects() {
		return entities;
	}

	public static void loadResource() {
		
		// Background
		mainMenu = new Image(ClassLoader.getSystemResource("Images/mainMenuBackground.png").toString());
		background = new Image(ClassLoader.getSystemResource("Images/background.png").toString());
		showInventoryButton = new Image(ClassLoader.getSystemResource("Images/showInventoryButton.png").toString(), 40, 40, true, true);
		showShopButton = new Image(ClassLoader.getSystemResource("Images/showShopButton.png").toString(), 40, 40, true, true);
		lose = new Image(ClassLoader.getSystemResource("player/lose.gif").toString());
		win = new Image(ClassLoader.getSystemResource("player/win.gif").toString());
		
		// AllBlockLoader
		dirt = new Image(ClassLoader.getSystemResource("Images/dirt.png").toString(), BLOCKSIZE - 2, BLOCKSIZE - 2, true, true);
		grass = new Image(ClassLoader.getSystemResource("Images/grass.png").toString(), BLOCKSIZE - 2, BLOCKSIZE - 2, true, true);
		stone = new Image(ClassLoader.getSystemResource("Images/stone.png").toString(), BLOCKSIZE, BLOCKSIZE, true, true);
		copper = new Image(ClassLoader.getSystemResource("Images/copper_ore.png").toString(), BLOCKSIZE, BLOCKSIZE, true, true);
		iron = new Image(ClassLoader.getSystemResource("Images/iron_ore.png").toString(), BLOCKSIZE, BLOCKSIZE, true, true);
		gold = new Image(ClassLoader.getSystemResource("Images/gold_ore.png").toString(), BLOCKSIZE, BLOCKSIZE, true, true);
		ruby = new Image(ClassLoader.getSystemResource("Images/ruby_ore.png").toString(), BLOCKSIZE + 1, BLOCKSIZE + 1, true, true);
		diamond = new Image(ClassLoader.getSystemResource("Images/diamond_ore.png").toString(), BLOCKSIZE, BLOCKSIZE, true, true);
		digged = new Image(ClassLoader.getSystemResource("Images/digged.png").toString(), BLOCKSIZE, BLOCKSIZE, true, true);
		
		// AllOreBarLoader
		ironBar = new Image(ClassLoader.getSystemResource("Images/iron_bar.png").toString(), BLOCKSIZE, BLOCKSIZE, true, true);
		goldBar = new Image(ClassLoader.getSystemResource("Images/gold_bar.png").toString(), BLOCKSIZE, BLOCKSIZE, true, true);
		rubyBar = new Image(ClassLoader.getSystemResource("Images/ruby_bar.png").toString(), BLOCKSIZE, BLOCKSIZE, true, true);
		diamondBar = new Image(ClassLoader.getSystemResource("Images/diamond_bar.png").toString(), BLOCKSIZE, BLOCKSIZE, true, true);
		
		// ToolsLoader
		stonePickaxe = new Image(ClassLoader.getSystemResource("Tools/stonePickaxe.png").toString());
		ironPickaxe = new Image(ClassLoader.getSystemResource("Tools/ironPickaxe.png").toString());
		goldPickaxe = new Image(ClassLoader.getSystemResource("Tools/goldPickaxe.png").toString());
		rubyPickaxe = new Image(ClassLoader.getSystemResource("Tools/rubyPickaxe.png").toString());
		diamondPickaxe = new Image(ClassLoader.getSystemResource("Tools/diamondPickaxe.png").toString());
		stoneShovel = new Image(ClassLoader.getSystemResource("Tools/stoneShovel.png").toString());
		ironShovel = new Image(ClassLoader.getSystemResource("Tools/ironShovel.png").toString());
		goldShovel = new Image(ClassLoader.getSystemResource("Tools/goldShovel.png").toString());
		rubyShovel = new Image(ClassLoader.getSystemResource("Tools/rubyShovel.png").toString());
		diamondShovel = new Image(ClassLoader.getSystemResource("Tools/diamondShovel.png").toString());
		
		// AllPotionLoader
		hpPotionI = new Image(ClassLoader.getSystemResource("Images/healthPotionBasic.PNG").toString());
		hpPotionII = new Image(ClassLoader.getSystemResource("Images/healthPotionII.PNG").toString());
		hpPotionIII = new Image(ClassLoader.getSystemResource("Images/healthPotionIII.PNG").toString());  
		staminaPotionI = new Image(ClassLoader.getSystemResource("Images/staminaPotionBasic.PNG").toString());
		staminaPotionII = new Image(ClassLoader.getSystemResource("Images/staminaPotionII.PNG").toString());
		staminaPotionIII = new Image(ClassLoader.getSystemResource("Images/staminaPotionIII.PNG").toString());
		
		// Player
		idle = new Image(ClassLoader.getSystemResource("player/idle.png").toString(), PLAYERSIZE, PLAYERSIZE, true, true);
		jump = new Image(ClassLoader.getSystemResource("player/jump.png").toString(), PLAYERSIZE, PLAYERSIZE, true, true);
		fall = new Image(ClassLoader.getSystemResource("player/fall.png").toString(), PLAYERSIZE, PLAYERSIZE, true, true);
		left = new Image(ClassLoader.getSystemResource("player/left.png").toString(), PLAYERSIZE, PLAYERSIZE, true, true);
		right = new Image(ClassLoader.getSystemResource("player/right.png").toString(), PLAYERSIZE, PLAYERSIZE, true, true);
		
		// digging
		pickaxeDigging = new Image[10];
		shovelDigging = new Image[10];
		verticalDigging = new Image[2];
		
		// Right
		pickaxeDigging[0] = new Image(ClassLoader.getSystemResource("player/stonePickaxeRight.png").toString(), PLAYERSIZE + 30, PLAYERSIZE + 30, true, true);
		shovelDigging[0] = new Image(ClassLoader.getSystemResource("player/stoneShovelRight.png").toString(), PLAYERSIZE + 30, PLAYERSIZE + 30, true, true);
		pickaxeDigging[1] = new Image(ClassLoader.getSystemResource("player/ironPickaxeRight.png").toString(), PLAYERSIZE + 30, PLAYERSIZE + 30, true, true);
		shovelDigging[1] = new Image(ClassLoader.getSystemResource("player/ironShovelRight.png").toString(), PLAYERSIZE + 30, PLAYERSIZE + 30, true, true);
		pickaxeDigging[2] = new Image(ClassLoader.getSystemResource("player/goldPickaxeRight.png").toString(), PLAYERSIZE + 30, PLAYERSIZE + 30, true, true);
		shovelDigging[2] = new Image(ClassLoader.getSystemResource("player/goldShovelRight.png").toString(), PLAYERSIZE + 30, PLAYERSIZE + 30, true, true);
		pickaxeDigging[3] = new Image(ClassLoader.getSystemResource("player/rubyPickaxeRight.png").toString(), PLAYERSIZE + 30, PLAYERSIZE + 30, true, true);
		shovelDigging[3] = new Image(ClassLoader.getSystemResource("player/rubyShovelRight.png").toString(), PLAYERSIZE + 30, PLAYERSIZE + 30, true, true);
		pickaxeDigging[4] = new Image(ClassLoader.getSystemResource("player/diamondPickaxeRight.png").toString(), PLAYERSIZE + 30, PLAYERSIZE + 30, true, true);
		shovelDigging[4] = new Image(ClassLoader.getSystemResource("player/diamondShovelRight.png").toString(), PLAYERSIZE + 30, PLAYERSIZE + 30, true, true);
		
		// Left
		pickaxeDigging[5] = new Image(ClassLoader.getSystemResource("player/stonePickaxeLeft.png").toString(), PLAYERSIZE + 30, PLAYERSIZE + 30, true, true);
		shovelDigging[5] = new Image(ClassLoader.getSystemResource("player/stoneShovelLeft.png").toString(), PLAYERSIZE + 30, PLAYERSIZE + 30, true, true);
		pickaxeDigging[6] = new Image(ClassLoader.getSystemResource("player/ironPickaxeLeft.png").toString(), PLAYERSIZE + 30, PLAYERSIZE + 30, true, true);
		shovelDigging[6] = new Image(ClassLoader.getSystemResource("player/ironShovelLeft.png").toString(), PLAYERSIZE + 30, PLAYERSIZE + 30, true, true);
		pickaxeDigging[7] = new Image(ClassLoader.getSystemResource("player/goldPickaxeLeft.png").toString(), PLAYERSIZE + 30, PLAYERSIZE + 30, true, true);
		shovelDigging[7] = new Image(ClassLoader.getSystemResource("player/goldShovelLeft.png").toString(), PLAYERSIZE + 30, PLAYERSIZE + 30, true, true);
		pickaxeDigging[8] = new Image(ClassLoader.getSystemResource("player/rubyPickaxeLeft.png").toString(), PLAYERSIZE + 30, PLAYERSIZE + 30, true, true);
		shovelDigging[8] = new Image(ClassLoader.getSystemResource("player/rubyShovelLeft.png").toString(), PLAYERSIZE + 30, PLAYERSIZE + 30, true, true);
		pickaxeDigging[9] = new Image(ClassLoader.getSystemResource("player/diamondPickaxeLeft.png").toString(), PLAYERSIZE + 30, PLAYERSIZE + 30, true, true);
		shovelDigging[9] = new Image(ClassLoader.getSystemResource("player/diamondShovelLeft.png").toString(), PLAYERSIZE + 30, PLAYERSIZE + 30, true, true);
		
		// 0-up, 1-down
		verticalDigging[0] = new Image(ClassLoader.getSystemResource("player/digUp.png").toString(), PLAYERSIZE + 30, PLAYERSIZE + 30, true, true);
		verticalDigging[1] = new Image(ClassLoader.getSystemResource("player/digDown.png").toString(), PLAYERSIZE + 30, PLAYERSIZE + 30, true, true);

		//backgroundPane
		buttonShopBackground = new Image(ClassLoader.getSystemResource("Images/backgroundButtonShop.png").toString());
		shopBackground = new Image(ClassLoader.getSystemResource("Images/backgroundShop.png").toString());
		bactToHomeButton = new Image(ClassLoader.getSystemResource("Images/backToHomeButton.png").toString());
		backgroundInventory = new Image(ClassLoader.getSystemResource("Images/backgroundInventory.png").toString());

	}
	
	public void add(IRenderable entity) {
		entities.add(entity);
		Collections.sort(entities, comparator);
	}
	


}
