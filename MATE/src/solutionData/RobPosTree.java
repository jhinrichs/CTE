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

	private Node parentRobPos = null;
	public List<Node> robPosChildren = new ArrayList<Node>();
	private List<IAgent> agentsAtNode = new ArrayList<IAgent>();
	private int distanceToParent = 0;

	public RobPosTree(Node origin, Node parent, int distanceToParent) {
		this.origin = origin;
		this.setParent(parent);
		this.distanceToParent = distanceToParent;
	}

	public RobPosTree(Node origin) {
		this.origin = origin;
	}

	public boolean isRobPosNode() {
		
		if (agentsAtNode.size() < 1 && robPosChildren.size() < 1 && !origin.isRoot()) {
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

	public List<IAgent> getAgentsAtNode() {
		return agentsAtNode;
	}

	public void addAgentToNode(IAgent a) {
		agentsAtNode.add(a);
	}

	public void removeAgentFromNode(IAgent a) {
		agentsAtNode.remove(a);
	}

	public void setAgentsAtNode(List<IAgent> agentsAtNode) {
		this.agentsAtNode = agentsAtNode;
	}

	/**returns the number of all agents that are located on any node going down from this node.
	 * @return
	 */
	public int getAllAgentsInTree() {
		if (robPosChildren.isEmpty()) {
			return agentsAtNode.size();
		} else {
			int numberOfRobots = 0;
			for (Node child : robPosChildren) {
				numberOfRobots += child.getRobPos().getAllAgentsInTree();
			}
			if(numberOfRobots >0 ) {
				// System.out.println("Number of robots in Subtree " + origin.getId() + " = " + numberOfRobots);
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



	public void moveAgentTo(IAgent a, Node newNode) {

		// check if agent at node
		if (agentsAtNode.contains(a)) {

			// if forward move:
			if (origin.getChildren().contains(newNode)) {

				moveForward(a, newNode);
			}
			// backward move to parent
			else if (origin.getParent().equals(newNode)) {
				moveBackward(a, newNode);
			}
		}
	}

	private void moveBackward(IAgent a, Node newNode) {
		agentsAtNode.remove(a);
		newNode.getRobPos().addAgentToNode(a);
		// if old position loses robPos status --> update all former children to new parent
		if (!isRobPosNode()) {
			parentRobPos.getRobPos().robPosChildren.remove(origin);
			for (Node n : robPosChildren) {
				n.getRobPos().update();
			}
		}
		// if old position is still robPos
		else {
			parentRobPos.getRobPos().robPosChildren.remove(origin);
			this.update();
			newNode.getRobPos().update();
		}
	}

	private void moveForward(IAgent a, Node newNode) {
		agentsAtNode.remove(a);

		if (!robPosChildren.contains(newNode)) {
			robPosChildren.add(newNode);
		}

		newNode.getRobPos().addAgentToNode(a);

		// get index of newNode
		int index = origin.getIndex(newNode);

		int minId = newNode.getId();

		// if child is last indexed all robpos children nodes with bigger id are
		// subtree of that node
		if (index +1 < origin.getChildCount()) {
			int maxId = origin.getChildAt(index).getId() - 1;
			// todo check if robposchildren with id beetween newnode.id and
			// origin.getChildAt(index+1) --> update that node
			for (Node n : robPosChildren) {
				if (n.getId() >= minId && n.getId() <= maxId) {
					robPosChildren.remove(n);
					n.getRobPos().update();
				}
			}
		} else {
			Node childToRemove = null;
			for (Node n : robPosChildren) {
				if (n.getId() >= minId) {
					childToRemove=n;
					n.getRobPos().update();
				}
			}
			robPosChildren.remove(childToRemove);
		}

		newNode.getRobPos().update();
	}

	private void update() {
		Node parent = origin.getParent();
		while(parent!=null && !parent.getRobPos().isRobPosNode()) {
			parent= parent.getParent();
		}
		parentRobPos = parent;
		
		if(parent != null && !parentRobPos.getRobPos().robPosChildren.contains(origin)){
		parentRobPos.getRobPos().robPosChildren.add(origin);
		}
	}

}
