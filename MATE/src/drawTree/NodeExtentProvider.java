package drawTree;

import javax.swing.tree.TreeNode;

public class NodeExtentProvider<Node> implements org.abego.treelayout.NodeExtentProvider<Node> {

	
	public static int height =20;
	public static int width = height;
	
	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	@Override
	public double getHeight(Node node) {
		// TODO Auto-generated method stub
		return height;
	}

	@Override
	public double getWidth(Node node) {
		// TODO Auto-generated method stub
		return width;
	}

}
