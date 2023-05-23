package logic.player;

import Entity.Base.Block;
import Entity.Base.Entity;
import Input.InputUtility;
import drawing.InventoryPane;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import logic.game.GameLogic;
import logic.item.Item;
import logic.item.Ore;
import logic.tools.Pickaxe;
import logic.tools.Shovel;
import logic.tools.Tools;
import sharedObject.RenderableHolder;
import util.Direction;
import util.Interactable;
import util.MineralType;
import Entity.Environment.DirtBlock;
import Entity.Environment.Map;
import Entity.Environment.OreBlock;

public class Player extends Entity implements Interactable {

	private String name;
	private int playerStamina;
	private double playerMoney;
	private Item[] inventory;
	private int itemHoldingIndex = 0;
	private Direction dir;
	private boolean yKeyPressed = false;
	private static final int PLAYERSPEED = Map.getBlocksize() / 10;
	

	public Player(String name) {
		setName(name);
		setPlayerStamina(300);
		setPlayerMoney(1000);
		setHp(100);
		inventory = new Item[9];
		setZ(1);
		setDir(Direction.NONE);
		// wait for fixing
		Pickaxe pickaxe = new Pickaxe("Pickaxe", MineralType.STONE, 5);
		Shovel shovel = new Shovel("Shovel", MineralType.STONE, 3);
		
		inventory[0] = pickaxe;
		inventory[1] = shovel;
	}
	
	
	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public int getPlayerStamina() {
		return playerStamina;
	}

	public void setPlayerStamina(int stamina) {
		stamina = Math.min(300, stamina);
		this.playerStamina = Math.max(0, stamina);
	}

	public double getPlayerMoney() {
		return playerMoney;
	}

	public void setPlayerMoney(double playerMoney) {
		this.playerMoney = Math.max(0, playerMoney);
	}
	

	@Override
	public void draw(GraphicsContext gc) {
		// TODO Auto-generated method stub
		int blockX = getX() / Map.getBlocksize();
			switch(getDir()) {
			case NONE :
				gc.drawImage(RenderableHolder.idle, getX() + 20, getY());
				break;
			case UP :
				if (InputUtility.getKeyPressed(KeyCode.Y)) {				
					if (getX() - (blockX * Map.getBlocksize()) > 25|| getX() - (blockX * Map.getBlocksize()) == 0) {
						gc.drawImage(RenderableHolder.verticalDigging[0], getX() - 10, getY());
					}
					else {
						gc.drawImage(RenderableHolder.verticalDigging[0], getX() + 10, getY());
					}
				}
				else {
					if (getX() - (blockX * Map.getBlocksize()) > 25|| getX() - (blockX * Map.getBlocksize()) == 0) {
						gc.drawImage(RenderableHolder.jump, getX() - 10, getY());
					}
					else {
						gc.drawImage(RenderableHolder.jump, getX() + 10, getY());
					}
				}
				break;
			case DOWN :
				if (InputUtility.getKeyPressed(KeyCode.Y)) {
					if (getX() - blockX * Map.getBlocksize() > 25 || getX() - (blockX * Map.getBlocksize()) == 0) {
						gc.drawImage(RenderableHolder.verticalDigging[1], getX() - 10, getY());
					}
					else if (getX() - (blockX * Map.getBlocksize()) == 0) {
						gc.drawImage(RenderableHolder.verticalDigging[1], getX() - 10, getY());
					}
					else {
						gc.drawImage(RenderableHolder.verticalDigging[1], getX() + 10, getY());
					}
				}
				else {
					if (getX() - blockX * Map.getBlocksize() > 25 || getX() - (blockX * Map.getBlocksize()) == 0) {
						gc.drawImage(RenderableHolder.fall, getX() - 10, getY());
					}
					else if (getX() - (blockX * Map.getBlocksize()) == 0) {
						gc.drawImage(RenderableHolder.fall, getX() - 10, getY());
					}
					else {
						gc.drawImage(RenderableHolder.fall, getX() + 10, getY());
					}
				}
				break;
			case LEFT :
				if (InputUtility.getKeyPressed(KeyCode.Y)) {
					if (getHoldingItem() instanceof Pickaxe) {
						gc.drawImage(RenderableHolder.pickaxeDigging[getImageIndex(getHoldingItem()) + 5], getX(), getY() - 15);
					}
					else if (getHoldingItem() instanceof Shovel) {
						gc.drawImage(RenderableHolder.shovelDigging[getImageIndex(getHoldingItem()) + 5], getX(), getY() - 15);
					}
					else {
						gc.drawImage(RenderableHolder.left, getX() + 10, getY());
						System.out.println("Put the left man to the left job");
					}
				}
				else {
					gc.drawImage(RenderableHolder.left, getX() + 10, getY());
			}
				break;
			case RIGHT :
				if (InputUtility.getKeyPressed(KeyCode.Y)) {
					if (getHoldingItem() instanceof Pickaxe) {
						gc.drawImage(RenderableHolder.pickaxeDigging[getImageIndex(getHoldingItem())], getX(), getY() - 15);
					}
					else if (getHoldingItem() instanceof Shovel) {
						gc.drawImage(RenderableHolder.shovelDigging[getImageIndex(getHoldingItem())], getX(), getY() - 15);
					}
					else {
						gc.drawImage(RenderableHolder.right, getX(), getY());
						System.out.println("Put the right man to the right job");
					}

				}
				else {
					gc.drawImage(RenderableHolder.right, getX(), getY());
			}
				break;
			default :
				gc.drawImage(RenderableHolder.idle, getX(), getY());
				break;
			}
		
	}
	
