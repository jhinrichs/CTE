package tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TreeCreator {

	private int maxDepth;
	private int minDepth;
	private int maxBranches;
	private int minBranches;
	private double branchingfactor = 0.4;
	private TreeSpecifier treeSpecifier;
	private Random numberGenerator = new Random();

	public TreeCreator(long seed, int maxDepth, int minDepth, int maxBranches, int minBranches,
			TreeSpecifier treeSpecifier) {
		this.maxBranches = maxBranches;
		this.maxDepth = maxDepth;
		this.minBranches = minBranches;
		this.minDepth = minDepth;
		this.treeSpecifier = treeSpecifier;
		this.numberGenerator = new Random(seed);
	}

	public TreeCreator(long seed) {
		this(seed, 10, 5, 5, 0, new TreeSpecifier(TreeTypes.standardTree, 100));
	}

	/**
	 * a fan Tree is a node with a lot of children. Looks like a fan. Add the
	 * specified number of children to a given Node
	 * 
	 * @param parent
	 * @param numberOfChildren
	 * @param depth
	 */
	public void addFanTree(Node parent) {
		int numberOfChildren = 10;
		System.out.println("Create FanTree with " + numberOfChildren + " Children");
		for (int i = 0; i < numberOfChildren; i++) {
			parent.addNode();
		}
	}

	/**
	 * A snake tree is a tree with just one child per node. Looks like a snake.
	 * Creates an stack of nodes with the given length and continues the tree
	 * with the last node created.
	 * 
	 * @param parent
	 * @param length
	 */
	public void addSnakeTree(Node parent, int depth) {
		int length = 10;
		System.out.println("Create snakeTree with length " + length);

		for (int i = 0; i < length; i++) {
			parent = new Node(parent);
		}

		addTree(parent, depth - 1);
	}

	/**
	 * add a tree specified by the TreeCreators attributes
	 * 
	 * @param parent
	 * @param depth
	 */
	public void addStandardTree(Node parent, int depth) {
		System.out.println("creating tree with depth " + depth);
		Node newNode = new Node(parent);
		addTree(newNode, depth - 1);
	}

	public void addTree(Node root) {
		addTree(root, maxDepth);
	}

	/**
	 * Creates a tree for the given Node with Tree Creators characteristics
	 * 
	 * @param parent
	 */
	public void addTree(Node parent, int depth) {

		if (depth - 1 > 0 && (maxDepth - depth < minDepth || numberGenerator.nextDouble()< branchingfactor)) {
			for (int i = minBranches + numberGenerator.nextInt(maxBranches - minBranches); i > 0; i--) {
				switch (treeSpecifier.getTreeType(numberGenerator.nextDouble())) {
				case standardTree:
					addStandardTree(parent, depth);
					break;				
					case snakeTree:
					addSnakeTree(parent, depth);
					break;
				case fanTree:
					addFanTree(parent);
					addTree(parent, depth);

				}
			}
		}

	}

	public Node createTree() {
		Node root = new Node(null);
		addTree(root);
		System.out.println("Number of node = "+ Node.getIdCount());
		return root;
	}

	public int getMaxBranches() {
		return maxBranches;
	}

	public int getMaxTreeDepth() {
		return maxDepth;
	}

	public int getMinBranches() {
		return minBranches;
	}

	public int getMinTreeDepth() {
		return minDepth;
	}

	public void setMaxBranches(int maxBranches) {
		this.maxBranches = maxBranches;
	}

	public void setMaxTreeDepth(int maxTreeDepth) {
		this.maxDepth = maxTreeDepth;
	}

	public void setMinBranches(int minBranches) {
		this.minBranches = minBranches;
	}

	public void setMinTreeDepth(int minTreeDepth) {
		this.minDepth = minTreeDepth;
	}

}
