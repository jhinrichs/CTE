package optimalExploration;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import drawTree.TreePainter;
import mate.MateAgent;
import solutionData.Agent;
import solutionData.IAgent;
import solutionData.RobPosTree;
import solutionData.Traversal;
import tree.Node;
import tree.TreeDataCalculator;
import tree.TreeFactory;
import treeExploration.ProgrammManager;

public class CollectiveExploration {

	public Node root;
	private int numberOfRobots = 0;
	private List<IAgent> agents = new ArrayList<IAgent>();

	TreeDataCalculator treeData;

	private Traversal solution;

	public CollectiveExploration(Node tree, int numberOfRobots) {
		this.root = TreeFactory.copyTree(tree);
		// this.root = tree;
		this.numberOfRobots = numberOfRobots;
		treeData = new TreeDataCalculator(this.root);
		solution = new Traversal(root, numberOfRobots);
		agents = solution.getAgents();
	}

	public Traversal getOptimum() {
		if (!solution.isValidSolution()) {
			computeOpt();
		}

		return solution;
	}

	private boolean computeOpt() {
		System.out.println("Start calculating Collective Tree Exploration Solution");
		int steps = 0;
		while (!root.isFinished() || !solution.allRobotsAtRoot() ) {
			System.out.println("calculating step " + steps);
			step();
			steps++;
		}

		return solution.isValidSolution();
	}
	


	private Traversal step() {

		// 1 calculate division for each populated node
		List<MovingPlan> plan = new ArrayList<MovingPlan>();
		calculateMove(plan);

		// 2#
		move(plan);
		//ProgrammManager.paintStep(solution);

		return solution;
	}

	private void move(List<MovingPlan> plan) {

		for (MovingPlan p : plan) {
			p.execute();
		}

	}

	private List<MovingPlan> calculateMove(List<MovingPlan> plan) {

		// // wenn knoten fertig und keine roboter mehr im Subtree gehen alle
		// roboter zum parent
		List<Node> activeNodes = getNodesWithAgents();

		for (Node n : activeNodes) {
			if (n.isLeaf() || n.isFinished() && !n.isRoot()) {
				for (IAgent a : n.getRobPos().getAgentsAtNode()) {
					plan.add(new MovingPlan(n.getParent(), a));
				}

			} else {
				// get unfinished subtrees
				ArrayList<Node> unfinishedSubtrees = new ArrayList<>();
				for (Node child : n.getChildren()) {
					if (!child.isFinished()) {
						unfinishedSubtrees.add(child);
					}
				}
				if (!unfinishedSubtrees.isEmpty()) {

					// getChildWithLeastNodes
					Node least = unfinishedSubtrees.get(0);
					int agentsInLeast = least.getRobPos().getAllAgentsInTree();
					for (Node subtree : unfinishedSubtrees) {
						if (subtree.getRobPos().getAllAgentsInTree() < agentsInLeast) {
							least = subtree;
							agentsInLeast = least.getRobPos().getAllAgentsInTree();
						}
					}

					// plan all movements for agents at node n
					int index = unfinishedSubtrees.indexOf(least);
					int size = unfinishedSubtrees.size();
					for (IAgent a : n.getRobPos().getAgentsAtNode()) {
						plan.add(new MovingPlan(unfinishedSubtrees.get(index % size), a));
						index++;
					}
				}
			}
		}

		return plan;
	}

	private List<Node> getNodesWithAgents() {
		List<Node> activeNodes = new ArrayList<Node>();
		for (IAgent a : agents) {
			Node position = a.getPosition();
			if (!activeNodes.contains(position)) {
				activeNodes.add(position);
			}
		}

		return activeNodes;
	}

}
