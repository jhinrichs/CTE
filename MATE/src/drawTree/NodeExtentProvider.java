package drawTree;

import javax.swing.tree.TreeNode;

public class NodeExtentProvider<Node> implements org.abego.treelayout.NodeExtentProvider<Node> {

	@Override
	public double getHeight(Node node) {
		// TODO Auto-generated method stub
		return 2;
	}

	@Override
	public double getWidth(Node node) {
		// TODO Auto-generated method stub
		return 2;
	}

}
