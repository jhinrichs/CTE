package solutionData;

import java.util.List;

import tree.Node;

public class RobPosTree {

	public Node originalNode;
	private Node parent;
	public List<Node> children;
	private List<Agent> agentsAtNode;
	private int distanceToParent;

	public RobPosTree(Node root, Node parent, int distanceToParent) {
		this.setParent(parent);
		originalNode = root;
		this.distanceToParent = distanceToParent;
	}

	public int getDistanceToParent() {
		return distanceToParent;
	}

	public void setDistanceToParent(int distanceToParent) {
		this.distanceToParent = distanceToParent;
	}

	public List<Agent> getAgentsAtNode() {
		return agentsAtNode;
	}

	public void setAgentsAtNode(List<Agent> agentsAtNode) {
		this.agentsAtNode = agentsAtNode;
	}

	public int numberOfAllAgentsInTree() {
		if (children.isEmpty()) {
			return agentsAtNode.size();
		} else {
			int numberOfRobots = 0;
			for (Node child : children) {
				numberOfRobots += child.getRobPos().numberOfAllAgentsInTree();
			}
			return numberOfRobots;
		}
	}

	public Node getParent() {
		return parent;
	}

	public void setParent(Node parent) {
		this.parent = parent;
	}

}
