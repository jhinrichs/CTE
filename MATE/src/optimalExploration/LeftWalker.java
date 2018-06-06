package optimalExploration;

import java.util.ArrayList;
import java.util.List;

import solutionData.Agent;
import solutionData.IAgent;
import solutionData.Traversal;
import tree.Node;
import tree.TreeDataCalculator;
import tree.TreeFactory;

public class LeftWalker {

	public Node tree;
	private int numberOfRobots = 0;
private Node lastFound;
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
		// System.out.println("Start calculating LeftWalker Solution");
		 System.out.println("Tree Depth = " + treeData.getDepth() + ", Number of Nodes = " + treeData.getNumberOfNodes() + ", number of robots = " + numberOfRobots);
		optimumSolution = new Traversal(tree, numberOfRobots);
		int energy = Integer.max(treeData.getDepth(), ((treeData.getNumberOfNodes() - 1) / numberOfRobots)+1);
		System.out.println("Maximale Energie = max(TreeDepth, (numberOfNodes -1)/number of robots) = " + energy);
		lastFound = tree;
		for (int i = 0; i < numberOfRobots; i++) {

			Agent a = new Agent(tree, 2 * energy);
			a.moveToNode(lastFound);
			while(a.enoughEnergy() && !tree.checkIfFinished()) {
			leftWalker(a);
			}
//			cleanAgentPath(a);
			optimumSolution.addAgent(a, i);
		}
		if (optimumSolution.isValidSolution()) {
			return true;
		}
		return false;
	}

	/**
	 * @param a
	 */
	private void cleanAgentPath(IAgent a) {
		int size = a.getNodesToVisit().size();

		for (int i = size - 1; i >= 0; i--) {
			if (!a.getNodesToVisit().get(i).isLeaf()) {
				a.getNodesToVisit().remove(i);
			} else {
				return;
			}
		}
	}

//	/**
//	 * @deprecated
//	 * @param root
//	 * @param energy
//	 * @return
//	 */
//	@SuppressWarnings("unused")
//	private List<Node> leftWalker(Node root, int energy) {
//		List<Node> nodesToVisit = new ArrayList<Node>();
//		while (!root.checkIfFinished() && energy / 2 > 0) {
//
//			for (Node child : root.getChildren()) {
//				// if not finished continue to traverse the tree to its leaf
//				if (!child.checkIfFinished() && energy / 2 > 0 && !child.isLeaf()) {
//
//					nodesToVisit.addAll(leftWalker(child, energy - 1));
//					energy -= nodesToVisit.size();
//				}
//				// if child is a leaf add this child to visitlist.
//				if (child.isLeaf() && energy / 2 > 0) {
//					nodesToVisit.add(child);
//					child.setFinished(true);
//				}
//
//			}
//		}
//		nodesToVisit.add(root);
//		return nodesToVisit;
//
//	}

	private void leftWalker2(Node root, Agent a) {
		a.addNode(root);
		a.energy -= 1;
		while (!root.checkIfFinished() && a.enoughEnergy()) {

			for (Node child : root.getChildren()) {
				if (!child.checkIfFinished() && a.enoughEnergy()) {

					// if not finished continue to traverse the tree to its leaf
					if (!child.isLeaf()) {
						leftWalker(a);

					} // if child is a leaf add this child to visitlist.
					else {
						a.addNode(child);
						a.energy -= 1;
						child.setFinished(true);
					}
				}
			}
		}
	}

	private void leftWalker(Agent a) {
		if (a.energy <= 0) {
			lastFound= a.getPosition();
			goToRoot(a);			

		} else {
			Node nextNode = getNextNode(a.getPosition());
			if (nextNode != null) {
				a.moveAgentLeftWalker(nextNode);
			}
			
		}

	}

	private void goToRoot(Agent a) {
		while (!a.getPosition().isRoot()) {
			a.moveAgentLeftWalker(a.getPosition().getParent());
		}

	}

	private Node getNextNode(Node n) {
		for (Node child : n.getChildren()) {
			if (!child.checkIfFinished()) {
				return child;
			}
		}
		return n.getParent();

	}

}
