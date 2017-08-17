package solutionData;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.xml.stream.events.NotationDeclaration;

import tree.INode;
import tree.Node;
import tree.TreeFactory;

public class Agent implements IAgent {

	private Node root;
	public int energy = 0;
	private int id;
	private static int idCounter = 0;
	private ArrayList<Node> path = new ArrayList<Node>();

	public INode getRoot() {
		return root;
	}

	public Node getTree() {
		return TreeFactory.createTree(root, path);
	}

	public int getId() {
		return id;
	}
	
	public Node activeNode() {
		return path.get(path.size()-1);
	}

	public boolean enoughEnergy() {
		if (energy > 0) {
			return true;
		} else {
			return false;
		}
	}

	public Agent() {
		setId();
		path = new ArrayList<Node>();
	}

	private void setId() {
		id = idCounter;
		idCounter++;
	}

	public Agent(Node root) {
		setId();
		this.root = root;
		path.add(root);
		root.setVisited(true);
		root.getRobPos().addAgentToNode(this);
	}

	public Agent(Node root, int energy) {
		this.root = root;
		this.energy = energy;
		setId();
		path = new ArrayList<Node>();
	}

	public void addNode(Node nodeToVisit) {
		if (!path.contains(nodeToVisit)) {
			path.add(nodeToVisit);
		}
	}

	public void removeNode(INode nodeToRemove) {
		path.remove(nodeToRemove);
	}

	public ArrayList<Node> getNodesToVisit() {

		return path;
	}

	public void addNodes(List<Node> nodes) {
		path.addAll(nodes);
	}

	public int getStepsCount() {
		if (path.size() > 1) {
			return (path.size() - 1) * 2;
		}
		return 0;
	}

	public Node getPosition() {
		if (path.size() == 0) {
			return null;
		} else {
			
			return path.get(path.size()-1);
		}
	}

	public void moveAgent(Node newNode){
		activeNode().getRobPos().moveAgentTo(this, newNode);
		path.add(newNode);
		newNode.setVisited(true);
		
	}

}
