package tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TreeFactory {

	public long seed;
	public int maxDepth;
	public int minDepth;
	public int maxBranches;
	public int minBranches;
	public double branchingfactor;
	public int maxNodes;
	public int minNodes;

	private Random numberGenerator = new Random();
	public String function;

	public TreeFactory(long seed, int maxDepth, int minDepth, int maxBranches, int minBranches, int maxNodes,
			int minNodes, double branchingfactor) {
		this.seed = seed;
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

		seed = numberGenerator.nextInt();
		numberGenerator = new Random(seed);

		Node.setIdCount(0);
		Node root = new Node(null);
		root.setTreeCode(createTreeCode());
		// List to save all leafs
		List<Node> leafs = new ArrayList<Node>();

		while (Node.getIdCount() < minNodes) {
			leafs.add(getLeaf(root));
			// add nodes current leaf until number of calculated nodes is reached
			while (Node.getIdCount() < maxNodes && !leafs.isEmpty()) {
				Node parent = leafs.get(0);
				int numberOfChildren = getNumberofChildren();

				while (numberOfChildren > 0 && Node.getIdCount() < maxNodes) {
					Node newNode = new Node(parent);
					leafs.add(newNode);
					numberOfChildren--;
				}
				// remove the parent Node because it already got all children
				leafs.remove(0);
			}
		}

		// System.out.println("Number of node = " + Node.getIdCount());

		return root;
	}

	private Node getLeaf(Node n) {
		if (n.isLeaf()) {
			return n;
		} else {
			Node leaf = null;
			while (leaf == null) {

				leaf = getLeaf(n.getChildAt(0));
			}
			return leaf;
		}
	}

	private int getNumberofChildren() {

		// return wurzelverteilung();
		return (int) (minBranches + Math.pow(numberGenerator.nextDouble(), 2) * maxBranches);
		// return minBranches + numberGenerator.nextInt(maxBranches - minBranches + 1);
	}

	private int wurzelverteilung() {
		return (int) (minBranches + Math.sqrt(numberGenerator.nextDouble()) * maxBranches);
	}

	/**
	 * copy the given tree Structure. All nodes are created new. For algorithms that
	 * alter and work on trees directly
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

	public static Node createTree(Node root, List<Node> nodes) {

		Node newTree = new Node(null, root.getId());
		if (root.isLeaf()) {
			return newTree;
		} else {
			for (Node child : root.getChildren()) {
				if (nodes.contains(child)) {
					newTree.addChild(createTree(child, nodes));
				}
			}
		}

		return newTree;
	}

	public String createTreeCode(){
		String treeCode = "#" + 
		seed +";"+             
		maxDepth +";"+          
		minDepth +";"+
		maxBranches +";"+       
		minBranches +";"+  
		branchingfactor +";"+ 
		maxNodes +";"+ 
		minNodes;          
		return treeCode;
	}

}
