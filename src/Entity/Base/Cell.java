package Entity.Base;

public class Cell {
	private Block block;
	private boolean isEmptied;
	
	public Cell() {
		this.block = null;
		setEmptied(true);
	
	}
	
	
	public boolean setBlock(Block b) {
		if (isEmptied() == true) {
			this.block = b;
			setEmptied(false);
			return true;
		}
		return false;
	}
	
	public void setEmptied(boolean isEmptied) {
		if (getBlock() != null) {
			if (getBlock().isDestroyed() == true) {
				this.isEmptied = true;
			}
			else {
				this.isEmptied = false;
			}
		}
		this.isEmptied = isEmptied;
	}
	
	public boolean isEmptied() {
		return isEmptied;
	}
	
	public Block getBlock() {
		return block;
	}


}
