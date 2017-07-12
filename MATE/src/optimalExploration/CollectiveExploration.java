package optimalExploration;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import solutionData.Agent;
import solutionData.RobPosTree;
import solutionData.Traversal;
import tree.Node;
import tree.TreeDataCalculator;
import tree.TreeFactory;

public class CollectiveExploration {

	public Node root;
	private int numberOfRobots = 0;
	private List<Agent> agents = new ArrayList<Agent>();

	TreeDataCalculator treeData;

	private Traversal solution;

	public CollectiveExploration(Node tree, int numberOfRobots) {
		this.root = TreeFactory.copyTree(tree);
		this.numberOfRobots = numberOfRobots;
		treeData = new TreeDataCalculator(this.root);
		for (int i = 0; i < numberOfRobots; i++) {
			agents.add(new Agent());
		}
	}

	public Traversal getOptimum() {
		if (solution == null) {
			computeOpt();
		}

		return solution;
	}

	private boolean computeOpt() {
		System.out.println("Start calculating Collective Tree Exploration Solution");
		solution = new Traversal(root, numberOfRobots);

		while (!root.isFinished()) {

			step();
		}
		return solution.isValidSolution();
	}

	private void step() {

		// 1 calculate division for each populated node
		List<MovingPlan> plan = new ArrayList<MovingPlan>();
		calculateMove(plan);

		// 2#
		move();

	}

	private void move() {

	}

	private List<MovingPlan> calculateMove(List<MovingPlan> plan) {

		// // wenn knoten fertig und keine roboter mehr im Subtree gehen alle
		// roboter zum parent
		List<Node> activeNodes = getNodesWithAgents();
		
		for(Node n : activeNodes){
			
		}
		// aufteilung auf unvollendete teilbäume

		// get unfinished children

		return null;
	}

	private List<Node> getNodesWithAgents() {
		List<Node> activeNodes = new ArrayList<Node>();
		for (Agent a : agents) {
			Node position = a.getPosition();
			if(!activeNodes.contains(position)){
				activeNodes.add(position);
			}
		}
		
		return activeNodes;
	}

}






