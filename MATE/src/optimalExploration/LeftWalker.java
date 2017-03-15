package optimalExploration;

import java.util.ArrayList;
import java.util.List;

import solutionData.Agent;
import solutionData.Traversal;
import tree.Node;
import tree.TreeDataCalculator;
import tree.TreeFactory;

public class LeftWalker {

	private Node tree;
	private int numberOfRobots = 0;
	private List<Agent> agents = new ArrayList<Agent>();;

	TreeDataCalculator treeData;

	private Traversal optimumSolution;

	public LeftWalker(Node tree, int numberOfRobots) {
		this.tree = TreeFactory.copyTree(tree);
		this.numberOfRobots = numberOfRobots;
		treeData = new TreeDataCalculator(this.tree);
	}

	public Traversal getOptimum() {
		if (optimumSolution == null) {
			computeOpt();
		}

		return optimumSolution;
	}

	private boolean computeOpt() {
		System.out.println("Start calculating optimal Solution");
		optimumSolution = new Traversal(tree, numberOfRobots);
		int energy = Integer.max(treeData.getDepth(), (treeData.getNumberOfNodes() - 1) / numberOfRobots);
		System.out.println("Maximale Energie = "+energy);
		for (int i = 0; i < numberOfRobots; i++) {

			Agent a = new Agent(tree,2*energy);
			leftWalker(tree, a);
			optimumSolution.addAgent(a, i);
		}
		if (optimumSolution.isValidSolution()){
			return true;
		}
		return false;
	}

	private List<Node> leftWalker(Node root, int energy) {
		List<Node> nodesToVisit = new ArrayList<Node>();
		while (!root.isFinished() && energy / 2 > 0) {

			for (Node child : root.getChildren()) {
				//if not finished continue to traverse the tree to its leaf
				if (!child.isFinished() && energy / 2 > 0 && !child.isLeaf()) {

					nodesToVisit.addAll(leftWalker(child, energy - 1));
					energy -= nodesToVisit.size();
				}
				// if child is a leaf add this child to visitlist. 
				if(child.isLeaf()&& energy / 2 > 0){
					nodesToVisit.add(child);
					child.setFinished(true);
				}
				
			}
		}
		nodesToVisit.add(root);
		return nodesToVisit;

	}
	
	private void leftWalker( Node root, Agent a) {
		a.addNode(root);
		while (!root.isFinished() && a.enoughEnergy()) {

			for (Node child : root.getChildren()) {
				if(!child.isFinished() && a.enoughEnergy()){
					a.energy-=1;
					//if not finished continue to traverse the tree to its leaf
					if (!child.isLeaf()) {					
					leftWalker(child, a);
					
					} // if child is a leaf add this child to visitlist. 
					else{					
					a.addNode(child);
					child.setFinished(true);
					}				
				}				
			}
		}
		
	}

}
