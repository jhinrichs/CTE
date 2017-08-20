package solutionData;

import mate.AgentManager;
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
	private AgentManager mate;
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

	public AgentManager getMate() {
		if (mate == null) {
			 mate = new AgentManager(tree, numberOfAgents, brainType);
		}
		return mate;
	}

}
