package drawTree;

import java.util.List;

import tree.Node;
import org.abego.treelayout.util.AbstractTreeForTreeLayout;

public class TreeForTreeLayout extends AbstractTreeForTreeLayout<tree.Node> {

	public TreeForTreeLayout(Node root) {
		super(root);
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<Node> getChildrenList(Node node) {
		return node.getChildren();
	}

	@Override
	public Node getParent(Node node) {
		
		return node.getParent();
	}

}
