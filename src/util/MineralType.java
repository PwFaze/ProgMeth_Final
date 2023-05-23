package util;

public enum MineralType {
	DIRT,
	STONE,
	IRON,
	GOLD,
	RUBY,
	DIAMOND;
	
	public static MineralType getUpgrade(MineralType type) {
		switch (type) {
		case STONE:
			return IRON;
		case IRON:
			return GOLD;
		case GOLD:
			return RUBY;
		case RUBY:
			return DIAMOND;
		default:
			System.out.println("Invalid Ore");
			return DIAMOND;
		}
	}
	
	public static String toString(MineralType type) {
		return "" + type;
	}
	
	
}
