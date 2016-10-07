package tree;

public class TreeCreator {

	public int treeDepth = 10;
	public int maxBranches = 3;

	public TreeCreator() {

		Node root = new Node(null);

		Node activeNode = root;

		createTree(root, treeDepth, maxBranches);

	}

	public void createTree(Node parent, int depth, int width) {

		for (int i = 1+(int)Math.random()*width ; i < width; i++) {	
			Node newNode = new Node(parent);
			if (depth - 1 > 0) {
				createTree(newNode, depth - 1, width);
			}

		}
	}

}
