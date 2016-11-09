package tree;

public class TreeCreator {

	public int treeDepth;
	public int minTreeDepth;
	public int maxBranches;
	public int minBranches;
	
	
	public static void addTree(Node parent, int depth, int width) {

		for (int i = 1+(int) (Math.random()*width) ; i < width; i++) {	
			Node newNode = new Node(parent);
			if (depth - 1 > 0) {
				addTree(newNode, depth - 1, width);
			}

		}
	}

}
