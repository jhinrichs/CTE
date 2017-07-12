package mate;

import java.util.ArrayList;
import java.util.List;

import mate.Brain.BrainModuleType;
import optimalExploration.MovingPlan;
import solutionData.RobPosTree;
import solutionData.Traversal;
import tree.INode;
import tree.Node;

public class MateAgentManager {

	private List<MateAgent> mates;

	private RobPosTree robPosTree;

	private INode exploredTree;

	private INode originalTree;

	private Traversal solution;

	public int searchDepth = 100;

	public MateAgentManager(Node root, int numberOfRobots, BrainModuleType brainType) {
		originalTree = root;
		solution = new Traversal(root, numberOfRobots);
		robPosTree = new RobPosTree(root, null, 0);
		root.setRobPos(robPosTree);
		for (int i = 0; i < numberOfRobots; i++) {
			mates.add(new MateAgent(root, brainType));
		}

	}

	public void nextStep() {
		calculateRobPosTree();
		List<MovingPlan> plan = new ArrayList<>();

		// plan steps
		for (MateAgent mate : mates) {
			plan.add(mate.calculateMove());
		}

		// do steps
		for (MovingPlan step : plan) {
			step.activate();
		}
	}

	private void calculateRobPosTree() {

		// get all nodes necessary to build proper RobPosTree
		ArrayList<Node> robPosNodes = new ArrayList<Node>();
		ArrayList<Node> nodesOnPath = new ArrayList<>();
		for (MateAgent mate : mates) {
			Node n = mate.getActiveNode();
			robPosNodes.add(n);
			nodesOnPath.add(n);
			while (n.getParent() != null && !nodesOnPath.contains(n)) {
				n = n.getParent();
				nodesOnPath.add(n);
			}
			if (!robPosNodes.contains(n)) {
				robPosNodes.add(n);
			}
		}

		// delete old robPosTree
		for (Node n : robPosNodes) {
			n.setRobPos(null);
			RobPosTree.activeRobs.clear();
		}

		// calculate and fill new values
		// f add agents to node
		for (MateAgent mate : mates) {
			mate.getActiveNode().getRobPos().addAgentToNode(mate);
		}

		// add tree structure and distance
		for (Node n : robPosNodes) {
			Node b = n.getParent();
			int distance = 0;
			while (!b.isRoot() && !robPosNodes.contains(b)) {
				distance++;
			}
			n.getRobPos().setParent(b);
			n.getRobPos().setDistanceToParent(distance);
			b.getRobPos().robPosChildren.add(n);

		}

		robPosTree.updateAllActiveRobs(searchDepth);

	}

}
