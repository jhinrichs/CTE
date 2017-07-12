package solutionData;

import java.util.ArrayList;
import java.util.List;

import tree.Node;

public class RobPosTree {

	private Node origin;
	public Node getOrigin() {
		return origin;
	}

	public void setOrigin(Node origin) {
		this.origin = origin;
	}

	private Node parentRobPos;
	public List<Node> robPosChildren = new ArrayList<Node>();
	private List<Agent> agentsAtNode = new ArrayList<Agent>();
	private int distanceToParent;

	public static List<RobPosTree> activeRobs = new ArrayList<>();

	private int[] nodesInDistances = null;

	public RobPosTree(Node origin, Node parent, int distanceToParent) {
		this.origin = origin;
		this.setParent(parent);
		this.distanceToParent = distanceToParent;
	}

	public RobPosTree() {

	}

	public boolean isRobPosNode() {
		if (agentsAtNode.isEmpty() && robPosChildren.size() < 1) {
			return false;
		} else {
			return true;
		}
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

	public void addAgentToNode(Agent a) {
		agentsAtNode.add(a);
	}

	public void removeAgentFromNode(Agent a) {
		agentsAtNode.remove(a);
	}

	public void setAgentsAtNode(List<Agent> agentsAtNode) {
		this.agentsAtNode = agentsAtNode;
	}

	public int numberOfAllAgentsInTree() {
		if (robPosChildren.isEmpty()) {
			return agentsAtNode.size();
		} else {
			int numberOfRobots = 0;
			for (Node child : robPosChildren) {
				numberOfRobots += child.getRobPos().numberOfAllAgentsInTree();
			}
			return numberOfRobots;
		}
	}

	public Node getParent() {
		return parentRobPos;
	}

	public void setParent(Node parent) {
		this.parentRobPos = parent;
	}

	public void updateAllActiveRobs(int searchDepth) {
		for (RobPosTree rob : activeRobs) {
			rob.updateUnexploredNodesInDistances(searchDepth);
		}
	}

	private void updateUnexploredNodesInDistances(int searchDepth) {

		List<Node> exploredNodes = origin.getChildren();
		for (int i = 0; i < searchDepth; i++) {
			int unvisitedNodes = 0;
			List<Node>  nodesToExplore = new ArrayList<>();

			for (Node child : exploredNodes) {
				// falls endpunkt --> zählen
				// falls nicht weitersuchen
				if (child.visited && child.getRobPos()!=null && !child.isFinished()) {
					nodesToExplore.add(child);
				} else if(!child.visited){
					unvisitedNodes++;
				}
			}
			exploredNodes = nodesToExplore;
			nodesInDistances[i]=unvisitedNodes;
		}

	}

	public int[] getNodesInDistance(int searchDepth) {
		if (nodesInDistances == null || nodesInDistances.length < searchDepth) {
			nodesInDistances = new int[searchDepth];
			updateAllActiveRobs(searchDepth);
		}

		return nodesInDistances;

	}

	private void calculateNodesInDistance(int searchdepth) {
		if (searchdepth <= 0) {

		}

	}

	public void delete() {
		activeRobs.remove(this);
	}

}
