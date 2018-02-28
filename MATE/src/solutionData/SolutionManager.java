package solutionData;

import mate.AgentManager;
import mate.Brain.BrainModuleType;
import optimalExploration.CollectiveExploration;
import optimalExploration.LeftWalker;
import tree.Node;

public class SolutionManager {

	private Node tree;
	private int numberOfAgents;

	private CollectiveExploration collectiveExploration;

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

}
