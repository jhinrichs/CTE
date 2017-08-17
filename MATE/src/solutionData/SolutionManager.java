package solutionData;

import mate.MateAgentManager;
import mate.Brain.BrainModuleType;
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
	private double movingThreshhold;
	private double distanceInfluence;
	private BrainModuleType brainType;

	public SolutionManager(Node tree, int numberOfRobots) {
		this.tree = tree;
		this.numberOfAgents = numberOfRobots;
	}

	public SolutionManager(Node tree, int numberOfAgents, double movingThreshhold, double distanceInfluence, BrainModuleType brainType) {
		this.tree = tree;
		this.numberOfAgents = numberOfAgents;
		this.movingThreshhold = movingThreshhold;
		this.distanceInfluence = distanceInfluence;
		this.brainType = brainType;
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
			 mate = new MateAgentManager(tree, numberOfAgents, brainType);
		}
		mate.getOptimum();
		return null;
	}

}
