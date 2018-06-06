package tree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import javax.swing.JCheckBox;

import jdk.nashorn.internal.runtime.arrays.NumericElements;

public class TreeFactory {

	public long seed;
	public long lastSeed;
	public int maxDepth;
	public int minDepth;
	public int maxBranches;
	public int minBranches;
	public double branchingfactor;
	public int maxNodes;
	public int minNodes;
	public int numberOfNodes;
	public boolean randomisiert=false;

	public Verteilungsfunktionen verteilung;

	private Random numberGenerator = new Random();
	public String function;

	public TreeFactory(long seed, int maxDepth, int minDepth, int maxBranches, int minBranches, int maxNodes,
			int minNodes, double branchingfactor, Verteilungsfunktionen verteilung, boolean randomized) {
		this.seed = seed;
		this.lastSeed = seed;
		this.branchingfactor = branchingfactor;
		this.minNodes = minNodes;
		this.maxNodes = maxNodes;
		this.maxBranches = maxBranches;
		this.maxDepth = maxDepth;
		this.minBranches = minBranches;
		this.minDepth = minDepth;
		this.verteilung = verteilung;
		randomisiert = randomized;

		this.numberGenerator = new Random(seed);
	}

	public TreeFactory(long seed) {
		this(seed, 10, 5, 5, 0, 50000, 100, 0.4, Verteilungsfunktionen.quadratisch, true);
	}

	public TreeFactory getNewFactory() {
		return new TreeFactory(numberGenerator.nextLong(), maxDepth, minDepth, maxBranches, minBranches, maxNodes,
				minNodes, branchingfactor, verteilung, randomisiert);
	}

	public Node createTree() {
		if(randomisiert) {
			return createTreeRand();
		}
		else {
			return createTreeEqual();
		}
	}
	
	public Node createTreeRand() {		// randomisierter
		lastSeed = numberGenerator.nextLong();
		numberGenerator = new Random(lastSeed);
		numberOfNodes = getNumberOfNodes();
		Node.setIdCount(0);
		Node root = new Node(null);
		root.setTreeCode(createTreeCode());
		root.setNumberOfNodesInTree(numberOfNodes);
		// List to save all leafs
		List<Node> leafs = new ArrayList<Node>();
		List<Node> allLeafs = new ArrayList<Node>();
		allLeafs.add(root);
		System.out.println("create new Tree with " + numberOfNodes + " nodes: min= "+minNodes +" max= " +maxNodes);
		if(numberOfNodes>maxNodes) {
			System.out.println("nodes bigger than max nodes");
		}

		while (Node.getIdCount() < numberOfNodes) {

			if (leafs.isEmpty()) {
				int rand = numberGenerator.nextInt(allLeafs.size());
				Node temp = allLeafs.get(rand);
				leafs.add(temp);
				allLeafs.remove(rand);
			}
			// leafs.add(getRandomLeaf(root));
			// add nodes current leaf until number of calculated nodes is reached
			while (Node.getIdCount() < numberOfNodes && !leafs.isEmpty()) {
				int rand =numberGenerator.nextInt(leafs.size());
				Node parent = leafs.get(rand);
				int numberOfChildren = getNumberofChildren();

				if (numberOfChildren == 0) {
					// wenn numer == 0 bedeuted dass, das es sich um ein Leaf handelt.
					allLeafs.add(parent);
				} 

				while (numberOfChildren > 0 && Node.getIdCount() < numberOfNodes) {
					Node newNode = new Node(parent);
					leafs.add(newNode);
					numberOfChildren--;
				}
				// remove the parent Node because it already got all children
				leafs.remove(rand);
				// Collections.shuffle(leafs);
			}
		}

		// System.out.println("Number of node = " + Node.getIdCount());

		return root;
	}

	
	public Node createTreeEqual() {	//gleichverteilter
		lastSeed = numberGenerator.nextLong();
		numberGenerator = new Random(lastSeed);
		numberOfNodes = getNumberOfNodes();
		Node.setIdCount(0);
		Node root = new Node(null);
		root.setTreeCode(createTreeCode());
		root.setNumberOfNodesInTree(numberOfNodes);
		// List to save all leafs
		List<Node> leafs = new ArrayList<Node>();
		List<Node> allLeafs = new ArrayList<Node>();
		allLeafs.add(root);
		System.out.println("create new Tree with " + numberOfNodes + " nodes");

		while (Node.getIdCount() < numberOfNodes) {

			if (leafs.isEmpty()) {
				int rand = numberGenerator.nextInt(allLeafs.size());
				Node temp = allLeafs.get(rand);
				leafs.add(temp);
				allLeafs.remove(rand);
			}
			// leafs.add(getRandomLeaf(root));
			// add nodes current leaf until number of calculated nodes is reached
			while (Node.getIdCount() < numberOfNodes && !leafs.isEmpty()) {
				
				Node parent = leafs.get(0);
				int numberOfChildren = getNumberofChildren();

				if (numberOfChildren == 0) {
					// wenn numer == 0 bedeuted dass, das es sich um ein Leaf handelt.
					allLeafs.add(parent);
				} 

				while (numberOfChildren > 0 && Node.getIdCount() < numberOfNodes) {
					Node newNode = new Node(parent);
					leafs.add(newNode);
					numberOfChildren--;
				}
				// remove the parent Node because it already got all children
				leafs.remove(0);
				// Collections.shuffle(leafs);
			}
		}

		// System.out.println("Number of node = " + Node.getIdCount());

		return root;
	}
	
	// returns a value beetween the min and max nodes value
	private int getNumberOfNodes() {
		return numberGenerator.nextInt((maxNodes+ 1) - minNodes) + minNodes;
	}

	private Node getRandomLeaf(Node n) {

		List<Node> leafList = n.getLeafList(null);

		if (leafList.isEmpty()) {
			
			return n;
		} else {
			int nextNode = numberGenerator.nextInt(leafList.size());
			return leafList.get(nextNode);
		}

	}

	private int getNumberofChildren() {

		switch (verteilung) {

		case quadratisch:
			return quadratischeVerteilung();

		case gleich:
			return gleichverteilt();

		case random:
			return random();

		case wurzel:
			return wurzelverteilung();

		}

		return 1;

	}

	private int random() {
		switch (numberGenerator.nextInt(3)){
		case 0:
			return gleichverteilt();
		case 1: 
			return quadratischeVerteilung();
		case 2:
			return wurzelverteilung();
		}
		return maxBranches;
		
	}

	private int gleichverteilt() {
		return minBranches + numberGenerator.nextInt(maxBranches - minBranches + 1);
	}

	private int quadratischeVerteilung() {
		return (int) (minBranches + Math.pow(numberGenerator.nextDouble(), 2) * (maxBranches - minBranches+1));
	}

	private int wurzelverteilung() {
		return (int) (minBranches + Math.sqrt(numberGenerator.nextDouble()) * (maxBranches - minBranches +1) );
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
		newTree.setNumberOfNodesInTree(root.getNumberOfNodesInTree());
		newTree.setTreeCode(root.getTreeCode());
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

	public String createTreeCode() {
		String treeCode = "#" + lastSeed + ";" + maxDepth + ";" + minDepth + ";" + maxBranches + ";" + minBranches + ";"
				+ branchingfactor + ";" + maxNodes + ";" + minNodes;
		return treeCode;
	}

}
