package tree;

public enum TreeTypes {
	standardTree, snakeTree, fanTree;

	private int snakeTreeLength = 5;
	private int fanTreeLength = 5;

	public int getlength() {

		switch (this) {
		case fanTree:
			return fanTreeLength;
		case snakeTree:
			return snakeTreeLength;
		default:
			break;
		}
		return 0;
	}
	
	public void setlength(int value) {

		switch (this) {
		case fanTree:
			fanTreeLength = value;
			break;
		case snakeTree:
			snakeTreeLength = value;
			break;
		}
	}

}