	public int getImageIndex(Item item) {
		switch (((Tools) item).getOre()) {
		case STONE :
			return 0;
		case IRON :
			return 1;
		case GOLD :
			return 2;
		case RUBY :
			return 3;
		case DIAMOND :
			return 4;
		default :
			return 0;
			}
	
	}

	@Override
	public boolean isVisible() {
		// always true
		return true;
	}

	
	public void update() {
		
		if (InputUtility.getKeyPressed(KeyCode.DIGIT1)) {
			setHoldingItem(KeyCode.DIGIT1);
		}
		else if (InputUtility.getKeyPressed(KeyCode.DIGIT2)) {
			setHoldingItem(KeyCode.DIGIT2);
		}
	    if (InputUtility.getKeyPressed(KeyCode.D)) {
	            setDir(Direction.RIGHT);
	            move(KeyCode.D);
	            move(KeyCode.S);
//	            System.out.println(getX() + ", " + getY());
	        }
	        if (InputUtility.getKeyPressed(KeyCode.A)) {
	            setDir(Direction.LEFT);
	            move(KeyCode.A);
	            move(KeyCode.S);
//	            System.out.println(getX() + ", " + getY());
	        }
	        if (InputUtility.getKeyPressed(KeyCode.W)) {
	            setDir(Direction.UP);
	            climb(KeyCode.W);
//	            System.out.println(getX() + ", " + getY());
	        }
	        if (InputUtility.getKeyPressed(KeyCode.S)) {
	            setDir(Direction.DOWN);
	            move(KeyCode.S);
//	            System.out.println(getX() + ", " + getY());
	        }
	        if (!yKeyPressed && InputUtility.getKeyPressed(KeyCode.Y)) {
	            yKeyPressed = true;
	            double targetX = getX();
	            double targetY = getY();
	            switch (getDir()) {
	                case UP:
	                    targetY -= Map.getBlocksize();
	                    break;
	                case DOWN:
	                    targetY += Map.getBlocksize();
	                    break;
	                case LEFT:
	                    targetX -= Map.getBlocksize();
	                    break;
	                case RIGHT:
	                    targetX += Map.getBlocksize();
	                    break;
	                default:
	                    break;
	            }
	            int blockX = (int) Math.ceil(targetX / Map.getBlocksize());
	            int blockY = (int) Math.ceil(targetY / Map.getBlocksize());

	            // Check if the targeted position has a block
	            if (GameLogic.getGameMap().getCellMap()[blockY][blockX] != null) {
	                Block block = GameLogic.getGameMap().getCellMap()[blockY][blockX].getBlock();
	                if (block != null) {
	                    GameLogic.interactWithBlock(this, blockX, blockY, block);
	                    if (block.isDestroyed()) {
	                        GameLogic.getGameMap().getCellMap()[blockY][blockX].setEmptied(true);
	                    }
	                }
	            }
	        } else if (!InputUtility.getKeyPressed(KeyCode.Y)) {
	            yKeyPressed = false;
	        }

	}


