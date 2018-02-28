package tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TreeFactory {

	private int maxDepth;
	private int minDepth;
	private int maxBranches;
	private int minBranches;
	private double branchingfactor;
	private int maxNodes;
	private int minNodes;

	private Random numberGenerator = new Random();

	public TreeFactory(long seed, int maxDepth, int minDepth, int maxBranches, int minBranches, int maxNodes,
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

	public TreeFactory(long seed) {
		this(seed, 10, 5, 5, 0, 50000, 100, 0.4);
	}

	
	public Node createTree() {
		Node.setIdCount(0);
		Node root = new Node(null);
		
		// List to save all leafs
		List<Node> leafs = new ArrayList<Node>();
		leafs.add(root);

		
		//add nodes current leaf until number of calculated nodes is reached
		while (Node.getIdCount() < maxNodes && !leafs.isEmpty()) {
			Node parent = leafs.get(0);
			int numberOfChildren = getNumberofChildren();
			
			while (numberOfChildren > 0 && Node.getIdCount() < maxNodes) {
				Node newNode = new Node(parent);
				leafs.add(newNode);
				numberOfChildren--;
			}
			//remove the parent Node because it already got all children
			leafs.remove(0);
		}
		
		System.out.println("Number of node = " + Node.getIdCount());

		return root;
	}

	private int getNumberofChildren() {
		
	if (maxNodes > Node.getIdCount()  || numberGenerator.nextDouble() > branchingfactor) {
		}
		return minBranches + numberGenerator.nextInt(maxBranches - minBranches + 1);
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
