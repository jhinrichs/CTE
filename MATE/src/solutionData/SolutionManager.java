package solutionData;

import optimalExploration.CollectiveExploration;
import optimalExploration.LeftWalker;
import tree.Node;

public class SolutionManager {

	private Node tree;
	private int numberOfAgents;

	private CollectiveExploration collectiveExploration;
	private LeftWalker leftie ;

	public SolutionManager(Node tree, int numberOfRobots) {
		this.tree = tree;
		this.numberOfAgents = numberOfRobots;
	}

	public CollectiveExploration getCTE() {
		if (collectiveExploration == null) {
			collectiveExploration = new CollectiveExploration(tree, numberOfAgents);
		}

		return collectiveExploration;
	}
	
	public LeftWalker getLeftWalker() {
		if(leftie == null) {
			leftie = new LeftWalker(tree, numberOfAgents);
		}
		return leftie;
	}

}
