package tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TreeFactoryOld {

	private int maxDepth;
	private int minDepth;
	private int maxBranches;
	private int minBranches;
	private double branchingfactor;
	private int maxNodes;
	private int minNodes;

	private Random numberGenerator = new Random();

	public TreeFactoryOld(long seed, int maxDepth, int minDepth, int maxBranches, int minBranches, int maxNodes,
			int minNodes, double branchingfactor) {
		this.branchingfactor = branchingfactor;
		this.minNodes = minNodes;
		this.maxNodes = maxNodes;
		this.maxBranches = maxBranches;
		this.maxDepth = maxDepth;
		this.minBranches = minBranches;
		this.minDepth = minDepth;

		this.numberGenerator = new Random(seed);
	}

	public TreeFactoryOld(long seed) {
		this(seed, 10, 5, 5, 0, 50000, 100, 0.4);
	}




	/**
	 * add a tree specified by the TreeCreators attributes
	 * 
	 * @param parent
	 * @param depth
	 */
	public void addStandardTree(Node parent, int depth) {
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

		if (maxNodes > Node.getIdCount() && depth - 1 > 0
				&& (maxDepth - depth < minDepth || numberGenerator.nextDouble() > branchingfactor)) {
			for (int i = minBranches + numberGenerator.nextInt(maxBranches - minBranches + 1); i > 0; i--) {
					addStandardTree(parent, depth);					
			}
		}

	}

	public Node createTree() {
		Node.setIdCount(0);
		Node root = new Node(null);
		while (Node.getIdCount() < minNodes) {
			addTree(root);
		}
		System.out.println("Number of node = " + Node.getIdCount());

		return root;
	}

	/**
	 * copy the given tree Structure. All nodes are created new. For algorithms
	 * that alter and work on trees directly
	 * 
	 * @param root
	 * @return
	 */
	public static Node copyTree(Node root) {
		Node newTree = new Node(null, root.getId());
		if (root.isLeaf()) {
			return newTree;
		} else {
			for (Node child : root.getChildren()) {
				newTree.addChild(copyTree(child));
			}
			return newTree;
		}

	}
	
	public static Node createTree(Node root, List<Node> nodes){
		
		Node newTree = new Node(null, root.getId());
			if(root.isLeaf()){
				return newTree;
			}
			else{
				for (Node child : root.getChildren()){
					if(nodes.contains(child)){
						newTree.addChild(createTree(child,nodes));
					}
				}
			}
		
		return newTree;
	}

}
