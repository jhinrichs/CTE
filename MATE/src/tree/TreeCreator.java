package tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TreeCreator {

	private int maxTreeDepth = 100;
	private int minTreeDepth=20;
	private int maxBranches = 5;
	private int minBranches = 0;
	private Random numberGenerator = new Random();

	public enum treeTypes {
		standardTree, snakeTree, fanTree;
	}

	private void treeSpecifier(treeTypes type, Node parent, int depth) {
		switch (type) {
		case standardTree:
			addStandardTree(parent, depth);
			break;
		case fanTree:
			addFanTree(parent, depth, (int) Math.random() * depth);
			break;
		case snakeTree:
			addSnakeTree(parent, (int) Math.random() * maxTreeDepth);
			break;
		}
	}

	private ArrayList<treeTypes> typesForTree;

	public ArrayList<treeTypes> getTypesForTree() {
		return typesForTree;
	}

	public void addTreeType(treeTypes type) {
		typesForTree.add(type);
	}

	public void removeTreeType(treeTypes type) {
		typesForTree.remove(type);
	}

	public TreeCreator() {
		typesForTree = new ArrayList<treeTypes>();
		typesForTree.add(treeTypes.standardTree);
		typesForTree.add(treeTypes.snakeTree);
		//test

	}

	/**add a tree specified by the TreeCreators attributes
	 * @param parent
	 * @param depth
	 */
	public void addStandardTree(Node parent, int depth) {
		System.out.println("creating tree with depth " + depth);		
			Node newNode = new Node(parent);
				addTree(newNode, depth);
	}

	/**
	 * A snake tree is a tree with just one child per node. Looks like a snake.
	 * Creates an stack of nodes with the given length and continues the tree with the last node created.
	 * 
	 * @param parent
	 * @param length
	 */
	public Node addSnakeTree(Node parent, int length) {
		System.out.println("Create snakeTree with length " + length);

			parent = new Node(parent);

		return parent;
	}

	/**
	 * a fan Tree is a node with a lot of children. Looks like a fan.
	 * Add the specified number of children to a given Node
	 * 
	 * @param parent
	 * @param numberOfChildren
	 * @param depth
	 */
	public Node addFanTree(Node parent, int depth, int numberOfChildren) {
		System.out.println("Create FanTree with " + numberOfChildren + " Children");
		for (int i = 0; i < numberOfChildren; i++) {
			new Node(parent);
		}
		return parent;
	}

	/**
	 * Creates a tree for the given Node with Tree Creators characteristics
	 * 
	 * @param parent
	 */
	public void addTree(Node parent, int depth) {
		// TODO Auto-generated method stub
		
		for (int i = minBranches + numberGenerator.nextInt(maxBranches-minBranches); i > 0; i--) {
			if (depth - 1 > 0) {
				treeTypes nextType = typesForTree.get(numberGenerator.nextInt(typesForTree.size()));
				treeSpecifier(nextType, parent, depth - 1);
			}

		}
	}

	/**
	 * sets the specified tree structure and creates a tree for the given Node.
	 * 
	 * @param parent
	 * @param maxDepth
	 * @param maxBranches
	 * @param minBranches
	 * @param minDepth TODO
	 */
	public void addTree(Node parent, int maxDepth, int maxBranches, int minBranches, int minDepth) {
		maxTreeDepth = maxDepth;
		this.minTreeDepth = minDepth;
		this.maxBranches = maxBranches;
		this.minBranches = minBranches;
		addTree(parent, maxTreeDepth);
	}

	public void addTree(Node root) {
		addTree(root, maxTreeDepth);
	}

}
