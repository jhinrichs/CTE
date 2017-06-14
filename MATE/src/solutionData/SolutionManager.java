package solutionData;

import optimalExploration.CollectiveExploration;
import optimalExploration.LeftWalker;
import tree.INode;
import tree.Node;

public class SolutionManager {

	private Node tree;
	private int numberOfRobots;
	private LeftWalker leftWalker;
	private CollectiveExploration collectiveExploration;

	public SolutionManager(Node tree, int numberOfRobots) {
		this.tree = tree;
		this.numberOfRobots = numberOfRobots;
	}

	public Traversal getOptimum() {

		Traversal best = getLeftWalker().getOptimum();
		
		if(getCTE().getOptimum().getSteps() < best.getSteps()){
			best = getCTE().getOptimum();
		}
		
		return best;
		
	}

	public LeftWalker getLeftWalker() {

		if (leftWalker == null) {
			leftWalker = new LeftWalker(tree, numberOfRobots);
		}
		
		return leftWalker;
	}
	
	
	public CollectiveExploration getCTE(){
		if (collectiveExploration == null){
			collectiveExploration = new CollectiveExploration(tree, numberOfRobots);
		}
		
		return collectiveExploration;
	}

}