	public void climb(KeyCode keycode) {
	    double targetX = getX();
	    double targetY = getY() - PLAYERSPEED;
	    int targetBlockX = (int) targetX / Map.getBlocksize();
	    int targetBlockY = (int) targetY / Map.getBlocksize();

	    // Check if the target block is null or has a platform
	    Block targetBlock = GameLogic.getGameMap().getCellMap()[targetBlockY][targetBlockX].getBlock();
	    boolean hasPlatform = targetBlock != null && targetBlock.hasPlatform();

	    if (hasPlatform || targetY / Map.getBlocksize() >= Map.getBlockdepth()) {
	        if (GameLogic.getGameMap().isMoveable(this, targetX, targetY)) {
	            if (keycode.equals(KeyCode.W)) {
	                setDir(Direction.UP);
	                setY(rounding(targetY));
	            }
	        }
	    }
	}



	public void move(KeyCode keycode) {
		double targetX = getX();
		double targetY = getY();
		switch(keycode) {
		case D:
			targetX += PLAYERSPEED;
			break;
		case A:
			targetX -= PLAYERSPEED;
			break;
		case S:
			targetY += PLAYERSPEED;
		default:
			break;
		}
		targetX = Math.max(0, targetX);
	 
		if (GameLogic.getGameMap().isMoveable(GameLogic.getPlayer(), targetX, targetY) == true ) {
			setX(rounding(targetX));
			setY(rounding(targetY));
		}

	}


	public static int getPlayerSpeed() {
		return PLAYERSPEED;
	}
	@Override
	public void interact(Interactable interacted) {
		if (interacted instanceof Block && GameLogic.getGameMap()
				.getCell(((Block) interacted).getX() / Map.getBlocksize(), ((Block) interacted).getY() / Map.getBlocksize()).isEmptied() == false) {
	        Block block = (Block) interacted;
			setPlayerStamina(getPlayerStamina() - ((Tools) getHoldingItem()).getStaminaUsed());
	        if (block instanceof DirtBlock && getHoldingItem() instanceof Shovel) {
	            block.setHp(block.getHp() - ((Tools) getHoldingItem()).getEfficiency() * 2);
	        }
	        else if (block instanceof OreBlock  && getHoldingItem() instanceof Shovel) {
	            block.setHp(block.getHp() - ((Tools) getHoldingItem()).getEfficiency() / 2);
	        }
	        else if (block instanceof DirtBlock  && getHoldingItem() instanceof Pickaxe) {
	            block.setHp(block.getHp() - ((Tools) getHoldingItem()).getEfficiency() / 2);
	        }
	        else if (block instanceof OreBlock  && getHoldingItem() instanceof Pickaxe) {
	            block.setHp(block.getHp() - ((Tools) getHoldingItem()).getEfficiency() * 2);
	        }
	        if (block.isDestroyed() == true && block.getName().equals("DIRT") == false) {
	        	Ore destroyedBlock = new Ore(block.getName().toUpperCase(), 1);
	        	InventoryPane.getInstance().addItem(destroyedBlock);
	        	for (Item it : GameLogic.getPlayer().getInventory()) {
	        		System.out.println(it);
	        	}
	        }
	    }
	}
	public Item[] getInventory() {
		return inventory;
	}
	public void setInventory(Item[] inventory) {
		this.inventory = inventory;
	}

	public Direction getDir() {
		return dir;
	}

	public void setDir(Direction dir) {
		this.dir = dir;
	}
	
	private int rounding(double num) {
		if ((num - (int) num )>= 0.5) {
			return (int) num + 1;
		}
		return (int) num;
	}
	
	public void setHoldingItem(KeyCode keycode) {
		switch (keycode) {
		case DIGIT1:
			setItemHoldingIndex(0);
			break;
		case DIGIT2:
			setItemHoldingIndex(1);
			break;
		default:
			return;
		}
	}

	public Item getHoldingItem() {
		return inventory[getItemHoldingIndex()];
	}


	public int getItemHoldingIndex() {
		return itemHoldingIndex;
	}

	public void setItemHoldingIndex(int itemHoldingIndex) {
		this.itemHoldingIndex = itemHoldingIndex;
	}
	
	public void updateInventory() {
		for (int i = 0 ; i < getInventory().length ; i++) {
			if (getInventory()[i].getAmount() <= 0 ) {
				getInventory()[i] = null;
			}
		}
	}



}
