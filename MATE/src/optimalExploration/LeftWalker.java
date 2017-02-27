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

	private void computeOpt() {
		int energy = Integer.max(treeData.getDepth(), (treeData.getNodes() - 1) / numberOfRobots);
		for (int i = 0; i < numberOfRobots; i++) {

			Agent a = new Agent();
			a.addNodes(leftWalker(tree, 4 * energy));
		}
	}

	private List<Node> leftWalker(Node root, int energy) {
		while (!root.isFinished() && energy / 2 > 0) {

			for (Node child : tree.getChildren()) {
				if (!child.isFinished()) {
					energy--;
					leftWalker(child, energy);
				}
			}
		}
		return null;

	}

}
