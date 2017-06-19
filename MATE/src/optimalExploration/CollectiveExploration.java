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
	public Node activeNodes;

	TreeDataCalculator treeData;

	private Traversal solution;

	public CollectiveExploration(Node tree, int numberOfRobots) {
		this.root = TreeFactory.copyTree(tree);
		activeNodes= root;
		activeNodes.setRobPos(new RobPosTree(root,null,0));		
		this.numberOfRobots = numberOfRobots;
		treeData = new TreeDataCalculator(this.root);

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
		calculateMove(activeNodes,plan);

		// 2#
		move();

	}


	private void move() {

	}

	private List<MovingPlan> calculateMove(Node active, List<MovingPlan> plan) {
		
		// wenn knoten fertig und keine roboter mehr im Subtree	gehen alle roboter zum parent
		if (active.isFinished() && active.getRobPos().numberOfAllAgentsInTree() == active.getRobPos().getAgentsAtNode().size() && active != root ) {
			for (Agent a : active.getRobPos().getAgentsAtNode()) {
				plan.add(new MovingPlan(active.getRobPos().getParent(), a));
			}
		} else 
		//aufteilung auf unvollendete teilbäume
		{
			
			//get unfinished children
			List<Node> unfinishedNodes = new ArrayList<Node>();
			for (Node child : active.getChildren()) {
				if (!child.isFinished()) {
					unfinishedNodes.add(child);
				}
			}
			int min =0;
			int minRobots = -1;
			for (Node child : unfinishedNodes) {
				//find first subtree with less robots
				if (minRobots < 0){
					
				}
			}

			for (Agent a : active.getRobPos().getAgentsAtNode()) {
			// unfinisched
				// plan.add(new MovingPlan(unfinishedNodes.get(i%active.agents.size()), a));
			}

		}
return null;
	}

}

class ActiveNode {
	public Node node;
	public List<Agent> agentsAtNode;
	public int numberOfAllAgentsInTree;

	public ActiveNode(Node root, List<Agent> a) {
		node = root;
		agentsAtNode = a;
		numberOfAllAgentsInTree = agentsAtNode.size();
	}

}
