package solutionData;

import mate.MateAgentManager;
import optimalExploration.CollectiveExploration;
import optimalExploration.LeftWalker;
import tree.INode;
import tree.Node;

public class SolutionManager {

	private Node tree;
	private int numberOfAgents;
	private LeftWalker leftWalker;
	private CollectiveExploration collectiveExploration;
	private MateAgentManager mate;

	public SolutionManager(Node tree, int numberOfRobots) {
		this.tree = tree;
		this.numberOfAgents = numberOfRobots;
	}

	public SolutionManager(Node tree, int numberOfAgents, double movingThreshhold, double distanceInfluence) {
		this.tree = tree;
		this.numberOfAgents = numberOfAgents;
	}

	public Traversal getOptimum() {

		Traversal best = getLeftWalker().getOptimum();

		if (getCTE().getOptimum().getSteps() < best.getSteps()) {
			best = getCTE().getOptimum();
		}

		return best;

	}

	public LeftWalker getLeftWalker() {

		if (leftWalker == null) {
			leftWalker = new LeftWalker(tree, numberOfAgents);
		}

		return leftWalker;
	}

	public CollectiveExploration getCTE() {
		if (collectiveExploration == null) {
			collectiveExploration = new CollectiveExploration(tree, numberOfAgents);
		}

		return collectiveExploration;
	}

	public MateAgentManager getMate() {
		if (mate == null) {
			// mate = new MateAgentManager(tree, numberOfRobots, brainType)
		}
		return null;
	}

}
