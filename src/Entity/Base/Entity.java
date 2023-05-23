package Entity.Base;

import sharedObject.IRenderable;

public abstract class Entity implements IRenderable {
	
	protected int x;
	protected int y;
	protected boolean isDestroyed;
	protected boolean isVisible;
	protected int z;
	protected int hp;
	
	public int getHp() {
		return hp;
	}

	public void setHp(int hp) {
		this.hp = Math.max(0, hp);
		if (getHp() <= 0) {
			setDestroyed(true);
		}
	}

	public abstract void update();
	
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}
	public void setY(int y) {
		this.y = y;
	}

	public boolean isDestroyed() {
		return isDestroyed;
	}

	public void setDestroyed(boolean isDestroyed) {
		this.isDestroyed = isDestroyed;
	}

	public int getY() {
		return y;
	}
	public int getZ() {
		return z;
	}
	public void setZ(int z) {
		this.z = z;
	}
	public boolean isVisible() {
		return isVisible;
	}
	public void setVisible(boolean isVisible) {
		this.isVisible = isVisible;
	}

}
