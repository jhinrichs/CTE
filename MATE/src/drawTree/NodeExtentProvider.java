package drawTree;

import javax.swing.tree.TreeNode;

public class NodeExtentProvider<Node> implements org.abego.treelayout.NodeExtentProvider<Node> {

	
	private int height =20;
	private int width = height;
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
