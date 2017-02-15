package solutionData;

import optimalExploration.LeftWalker;
import tree.Node;

public class SolutionManager {

	private Node tree;
	private int numberOfRobots;
	private LeftWalker leftWalker;
	
	public SolutionManager(Node tree, int numberOfRobots) {
		// TODO Auto-generated constructor stub
	}

	public Traversal getOptimum() {
		// TODO Auto-generated method stub
		LeftWalker optimumSolution = new LeftWalker(tree, numberOfRobots);
		
		return null;
	}

}
