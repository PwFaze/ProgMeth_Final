package Entity.Environment;

import Entity.Base.Block;
import Entity.Base.Cell;
import logic.game.GameLogic;
import logic.player.Player;
import util.MineralType;

public class Map {
	
	private int width;
	private int height;
	
	private static Cell[][] cellMap;
	private static final int BLOCKSIZE = 40;

	private static int BLOCKDEPTH;
	
	
	public Map(String[][] map) {
		int row = map.length;
		int column = map[0].length;
		setHeight(row);
		setWidth(column);
		System.out.println(getHeight());
		System.out.println(getWidth());
		
		cellMap = new Cell[row][column];
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < column; j++) {
				cellMap[i][j] = new Cell();
				switch(map[i][j].strip()) {
				case "REST":
					BLOCKDEPTH = i;
					break;
				case "N":
					break;
				case "G":
					setBlock(new DirtBlock(true), j, i);
					break;
				case "D":
					setBlock(new DirtBlock(false), j, i);
					break;
				case "S":
					setBlock(new OreBlock(MineralType.STONE), j, i);
					break;	
				case "I":
					setBlock(new OreBlock(MineralType.IRON), j, i);
					break;
				case "GOLD":
					setBlock(new OreBlock(MineralType.GOLD), j, i);
					break;
				case "RUBY" :
					setBlock(new OreBlock(MineralType.RUBY), j, i);
					break;
				case "DIA" :
					setBlock(new OreBlock(MineralType.DIAMOND), j, i);
					break;
				default :
					break;
				}
			}
			
		}
		System.out.println(BLOCKDEPTH);
		addAllBlock();
		
	}
	
	public void setBlock(Block b, int x, int y) {
		b.setX(x*BLOCKSIZE);
		b.setY(y*BLOCKSIZE);
		if (y > BLOCKDEPTH + 2) {
			b.setVisible(false);
		}
		else {
			b.setVisible(true);
		}
		cellMap[y][x].setBlock(b);
		
		
	}
	
	public void addAllBlock() {
		for(Cell[] row: cellMap) {
			for (Cell column: row) {
				if (column.isEmptied() == false) {
					GameLogic.addEntity(column.getBlock());
				}
			}
		}
		
	}
	
	public boolean isMoveable(Player p, double targetX, double targetY) {
	    if (Math.ceil(targetX / Map.getBlocksize())< 0 || Math.ceil(targetX / Map.getBlocksize()) > width -1||
	        targetY / Map.getBlocksize() < 0 || targetY / Map.getBlocksize() > height - 1) {
	        // Out of bounds -> return false
	        return false;
	    }

	    int blockX = (int) ((targetX + Map.getBlocksize() - Player.getPlayerSpeed()) / Map.getBlocksize());
	    int blockY = (int) ((targetY + Map.getBlocksize() - Player.getPlayerSpeed()) / Map.getBlocksize());

	    if (cellMap[blockY][blockX].isEmptied() == false) {
	        return false; // Blocked by a block
	    }

	    if (targetX == p.getX() && targetY == p.getY()) {
	        return false; // Target position is the same as the current position
	    }
	    
	    
	    if (targetY > Map.getBlockdepth() * Map.getBlocksize() && cellMap[blockY][blockX].isEmptied() == false) {
	    	return false;
	    }

	    return true;
	}



	public Cell[][] getCellMap() {
		return cellMap;
	}


	public int getWidth() {
		return width;
	}


	public void setWidth(int width) {
		this.width = width;
	}


	public int getHeight() {
		return height;
	}


	public void setHeight(int height) {
		this.height = height;
	}
	public static int getBlocksize() {
		return BLOCKSIZE;
	}

	public static final int getBlockdepth() {
		return BLOCKDEPTH;
	}
	
	public Cell getCell(int targetX, int targetY) {
		return getCellMap()[targetY][targetX];
	}

	

}
