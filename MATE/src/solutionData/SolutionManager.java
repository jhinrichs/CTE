package solutionData;

import optimalExploration.LeftWalker;
import tree.Node;

public class SolutionManager {

	private Node tree;
	private int numberOfRobots;
	private LeftWalker leftWalker;
	
	public SolutionManager(Node tree, int numberOfRobots) {
		this.tree = tree;
		this.numberOfRobots = numberOfRobots;
		leftWalker = new LeftWalker(tree, numberOfRobots);
	}

	public Traversal getOptimum() {
		
		return leftWalker.getOptimum();
	}

}
